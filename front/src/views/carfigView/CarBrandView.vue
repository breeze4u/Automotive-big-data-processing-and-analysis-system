<template>
  <div>
    <div style="margin-top: 20px">
      <div id="bie" style="width: 100%;height: 600px">
      </div>
    </div>

    <div style="margin-top: 20px">
      <div id="bar" style="height: 600px">
      </div>
    </div>

    <div style="margin-top: 20px">
      <div id="line" style="height: 600px">
      </div>
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
    this.initBar();
    this.initLine();
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
      request.get("/newCar/bar").then(res=>{
        if(res.code === '0'){
          let map = res.data
          this.initBar(map.xAxis,map.yAxis)
        }else {
          this.$message.error(res.msg)
        }
      })
      request.get("/newCar/bar").then(res=>{
        if(res.code === '0'){
          let map = res.data
          this.initLine(map.xAxis,map.yAxis)
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
    },
    initBar(xAxis,yAxis){
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
            type: 'bar',
            itemStyle: {
              color: function (params) {
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
    },
  }
}
</script>

