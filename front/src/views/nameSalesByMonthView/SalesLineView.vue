<template>
  <div>
    <div class="block" style="margin-top: 10px">
      <span>选择时间</span>
      <el-date-picker style="width: 150px" value-format="yyyy-MM" v-model="params.infor1"
                      type="month" placeholder="选择日期" :picker-options="pickerOptions">
      </el-date-picker>
      <el-button type="warning" style="margin-left: 10px" @click="findBySearchLine()">查询</el-button>
    </div>
    <div style="width: 100%; float: left;margin-top: 20px">
      <div id="bar" style="height: 600px">
      </div>
    </div>
  </div>

</template>
<script>
import request from "@/utils/request";
import * as echarts from "echarts";
export default {
  name: "SalesLineView",
  data() {
    return {
      params: {
        infor1: '2023-11',
      },
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
    this.initLine();
    this.initEchartsLine();
  },
  methods: {

    initEchartsLine(){
      request.get("/sales/bar",{
        params: this.params
      }).then(res=>{
        if(res.code === '0'){
          let map = res.data
          this.initLine(map.xAxis,map.yAxis)
        }else {
          this.$message.error(res.msg)
        }
      })
    },
    findBySearchLine() {
      request.get("/sales/bar", {
        params: this.params
      }).then(res => {
        if (res.code === '0') {
          let map = res.data
          this.initLine(map.xAxis,map.yAxis)
        } else {
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
          text: '汽车厂商汽车销售数量Top30',
          subtext: this.params.infor1,
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

  }
}
</script>

