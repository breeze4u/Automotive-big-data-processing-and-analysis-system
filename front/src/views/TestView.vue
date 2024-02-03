<template>
  <div class="my-page">
    <div class="el-dialog--center"><h1>价格预测</h1></div>

    <div class="el-dialog--center" style="margin-top: 40px">
      <div style="margin-top: 20px">
        <span style="font-size: 20px; margin-left: 20px;">选择品牌</span>
        <el-select v-model="carPredict.brand" style="width: 150px;margin-left: 5px " placeholder="请选择品牌">
          <el-option
              v-for="(parkingLot, index) in parkingLotsBrand"
              :key="index"
              :label="parkingLot"
              :value="parkingLot">
          </el-option>
        </el-select>

        <span style="font-size: 20px; margin-left: 20px;">选择地区</span>
        <el-select v-model="carPredict.local" style="width: 150px; margin-left: 5px" placeholder="请选择地区">
          <el-option
              v-for="(parkingLot, index) in parkingLotsLocal"
              :key="index"
              :label="parkingLot"
              :value="parkingLot">
          </el-option>
        </el-select>


        <span style="font-size: 20px; margin-left: 20px;">选择时间</span>
        <el-select v-model="carPredict.time" style="width: 150px; margin-left: 5px" placeholder="请选择时间">
          <el-option
              v-for="(parkingLot, index) in parkingLotsTime"
              :key="index"
              :label="parkingLot"
              :value="parkingLot">
          </el-option>
        </el-select>
      </div>

      <div style="margin-top: 20px">
        <span style="font-size: 20px; margin-left: 20px;width: 100px">输入汽车行驶里程</span>
        <el-input v-model="carPredict.mileage" style="width: 150px; margin-left: 5px" placeholder="请输入里程表" ></el-input>
        <span style="font-size: 20px; margin-left: 5px;">万公里</span>

        <span style="font-size: 20px; margin-left: 50px;width: 100px">输入汽车原价</span>
        <el-input v-model="carPredict.sprice" style="width: 150px; margin-left: 5px" placeholder="请输入原价 "></el-input>
        <span style="font-size: 20px; margin-left: 5px;">万元</span>
        <el-button type="warning" style="margin-left: 40px; width: 100px" @click="getPrice()">查询</el-button>
      </div>
      <div style="margin-top: 40px">
        <span style="font-size: 20px; margin-right: 100px; color: rebeccapurple">预测价一：{{price1}}</span>
        <span style="font-size: 20px; margin-left: 100px; color: rebeccapurple">预测价二：{{price2}}</span>
      </div>
    </div>



  </div>
</template>
<script>
import request from "@/utils/request";
export default {
  name: "PricePredictView",
  data() {
    return {
      carPredict: {
        brand: '库里南',
        local: '广州',
        time: '2024款',
        mileage: '0.1',
        sprice: '750.06',
      },
      parkingLotsBrand: [],
      parkingLotsLocal: [],
      parkingLotsTime: [],
      price1: '',
      price2: '',

    };
  },
  created() {
    this.loadBrand(); // 在组件创建后加载数据
    this.loadLocal();
    this.loadTime();
  },
  methods: {
    loadBrand() {
      request.get("/carPredict/brand").then(res => {
        if (res.code === '0') {
          this.parkingLotsBrand = res.data;
        }
      })
    },
    loadLocal() {
      request.get("/carPredict/local").then(res => {
        if (res.code === '0') {
          this.parkingLotsLocal = res.data;
        }
      })
    },
    loadTime() {
      request.get("/carPredict/time").then(res => {
        if (res.code === '0') {
          this.parkingLotsTime = res.data;
        }
      })
    },
    getPrice() { // 新方法预测汽车价格
      request.get("/carPredict/predict", {
        params: this.carPredict
      }).then(res => {
        if (res.code === '0') { // 假设接口返回的code为'0'表示成功
          this.price1 = res.data.price1; // 将接口返回的数据赋值给组件的数据属性
          this.price2 = res.data.price2;
        }else {
          this.$message({
            message: res.msg,
            type: 'error'
          });
        }
      });
    },

  },
}
</script>

<style scoped>
.my-page {
  background-image: url('@/assets/img_2.png');
  background-size: cover;
  background-position: center;
  width: 100%;
  height: 700px;
}
</style>

