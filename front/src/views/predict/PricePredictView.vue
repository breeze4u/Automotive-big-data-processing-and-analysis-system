<template>
  <div>
    <div class="block" style="margin-top: 10px">
      <el-select v-model="carPredict.brand" style="width: 200px" placeholder="请选择品牌">
        <el-option
            v-for="(parkingLot, index) in parkingLotsBrand"
            :key="index"
            :label="parkingLot"
            :value="parkingLot">
        </el-option>
      </el-select>
      <el-select v-model="carPredict.local" style="width: 200px; margin-left: 5px" placeholder="请选择地区">
        <el-option
            v-for="(parkingLot, index) in parkingLotsLocal"
            :key="index"
            :label="parkingLot"
            :value="parkingLot">
        </el-option>
      </el-select>
      <el-select v-model="carPredict.time" style="width: 200px; margin-left: 5px" placeholder="请选择时间">
        <el-option
            v-for="(parkingLot, index) in parkingLotsTime"
            :key="index"
            :label="parkingLot"
            :value="parkingLot">
        </el-option>
      </el-select>
      <el-input v-model="carPredict.mileage" style="width: 200px; margin-left: 5px" placeholder="请输里程表" ></el-input>
      <el-input v-model="carPredict.sprice" style="width: 200px; margin-left: 5px" placeholder="请输入原价"></el-input>
      <el-button type="warning" style="margin-left: 10px" @click="getPrice()">查询</el-button>

    </div>
    <h3>预测价一：{{price1}}</h3>
    <h3>预测价二：{{price2}}</h3>
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
