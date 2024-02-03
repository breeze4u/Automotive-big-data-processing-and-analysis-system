<template>
  <div style="width: 100%; float: left;margin-top: 20px">
    <div id="bar" style="height: 600px">
    </div>
  </div>
</template>
<script>
import request from "@/utils/request";
import * as echarts from "echarts";
export default {
  name: "Test",
  data() {
    return {
    }
  },
  // 页面加载的时候，做一些事情，在created里面
  mounted() {
    this.initLine();
    this.initEcharts();
  },
  methods: {
    initEcharts(){

      request.get("/newCar/bar").then(res=>{
        if(res.code === '0'){
          let map = res.data
          this.initLine(map.xAxis,map.yAxis)
        }else {
          this.$message.error(res.msg)
        }
      })
    },


    initLine(xAxis,yAxis){
      let chartDom = document.getElementById('bar');
      let myChart = echarts.init(chartDom);
      let option;
      option = {
        title: {
          text: '汽车子品牌数量Top30',
          subtext: 'Fake Data',
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
            type: 'line'
          }
        ]
      };
      option && myChart.setOption(option);
    }
  }
}
</script>

