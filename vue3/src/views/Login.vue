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

export default {
  
  data() {
    return {
      username: '',
      password: ''
    }
    
  },
  methods: {
    login() {
      console.log("111");
      let URL = '/loginController/loginCheck';
      axios
      .post(URL, {
        'username': this.username,
        'password': this.password
      })
      .then( res => {
        console.log(res.data);
        this.$router.push('/Home')
      })
      .catch(error => {
        console.log('error!')
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