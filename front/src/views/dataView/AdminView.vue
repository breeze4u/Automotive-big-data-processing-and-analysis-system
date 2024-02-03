<template>
  <div class="admin">
    <div>
      <el-input v-model="params.infor1" style="width: 200px" placeholder="请输入姓名"></el-input>
      <el-input v-model="params.infor2" style="width: 200px; margin-left: 5px" placeholder="请输入电话"></el-input>
      <el-button type="warning" style="margin-left: 10px" @click="findBySearch()">查询</el-button>
      <el-button type="warning" @click="reset()">清空</el-button>
      <el-button type="primary" style="margin-left: 10px" @click="add()">新增</el-button>
    </div>
    <div class="pagination-container" style="margin-top: 10px">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="params.pageNum"
          :page-sizes="[5, 10, 15, 20]"
          :page-size="params.pageSize"
          layout="total, sizes, prev, pager, next"
          :total="total">
      </el-pagination>
      <div class="jump-page" style="margin-left: 10px">
        <el-button type="primary" @click="handleJump" style="margin-right: 5px">跳转到</el-button>
        <el-input v-model="jumpPage" style="width: 60px "></el-input>
        <el-button type="primary" style="margin-left: 5px">页</el-button>
      </div>
    </div>

    <div>
      <el-table :data="tableData" style="width: 100%; margin: 15px 0px; height: 100%">
        <el-table-column prop="id" label="编号" width="80"></el-table-column>
        <el-table-column prop="name" label="姓名" width="140"></el-table-column>
        <el-table-column prop="pic" label="头像" width="140" >
          <template slot-scope="scope">
            <img :src="scope.row.pic" alt="图片" style="width: 100%; max-width: 100px; height: auto;">
          </template>
        </el-table-column>
        <el-table-column prop="password" label="密码" width="140"></el-table-column>
        <el-table-column prop="gender" label="性别" width="140"></el-table-column>
        <el-table-column prop="tel" label="电话" width="140"></el-table-column>
        <el-table-column prop="address" label="地址" width="140"></el-table-column>
        <el-table-column label="操作" class="align-buttons">
          <template slot-scope="scope">
            <div style="display: flex; align-items: center;">
              <el-button type="primary" @click="edit(scope.row)">编辑</el-button>
              <el-popconfirm title="确定删除吗？" @confirm="del(scope.row.id)">
                <el-button slot="reference" type="danger" style="margin-left: 5px; margin-right: 10px;">删除</el-button>
              </el-popconfirm>
            </div>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <div>
      <el-dialog title="请填写信息" :visible.sync="dialogFormVisible" width="30%">
        <el-form :model="form">
          <el-form-item label="姓名" label-width="15%">
            <el-input v-model="form.name" autocomplete="off" style="width: 90%"></el-input>
          </el-form-item>
          <el-form-item label="性别" label-width="15%">
            <el-radio v-model="form.gender" label="男">男</el-radio>
            <el-radio v-model="form.gender" label="女">女</el-radio>
          </el-form-item>
          <el-form-item label="密码" label-width="15%">
            <el-input v-model="form.password" autocomplete="off" style="width: 90%"></el-input>
          </el-form-item>
          <el-form-item label="电话" label-width="15%">
            <el-input v-model="form.tel" autocomplete="off" style="width: 90%"></el-input>
          </el-form-item>
          <el-form-item label="地址" label-width="15%">
            <el-input v-model="form.address" autocomplete="off" style="width: 90%"></el-input>
          </el-form-item>
          <el-form-item label="头像" label-width="15%">
            <el-input v-model="form.pic" autocomplete="off" style="width: 90%"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="submit()">确 定</el-button>
        </div>
      </el-dialog>
    </div>


  </div>
</template>
<script>


import request from "@/utils/request";

export default {
  name: "AdminView",
  data() {
    return {
      jumpPage: '',
      params: {
        pageNum: 1,
        pageSize: 10,
        infor1: '',
        infor2: '',

      },
      tableData: [],
      total: 0,
      dialogFormVisible: false,
      form: {}
    }
  },
  created() {
    this.load();
  },

  methods: {
    load() {
      request.get("/user/getListByParams").then(res => {
        if (res.code === '0') {
          this.tableData = res.data.list;
          this.total = res.data.total;
        }
      })
    },
    findBySearch() {
      request.get("/user/getListByParams", {
        params: this.params
      }).then(res => {
        if (res.code === '0') {
          this.tableData = res.data.list;
          this.total = res.data.total;
        } else {

        }
      })
    },
    del(id) {
      request.delete("/user/delete/" + id).then(res => {
        if (res.code === '0') {
          this.$message({
            message: '删除成功',
            type: 'success'
          });
          this.findBySearch();
        } else {
          this.$message({
            message: res.msg,
            type: 'error'
          });
        }
      })
    },


    add() {
      this.form = {};
      this.dialogFormVisible = true;
    },
    edit(obj) {
      this.form = obj;
      this.dialogFormVisible = true;
    },
    reset(){
      this.params = {
        info1: '',
        info2: ''
      }
      this.findBySearch();
    },
    handleSizeChange(pageSize) {
      this.params.pageSize = pageSize;
      this.findBySearch();
    },
    handleCurrentChange(pageNum) {
      this.params.pageNum = pageNum;
      this.findBySearch();
    },
    submit() {
      request.post("/user/saveUser", this.form).then(res => {
        if (res.code === '0') {
          this.$message({
            message: '操作成功',
            type: 'success'
          });
          this.dialogFormVisible = false;
          this.findBySearch();
        } else {
          this.$message({
            message: res.msg,
            type: 'error'
          });
        }
      })
    },handleJump() {
      const pageNum = parseInt(this.jumpPage, 10);
      if (Number.isInteger(pageNum) && pageNum >= 1) {
        this.params.pageNum = pageNum;
        this.findBySearch();
        this.jumpPage =''
      } else {
        this.$message.error('请输入有效的页码');
      }
    },
  },
}
</script>
<style>
.align-buttons {
  width: 100%;
}
</style>
