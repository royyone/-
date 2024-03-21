import axios from "axios";
 
// import {getCurrentInstance} from "vue";
// const { proxy } = getCurrentInstance();
// 创建 axios 实例
const request = axios.create({
    baseURL: process.env.VUE_APP_BASEURL, // 设置基础URL
    timeout: 5000, // 设置超时时间
  });
 
// 请求拦截器
request.interceptors.request.use(
    config => {
      // 在请求发送之前做一些处理，例如添加 token 等
      const token = localStorage.getItem("token");
      config.headers.Authorization = `Bearer ${token}`;
      // console.log(token);
      // console.log('请求拦截前');
      // console.log(config);
      return config;
    },
    error => {
      // 请求错误处理
      // console.log('请求拦截前 BUG');
      return Promise.reject(error);
    }
  );
  
// 响应拦截器
request.interceptors.response.use(
  response => {
    // 在这里对响应进行处理，例如统一处理错误码等
    // if (response.data.code !== '200') {
    //   return Promise.reject(new Error(response.data.message));
    // }
    // console.log("response:");
    // console.log(response);
    return response;
  },
  error => {
    // 响应错误处理
    if (error.response.status === 401) { // 假设401表示token过期或不合法
      // 这里处理token过期或不合法的逻辑
      // 可以是重新登录，刷新token，跳转登录页等
      console.log("响应错误，token 401");
    }
    // console.log("响应错误！");
    console.log("响应错误！");
    return Promise.reject(error);
  }
);
  
// 交出去，共这个项目中的其他组件使用
export default request;