<template>
  <div class="login-wrap">
      <!--输入框-->
      <!-- <el-button @click="test(username)"> 测试</el-button> -->

      <div class="form-wrapper">
        <div class="header">
          证书下载平台
        </div>
        <form @submit.prevent="login" class="login-form">
        <div class="input-wrapper">
          <div class="border-wrapper">
            <input type="text" v-model="username" placeholder="用户名" class="border-item" autocomplete="off" />
          </div>
          <div class="border-wrapper">
            <input type="password" v-model="password" placeholder="密码" class="border-item" autocomplete="off" />
          </div>
          <div class = "action">
            <el-button plain class="btn" @click="login">登录</el-button>
          </div>
        </div>
        </form>
      </div>
    </div>
    
      
     
      
    
</template>

<script>
import {ref} from 'vue'; 
import axios from 'axios';
import emitter from '@/utils/emitter'
export default {
  data() {
    return {
      username: '',
      password: '',
      isadmin: 0,
      truth_name: ''
    }
    
  },
  setup() {
    ;
    const test = (username) => {
      console.log("test被执行");
      console.log("..");
      
    };
    return {
      test
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
          localStorage.setItem("token", data.data["token"]);
          // emitter.emit('sendIsAdmin', data.data["isadmin"]);
          localStorage.setItem("isadmin", data.data["isadmin"]);
          // console.log(typeof(data.data["isadmin"]));
          // emitter.emit('sendTruth_Name', data.data["truth_name"]); 
          localStorage.setItem("truth_name", data.data["truth_name"]); 

          // emitter.on('se 
          if( data.data["isadmin"] === 1 ) this.$router.push('/First');
          else this.$router.push('/StuHome');
        }
        else {
          //todo 密码错误交互;
          // alert("登录失败，请检查用户名和密码");
          console.log(data.msg);
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

.login-form {
  display: flex;
  flex-direction: column;
}

.login-wrap {
    height: 820px;
    font-family: JetBrains Mono Medium;
    display: flex;
    align-items: center;
    justify-content: center;
    /* background-color: #0e92b3; */
    background: url('../assets/background.jpg');
    background-size: 100% 100%;
  }
   
  .form-wrapper {
    width: 300px;
    background-color: rgba(41, 45, 62, 0.8);
    color: #fff;
    border-radius: 2px;
    padding: 50px;
  }
   
  .form-wrapper .header {
    text-align: center;
    font-size: 35px;
    text-transform: uppercase;
    line-height: 100px;
  }
   
  .form-wrapper .input-wrapper input {
    background-color: rgb(41, 45, 62);
    border: 0;
    width: 100%;
    text-align: center;
    font-size: 15px;
    color: #fff;
    outline: none;
  }
   
  .form-wrapper .input-wrapper input::placeholder {
    text-transform: uppercase;
  }
   
  .form-wrapper .input-wrapper .border-wrapper {
    background-image: linear-gradient(to right, #e8198b, #0eb4dd);
    width: 100%;
    height: 50px;
    margin-bottom: 20px;
    border-radius: 30px;
    display: flex;
    align-items: center;
    justify-content: center;
  }
   
  .form-wrapper .input-wrapper .border-wrapper .border-item {
    height: calc(100% - 4px);
    width: calc(100% - 4px);
    border-radius: 30px;
  }
   
  .form-wrapper .action {
    display: flex;
    justify-content: center;
  }
   
  .form-wrapper .action .btn {
    width: 60%;
    text-transform: uppercase;
    border: 2px solid #0e92b3;
    text-align: center;
    line-height: 50px;
    border-radius: 30px;
    cursor: pointer;
  }
   
  .form-wrapper .action .btn:hover {
    background-image: linear-gradient(120deg, #84fab0 0%, #8fd3f4 100%);
  }
   
  .form-wrapper .icon-wrapper {
    text-align: center;
    width: 60%;
    margin: 0 auto;
    margin-top: 20px;
    border-top: 1px dashed rgb(146, 146, 146);
    padding: 20px;
  }
   
  .form-wrapper .icon-wrapper i {
    font-size: 20px;
    color: rgb(187, 187, 187);
    cursor: pointer;
    border: 1px solid #fff;
    padding: 5px;
    border-radius: 20px;
  }
   
  .form-wrapper .icon-wrapper i:hover {
    background-color: #0e92b3;
  }
</style>