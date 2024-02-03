import Vue from 'vue'
import VueRouter from 'vue-router'
import AdminView from "@/views/dataView/AdminView.vue";
import NewCarView from "@/views/dataView/NewCarView.vue";
import LoginView from "@/views/LoginView.vue";
import Layout from "@/views/Layout.vue";
import RegisterView from "@/views/RegisterView.vue";
import UsedCarView from "@/views/dataView/UsedCarView.vue";
import SalesView from "@/views/dataView/SalesView.vue";
import BinView from "@/views/carfigView/BinView.vue";
import BarView from "@/views/carfigView/BarView.vue";
import LineView from "@/views/carfigView/LineView.vue";
import SalesBinView from "@/views/nameSalesByMonthView/SalesBinView.vue";
import CarBrandView from "@/views/carfigView/CarBrandView.vue";
import SalesLineView from "@/views/nameSalesByMonthView/SalesLineView.vue";
import SalesBarView from "@/views/nameSalesByMonthView/SalesBarView.vue";
import TestView from "@/views/TestView.vue";
import SalesTopByTimeView from "@/views/nameSalesByMonthView/SalesTopByTimeView.vue";
import SalesByBrandAndTimeView from "@/views/monthSalesByNameYear/SalesByBrandAndTimeView.vue";
import BrandSaleByNameMonthView from "@/views/brandSaleByNameMonth/BrandSaleByNameMonthView.vue";
import BrandSaleByMonthView from "@/views/brandSaleByMonth/BrandSaleByMonthView.vue";
import PricePredictView from "@/views/predict/PricePredictView.vue";


Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },

  {
    path: '/register',
    name: 'register',
    component: RegisterView
  },
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    children: [ // 子路由
      {
        path: 'admin',
        name: 'admin',
        component: AdminView
      },
      {
        path: 'newCar',
        name: 'newCar',
        component: NewCarView
      },
      {
        path: 'usedCar',
        name: 'usedCar',
        component: UsedCarView
      },
      {
        path: 'sales',
        name: 'sales',
        component: SalesView
      },
      {
        path: 'salesBin',
        name: 'salesBin',
        component: SalesBinView
      },
      {
        path: 'salesBar',
        name: 'salesBar',
        component: SalesBarView
      },
      {
        path: 'salesLine',
        name: 'salesLine',
        component: SalesLineView
      },
      {
        path: 'bin',
        name: 'bin',
        component: BinView
      },
      {
        path: 'line',
        name: 'line',
        component: LineView
      },
      {
        path: 'bar',
        name: 'bar',
        component: BarView
      },
      {
        path: 'carBrand',
        name: 'carBrand',
        component: CarBrandView
      },
      {
        path: 'salesTopByTime',
        name: 'salesTopByTime',
        component: SalesTopByTimeView
      },
      {
        path: 'salesByBrandAndTime',
        name: 'salesByBrandAndTime',
        component: SalesByBrandAndTimeView
      },
      {
        path: 'brandSaleByNameMonth',
        name: 'brandSaleByNameMonth',
        component: BrandSaleByNameMonthView
      },
      {
        path: 'brandSaleByMonth',
        name: 'brandSaleByMonth',
        component: BrandSaleByMonthView
      },
      {
        path: 'pricePredict',
        name: 'pricePredict',
        component: PricePredictView
      },
      {
        path: 'test',
        name: 'test',
        component: TestView,
      },
    ]
  },

]

const router = new VueRouter({
  routes
})

// 路由守卫
router.beforeEach((to, from, next) => {
  const user = localStorage.getItem("user");
  if (!user && to.path !== '/login' && to.path !== '/register') { // 添加了 '/register' 到排除列表
    return next("/login");
  }
  next();
})
export default router
