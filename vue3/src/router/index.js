// import { createRouter, createWebHashHistory } from "vue-router";
import { createRouter, createWebHistory } from 'vue-router'


const routes = [
  {
    path: "/",
    redirect:"/Login"
  },
  {
    path: '/Login',
    name: 'Login',
    component: () => import("../views/Login.vue")
  },
  {
    path: '/First',
    name: 'First',
    component: () => import("../views/First.vue"),
    beforeEnter: (to,from,next) => {
      
        // 释义：进入主页后，若下一个点击的路由不是Login，并且已登录变量为false，
        // 则扭送 /login
        if (to.name !== 'Login' && !localStorage.getItem("token")) next('/Login');
        else next();
    } 
  },
  {
    path: '/Home',
    name: 'Home',
    component: () => import("../views/Home.vue"),
    beforeEnter: (to,from,next) => {
      
      // 释义：进入主页后，若下一个点击的路由不是Login，并且已登录变量为false，
      // 则扭送 /login
      if (to.name !== 'Login' && !localStorage.getItem("token")) next('/Login');
      else next();
    } 
  },
  {
    path: '/StuHome',
    name: 'StuHome',
    component: () => import("../views/StuHome.vue"),
    beforeEnter: (to,from,next) => {
      
      // 释义：进入主页后，若下一个点击的路由不是Login，并且已登录变量为false，
      // 则扭送 /login
      if (to.name !== 'Login' && !localStorage.getItem("token")) next('/Login');
      else next();
    } 
  },
  {
    path: '/Header',
    name: 'Test',
    component: () => import("../components/Header.vue")
  },
  {
    path: '/Temp',
    name: 'Temp',
    component: () => import("../views/Temp.vue"),
  }
];

const router = createRouter({
    routes,
    history: createWebHistory(process.env.BASE_URL),
});


export default router

