<template>
  <div>
    <div class="block" style="margin-top: 10px">
      <el-select v-model="params.infor1" style="width: 200px" placeholder="请选择车场">
        <el-option
            v-for="(parkingLot, index) in parkingLots"
            :key="index"
            :label="parkingLot.label"
            :value="parkingLot.value">
        </el-option>
      </el-select>

      <el-date-picker style="width: 150px; margin-left: 5px" value-format="yyyy" v-model="params.infor2"
                      type="year" placeholder="选择日期" :picker-options="pickerOptions">
      </el-date-picker>
      <el-button type="warning" style="margin-left: 10px" @click="handleClick">查询</el-button>
    </div>
    <div style="width: 100%; float: left;margin-top: 20px">
      <div id="bin" style="height: 600px"></div>
    </div>

    <div style="width: 100%; float: left;margin-top: 20px">
      <div id="bar" style="height: 600px"></div>
    </div>
    <div style="width: 100%; float: left;margin-top: 20px">
      <div id="line" style="height: 600px"></div>
    </div>
  </div>

</template>
<script>
import request from "@/utils/request";
import * as echarts from "echarts";
export default {
  name: "SalesByNameAndTimeView",
  data() {
    return {
      params: {
        infor1: '华晨宝马',
        infor2: '2023',
      },
      parkingLots: [],

      pickerOptions: {
        disabledDate(time) {
          const targetYear = time.getFullYear();
          return targetYear > 2023 || targetYear < 2007;
        }
      },
    };
  },
  // 页面加载的时候，做一些事情，在created里面
  mounted() {
    this.initBar();
    this.initEchartsBar();
    this.initBin();
    this.initEchartsBin();
    this.load();
  },
  methods: {
    handleClick() {
      this.findBySearchBar();
      this.findBySearchBin();
    },

    load() {
      request.get("/sales/getName").then(res => {
        if (res.code === '0') {
          this.parkingLots = res.data
        }
      })
    },
    initEchartsBar(){
      request.get("/sales/barMonthSalesByNameYear",{
        params: this.params
      }).then(res=>{
        if(res.code === '0'){
          let map = res.data
          this.initBar(map.xAxis,map.yAxis)
          this.initLine(map.xAxis,map.yAxis)
        }else {
          this.$message.error(res.msg)
        }
      })
    },
    findBySearchBar() {
      request.get("/sales/barMonthSalesByNameYear", {
        params: this.params
      }).then(res => {
        if (res.code === '0') {
          let map = res.data
          if (map.xAxis.length === 0 || map.yAxis.length === 0) {
            this.$message.error('暂无数据,请切换厂商或时间')
          } else {
            this.initBar(map.xAxis, map.yAxis)
            this.initLine(map.xAxis, map.yAxis)
          }
        } else {
          this.$message.error(res.msg)
        }
      })
    },

    initBar(xAxis,yAxis){
      let chartDom = document.getElementById('bar');
      let myChart = echarts.init(chartDom);
      let option;
      option = {
        title: {
          text: this.params.infor1+'汽车厂商'+this.params.infor2+'销售数据柱状图',
          subtext: '如无数据显示请切换厂商或时间',
          left: 'center'
        },
        xAxis: {
          type: 'category',
          data: xAxis,
          axisLabel: { interval: 0, rotate: 30
          }
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: yAxis,
            type: 'bar',
            itemStyle: {
              color: function(params) {
                // 这里返回一个颜色，例如根据数据大小返回不同的颜色
                let colorList = [
                  '#ff0000', '#00ff00', '#0000ff', '#ffff00', '#ff00ff',
                  '#00ffff', '#ff8c00', '#8b00ff', '#8b008b', '#8fbc8f',
                  '#8a2be2', '#7fffd4', '#2f4f4f', '#9400d3', '#9400ff',
                  '#4682b4', '#bada55', '#d3d3d3', '#f08080', '#b8860b',
                  '#a9a9a9', '#8b4513', '#d2b48c', '#8c5725', '#a0522d',
                  '#87cefa', '#b0e0e6', '#6b8e23'
                ];
                return colorList[Math.floor(Math.random() * colorList.length)];
              }
            }
          }
        ]
      };
      option && myChart.setOption(option);
    },

    initLine(xAxis,yAxis){
      let chartDom = document.getElementById('line');
      let myChart = echarts.init(chartDom);
      let option;
      option = {
        title: {
          text: this.params.infor1+'汽车厂商'+this.params.infor2+'销售数据折线图',
          subtext: '如无数据显示请切换厂商或时间',
          left: 'center'
        },
        xAxis: {
          type: 'category',
          data: xAxis,
          axisLabel: { interval: 0, rotate: 30
          }
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: yAxis,
            type: 'line',
            itemStyle: {
              color: function(params) {
                // 这里返回一个颜色，例如根据数据大小返回不同的颜色
                let colorList = [
                  '#ff0000', '#00ff00', '#0000ff', '#ffff00', '#ff00ff',
                  '#00ffff', '#ff8c00', '#8b00ff', '#8b008b', '#8fbc8f',
                  '#8a2be2', '#7fffd4', '#2f4f4f', '#9400d3', '#9400ff',
                  '#4682b4', '#bada55', '#d3d3d3', '#f08080', '#b8860b',
                  '#a9a9a9', '#8b4513', '#d2b48c', '#8c5725', '#a0522d',
                  '#87cefa', '#b0e0e6', '#6b8e23'
                ];
                return colorList[Math.floor(Math.random() * colorList.length)];
              }
            }
          }
        ]
      };
      option && myChart.setOption(option);
    },

    initEchartsBin(){
      request.get("/sales/binMonthSalesByNameYear",{
        params: this.params
      }).then(res=>{
        if(res.code === '0'){
          if(res.data.length===0){
            this.$message.error('暂无数据,请切换厂商或时间')
          }else {
            this.initBin(res.data)
          }
        }else {
          this.$message.error(res.msg)
        }
      })
    },

    findBySearchBin() {
      request.get("/sales/binMonthSalesByNameYear", {
        params: this.params
      }).then(res => {
        if(res.code === '0'){
          if(res.data.length!==0){
            this.initBin(res.data)
          }
        }else {
          this.$message.error(res.msg)
        }
      })
    },

    initBin(data){
      let chartDom = document.getElementById('bin');
      let myChart = echarts.init(chartDom);
      let option;

      option = {
        title: {
          text: this.params.infor1+'汽车厂商'+this.params.infor2+'销售数据柱状图',
          subtext: '如无数据显示请切换厂商或时间',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          left: 'left'
        },
        series: [
          {
            name: '汽车子品牌数量Top20',
            type: 'pie',
            radius: '50%',
            data: data,
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      };
      option && myChart.setOption(option);
    },
  }
}
</script>
