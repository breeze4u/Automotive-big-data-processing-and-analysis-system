<template>
  <div>
    <div class="block" style="margin-top: 10px">
      <span>选择时间</span>
      <el-date-picker style="width: 150px" value-format="yyyy-MM" v-model="params.infor1"
                      type="month" placeholder="选择日期" :picker-options="pickerOptions">

      </el-date-picker>
      <el-button type="warning" style="margin-left: 10px" @click="findBySearchBin()">查询</el-button>
    </div>
    <div style="margin-top: 20px">
      <div id="bin" style="width: 100%;height: 600px"></div>
    </div>
  </div>

</template>
<script>
import request from "@/utils/request";
import * as echarts from "echarts";
export default {
  name: "SalesBinView",
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
    this.initBin();
    this.initEchartsBin();
  },
  methods: {
    initEchartsBin(){
      request.get("/sales/bin",{
        params: this.params
      }).then(res=>{
        if(res.code === '0'){
          this.initBin(res.data)
        }else {
          this.$message.error(res.msg)
        }
      })
    },

    findBySearchBin() {
      request.get("/sales/bin", {
        params: this.params
      }).then(res => {
        if(res.code === '0'){
          this.initBin(res.data)
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
          text: '汽车厂商汽车销售数量Top30',
          subtext: this.params.infor1,
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

