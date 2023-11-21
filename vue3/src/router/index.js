// router.js

import { createRouter, createWebHistory } from 'vue-router';
import adminHome from '../views/adminHome.vue';
import Login from '../views/Login.vue';
import demo from '../views/demo.vue';

const routes = [
    {
        path: '/',
        redirect: '/Login'
    },
    {
        path: '/Login',
        name: 'Login',
        component: Login
    },
    {
        path: '/demo',
        name: 'demo',
        component: demo
    },
    {
        path: '/adminHome',
        name: 'adminHome',
        component: adminHome
    },

];
const router = createRouter({
    //history模式：createWebHistory , hash模式：createWebHashHistory
    history: createWebHistory(process.env.BASE_URL),
    routes
})
export default router
