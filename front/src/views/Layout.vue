<template>
  <div id="layout">
    <el-container>
      <el-header style="background-color: #4fd7b9">
        <img src="@/assets/logo.png" alt="" style="width: 40px; position: relative; top: 10px;">
        <span style="font-size: 20px; margin-left: 15px; color: white">汽车之家数据分析系统</span>
        <el-dropdown style="float: right; height: 60px; line-height: 60px">
          <span class="el-dropdown-link" style="color: white; font-size: 16px">{{user.name}}<i class="el-icon-arrow-down el-icon--right"></i></span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>
              <div @click="logout">退出登录</div>
            </el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>

      </el-header>
    </el-container>

    <el-container>
      <el-aside style="overflow: hidden; min-height: 100vh; background-color: #545c64; width: 250px">
        <el-menu :default-active="$route.path" router background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
          <el-menu-item index="/">
            <i class="el-icon-s-home"></i>
            <span slot="title">系统首页</span>
          </el-menu-item>

          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-location"></i><span>用户管理</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="/admin">用户信息</el-menu-item>
            </el-menu-item-group>
          </el-submenu>

          <el-submenu index="3">
            <template slot="title">
              <i class="el-icon-location"></i><span>汽车数据展示</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="/newCar">汽车信息</el-menu-item>
              <el-menu-item index="/usedCar">二手车信息</el-menu-item>
              <el-menu-item index="/sales">汽车销售信息</el-menu-item>
              <el-menu-item index="/carBrand">汽车品牌</el-menu-item>
            </el-menu-item-group>
          </el-submenu>

<!--          <el-submenu index="4">-->
<!--            <template slot="title">-->
<!--              <i class="el-icon-location"></i><span>汽车品牌图表展示</span>-->
<!--            </template>-->
<!--            <el-menu-item-group>-->
<!--              <el-menu-item index="/bin">汽车品牌饼图</el-menu-item>-->
<!--              <el-menu-item index="/bar">汽车品牌柱状图</el-menu-item>-->
<!--              <el-menu-item index="/line">汽车品牌折线图</el-menu-item>-->
<!--            </el-menu-item-group>-->
<!--          </el-submenu>-->

          <el-submenu index="5">
            <template slot="title">
              <i class="el-icon-location"></i><span>汽车厂商月销售排名</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="/salesTopByTime">月销量排名</el-menu-item>
              <el-menu-item index="/salesBin">月销量排名饼图</el-menu-item>
              <el-menu-item index="/salesBar">月销量排名柱状图</el-menu-item>
              <el-menu-item index="/salesLine">月销量排名折线图</el-menu-item>
            </el-menu-item-group>
          </el-submenu>

          <el-submenu index="6">
            <template slot="title">
              <i class="el-icon-location"></i><span>汽车厂商销售数据</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="/salesByBrandAndTime">年销量数据销量图</el-menu-item>
              <el-menu-item index="/brandSaleByNameMonth">汽车品牌月销量</el-menu-item>
              <el-menu-item index="/brandSaleByMonth">汽车品牌月销量top30</el-menu-item>
            </el-menu-item-group>
          </el-submenu>


          <el-menu-item index="/pricePredict">
            <i class="el-icon-location"></i>
            <span slot="title">价格预测</span>
          </el-menu-item>

          <el-menu-item index="/test">
            <i class="el-icon-location"></i>
            <span slot="title">测试</span>
          </el-menu-item>
        </el-menu>
      </el-aside>

      <el-main>
        <router-view/>
      </el-main>
    </el-container>

  </div>
</template>

<script>
export default {
  name: "Layout",
  data () {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
    }
  },

  methods: {
    logout() {
      localStorage.removeItem("user");
      this.$router.push("/login");
    }
  }

}
</script>

