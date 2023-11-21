<template>
    <div class="login-container">
      <h2>登录</h2>
      <form @submit.prevent="login">
        <div class="form-group">
          <label for="username">用户名:</label>
          <input type="text" v-model="username" placeholder="用户名">
        </div>
        <div class="form-group">
          <label for="password">密码:</label>
          <input type="password" v-model="password" placeholder="密码">
        </div>
        <div class="form-group">
          <button @click="login">登录</button>
        </div>
      </form>
    </div>
  </template>
  
  <script>

  import axios from 'axios';
  export default {
    data () {
      return {
        username: '',
        password: ''
      };
    },

    methods: {
      login() {
        // this.username 和 this.password 获取表单输入的值
        // let param = new URLSearchParams()
        // param.append('username', this.username)
        // param.append('password', this.password)
      // 发送 POST 请求
        axios
        .post('/loginController/loginCheck', {
          username: this.username,
          password: this.password
        }) /// 接口
        .then(res => {
          // todo 根据教师端还是学生端生成不同页面
          console.log(res.data);
          if(res.data.message=='登录成功') {
            this.$router.push('/adminHome');  
          }
          else {
            alert('账号或密码错误');
          }
          
        })
        .catch(error => {
          console.error(error.message);
        });
      }
    }
  };
  </script>
  
  <style>
  .login-container {
    max-width: 400px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 4px;
    padding-top: 10px;
  }
  
  .form-group {
    margin-bottom: 10px;
  }
  
  label {
    display: block;
  }
  
  input[type="text"],
  input[type="password"] {
    width: 100%;
    padding: 5px;
  }
  </style>