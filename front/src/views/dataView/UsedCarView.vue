<template>
  <div class="usedCar">
    <div>
      <el-input v-model="params.infor1" style="width: 200px" placeholder="请输入品牌"></el-input>
      <el-input v-model="params.infor2" style="width: 200px; margin-left: 5px" placeholder="请输入地区"></el-input>
      <el-input v-model="params.infor3" style="width: 200px; margin-left: 5px" placeholder="请输入里程"></el-input>
      <el-input v-model="params.infor4" style="width: 200px; margin-left: 5px" placeholder="请输入售价"></el-input>
      <el-button type="primary" style="margin-left: 10px" @click="findBySearch()">查询</el-button>
      <el-button type="warning" @click="reset()">清空</el-button>

    </div>
    <div class="pagination-container" style="margin-top: 10px">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="params.pageNum"
          :page-sizes="[10, 20, 50, 100]"
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
      <el-table :data="tableData" style="width: 100%; margin: 15px 0px">
        <el-table-column prop="id" label="编号" width="80"></el-table-column>
        <el-table-column prop="local" label="地区" width="100"></el-table-column>
        <el-table-column prop="brand" label="品牌" width="300"></el-table-column>
        <el-table-column prop="mileage" label="里程" width="120"></el-table-column>
        <el-table-column prop="time" label="购买时间" width="120"></el-table-column>
        <el-table-column prop="transfers" label="过户次数" width="120"></el-table-column>
        <el-table-column prop="sprice" label="原价" width="120"></el-table-column>
        <el-table-column prop="price" label="售价" width="120"></el-table-column>

      </el-table>
    </div>
    <div class="block">

    </div>
  </div>

</template>
<script>


import request from "@/utils/request";

export default {
  name: "UsedCarView",
  data() {
    return {
      jumpPage: '',
      params: {
        pageNum: 1,
        pageSize: 10,
        infor1: '',
        infor2: '',
        infor3: '',
        infor4: '',
      },
      tableData: []
    }
  },
  created() {
    this.load();
  },

  methods: {
    load() {
      request.get("/usedCar/getListByParams").then(res => {
        if (res.code === '0') {
          this.tableData = res.data.list;
          this.total = res.data.total;

        }
      })
    },
    findBySearch() {
      request.get("/usedCar/getListByParams", {
        params: this.params
      }).then(res => {
        if (res.code === '0') {
          this.tableData = res.data.list;
          this.total = res.data.total;
        } else {

        }
      })
    },
    reset(){
      this.params = {
        infor1: '',
        infor2: '',
        infor3: '',
        infor4: '',
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
    handleJump() {
      const pageNum = parseInt(this.jumpPage, 10);
      if (Number.isInteger(pageNum) && pageNum >= 1) {
        this.params.pageNum = pageNum;
        this.findBySearch();
        this.jumpPage =''
      } else {
        this.$message.error('请输入有效的页码');
      }
    },
  }
}
</script>
