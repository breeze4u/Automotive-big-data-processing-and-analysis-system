<template>
  <div class="my-page">
    <div>
      <div  style="height: 80px">.</div>
      <h1 style="text-align: center; ">汽车之家数据分析系统</h1>
    </div>
    <div style="width: 400px; height: 400px; margin: 60px auto; background-color:rgba(85,210,220,0.5); border-radius: 10px">
      <div style="width: 100%; height: 100px; font-size: 30px; line-height: 100px; text-align: center; color: #101111">欢迎登录</div>
      <div style="margin-top: 25px; text-align: center; height: 320px;">
        <el-form :model="admin">
          <el-form-item>
            <el-input v-model="admin.name" prefix-icon="el-icon-user" style="width: 80%" placeholder="请输入用户名"></el-input>
          </el-form-item>
          <el-form-item>
            <el-input v-model="admin.password" prefix-icon="el-icon-lock" style="width: 80%" placeholder="请输入密码"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button style="width: 80%; margin-top: 10px" type="primary" @click="login()">登录</el-button>
          </el-form-item>
          <el-form-item>
            <div>没有账号去
            <el-button style="width: 10%; margin-top: 10px" type="text" @click="register">注册</el-button>
            </div>
          </el-form-item>
        </el-form>

      </div>
    </div>
  </div>


</template>

<script>

import request from "@/utils/request";

export default {
  name: "Login",
  data() {
    return {
      admin: {}
    }
  },
  // 页面加载的时候，做一些事情，在created里面
  created() {
  },
  // 定义一些页面上控件出发的事件调用的方法
  methods: {
    login() {
      request.post("/user/login", this.admin).then(res => {
        if (res.code === '0') {
          this.$message({
            message: '登录成功',
            type: 'success'
          });
          localStorage.setItem("user", JSON.stringify(res.data))
          this.$router.push("/admin");
        }
        else {
          this.$message({
            message: res.msg,
            type: 'error'
          });
        }

      })
    },
    register() {
      this.$router.push("/register");
    },
  }
}
</script>

<style scoped>
.my-page {
  background-image: url('@/assets/img.png');
  background-size: cover;
  background-position: center;
  width: 100%;
  height: 700px;
}
</style>
