<template>
  <div class="login-container">
    <h2 class="login-title">Login</h2>
    <form @submit.prevent="login" class="login-form">
      <div class="form-group">
        <label for="username" class="form-label">用户名:<br></label>
        <input type="text" id="username" v-model="username" class="form-input" />
      </div>
      <div class="form-group">
        <label for="password" class="form-label">密码:<br></label>
        <input type="password" id="password" v-model="password" class="form-input" />
      </div>
      <button type="submit" class="login-button">Login</button>
    </form>
  </div>
  <div id="app">
    <el-button type="success">Success</el-button>
  </div>
</template>

<script>
import axios from 'axios';
import request from '../services/request.js';

export default {
  
  data() {
    return {
      username: '',
      password: '',
      isadmin: 0,
      truth_name: ''
    }
    
  },
  methods: {
    login() {
      // console.log("111");
      let URL = 'http://localhost:8081/loginController/loginCheck';
      axios
      .post(URL, {
        'user_name': this.username,
        'user_pwd': this.password
      })
      .then( ({data}) => {
        if( data.code == 200 ) {
          console.log(data.data);
          sessionStorage.setItem("token", data.data["token"]);
          sessionStorage.setItem("isadmin", data.data["isadmin"]); 
          sessionStorage.setItem("truth_name", data.data["truth_name"]); 
          if(sessionStorage.getItem("isadmin") === "1") this.$router.push('/First')
          else this.$router.push('/StuHome');
        }
        else {
          //todo 密码错误交互;
        }
      })
      .catch(error => {
        console.log(error);
        alert("Login BUG! 请联系管理员");
      });
    }
  }
}
</script>

<style lang="scss" scoped>
.login-container {
  max-width: 300px;
  margin: 0 auto;
  padding: 20px;
  background-color: #f0f0f0;
  border-radius: 4px;
}

.login-title {
  text-align: center;
  margin-bottom: 20px;
}

.login-form {
  display: flex;
  flex-direction: column;
}

.form-group {
  margin-bottom: 15px;
}

.form-label {
  font-weight: bold;
}

.form-input {
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.login-button {
  padding: 8px 15px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.login-button:hover {
  background-color: #0056b3;
}
</style>