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
    path: '/Temp',
    name: 'Temp',
    component: () => import("../views/Temp.vue")
  },
  {
    path: '/First',
    name: 'First',
    component: () => import("../views/First.vue")
  },
  {
    path: '/Home',
    name: 'Home',
    component: () => import("../views/Home.vue")
  },
  {
    path: '/Header',
    name: 'Test',
    component: () => import("../components/Header.vue")
  }
];

const router = createRouter({
    routes,
    history: createWebHistory(process.env.BASE_URL),
});

export default router

