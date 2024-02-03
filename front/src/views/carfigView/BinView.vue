<template>
  <div style="margin-top: 20px">
    <div id="bie" style="width: 100%;height: 600px">
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
    this.initBin();
    this.initEcharts();
  },
  methods: {
    initEcharts(){

      request.get("/newCar/bin").then(res=>{
        if(res.code === '0'){
          this.initBin(res.data)
        }else {
          this.$message.error(res.msg)
        }
      })
    },


    initBin(data){
      let chartDom = document.getElementById('bie');
      let myChart = echarts.init(chartDom);
      let option;

      option = {
        title: {
          text: '汽车子品牌数量top30',
          subtext: 'Fake Data',
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
    }
  }
}
</script>

