<template>
    <div class="container">
      <div class="imgWrap" id="slider">
        <img class="mainbg" src="https://authserver.hznu.edu.cn/authserver/cumt/static/web/images/bg2.jpg" alt="">
      </div>
      <div class="login_container">
        <div class="item">
          <div class="active" @click="$router.push('/login')">登录</div>
          <div @click="$router.push('/login')">注册</div>
        </div>
        <div class="login_content">
          <div>
            <form :id="login_id" :name="login_name">
              <div class="number_item">
                <span class="prex-icon"><img
                    src="https://authserver.hznu.edu.cn/authserver/cumt/static/web/images/user1.png"
                    class="login-icon"></span>
                <input type="text" placeholder="请输入用户账号" v-model="username" @blur="checkUserNum()" />
              </div>
              <div class="password_item">
                <span class="prex-icon"><img
                    src="https://authserver.hznu.edu.cn/authserver/cumt/static/web/images/pass1.png"
                    class="login-icon"></span>
                <input type="password" placeholder="请输入密码" v-model="password" @blur="checkPwdLen()"
                  @keydown.enter="login()" />
              </div>
              <span :class="errors">{{ loginError }}</span>
              <input type="button" value="登录" :id="btnLogin" @click="login()" />
            </form>
          </div>
        </div>
      </div>
    </div>
    <!-- 32-56行邮箱注册账号，暂时没啥用 -->
    <!-- <el-dialog :model-value="VisibleRegitserDialog" title="邮箱登录" @close="RegisterDialogClose" :before-close="RegisterDialogClose"> -->
        <!-- <el-form 
        :label-position="labelPosition" 
        label-width="auto"
        style="max-width: 500px"   
         class="">
            <el-form-item label="邮箱">
                <el-input v-model="Email" placeholder="" clearable />
            </el-form-item>
            <el-form-item label="密码">
                <el-input v-model="pwd1" placeholder="" clearable />
            </el-form-item>
            
            <el-row :gutter="20">
                <el-col :span="10" style="margin-left: 40px;">
                    <el-input v-model="EmailCode" placeholder="" clearable />
                </el-col>
                <el-col :span="6">
                    <el-button type="primary" @click="EmailSend()">发送验证码</el-button>
                </el-col>
            </el-row>

            <el-button type="primary" @click="EmailLogin()">邮箱登录</el-button>
        </el-form> -->
    <!-- </el-dialog> -->
</template>
    


<script>

</script>

<script setup lang = "js">
    import CryptoJS from 'crypto-js';
    import {ref} from 'vue'; 
    import axios from 'axios';
    import emitter from '@/utils/emitter';
    import {useRouter} from'vue-router';
import { ROOT_TREE_INJECTION_KEY } from 'element-plus/es/components/tree-v2/src/virtual-tree';
    const router = useRouter();

    // 数据
    let username = ref();
    let password = ref();
    let errorVisble = ref();
    let errorMsg = ref();
    let VisibleRegitserDialog = ref(false);
    let loginError = ref();
    //
    let Email = ref();
    let EmailCode = ref();
    let pwd1 = ref();
    const labelPosition = ref('right');
    // 登录请求
    const login = () => {
        let URL = process.env.VUE_APP_BASEURL + "loginController/loginCheck";
        axios
        .post(URL, {
            'user_name': username.value,
            'user_pwd': HashPassword(password.value)
            // 'user_pwd': password.value
        })
        .then( ({data}) => {
            /**
             * 如果登录验证成功，本地存下token
             */
            if( data.code == 200 ) {
                console.log(data.data);
                localStorage.setItem("token", data.data["token"]);
                // emitter.emit('sendIsAdmin', data.data["isadmin"]);
                localStorage.setItem("isadmin", data.data["isadmin"]);
                // console.log(typeof(data.data["isadmin"]));
                // emitter.emit('sendTruth_Name', data.data["truth_name"]); 
                localStorage.setItem("truth_name", data.data["truth_name"]); 
    
                /**
                 * 这里根据身份，跳转对应的界面
                 */
                if( data.data["isadmin"] === 0 ) {
                    // router.push("/FileProcessing");
                    router.push('/StuHome');
                }
                else {
                    // router.push("/FileProcessing");
                    router.push('/First');
                }
            }
            else {
                errorVisble.value = true;
                errorMsg.value = data.msg; 
                // alert("登录失败，请检查用户名和密码");
                console.log(data.msg);
            }
        })
        .catch(error => {
            console.log(error);
            alert("Login BUG! 请联系管理员");
        });
        // 哈希加密密码
    }
    // 密码加密
    const HashPassword = (input) => {
        console.log(CryptoJS.SHA256(input).toString());
        return CryptoJS.SHA256(input).toString();
    }
    const checkPwdLen = () => {
        let Len = password.value.length;
        loginError.value = '';
        if (Len < 6 || Len <= 0 || Len > 20) {
          loginError.value = '用户密码不合法';
          return false;
        }
        return true;
    }
    const checkUserNum = () => {
        let Len = username.value.length;
        loginError.value = '';
        if (Len <= 0 || Len > 15) {
          loginError.value = '用户账号不合法';
          return false;
        }
        return true;
      }
    // 打开用户注册的弹窗
    // const RegisterDialogOpen = () => {
    //     VisibleRegitserDialog.value = true;
    // };
    // const RegisterDialogClose = () => {
    //     VisibleRegitserDialog.value = false;
    // };
    // 发送email
    // const EmailSend = () => {
    //     let URL = process.env.VUE_APP_BASEURL + "loginController/EmailSend";
    //     axios
    //     .post(URL, {
    //         'email': Email.value,
    //     })
    //     .then( ({data}) => {
    //         ;
    //     })
    //     .catch( error => {
    //         alert("EmailSend BUG!");
    //     });
    // }
    // const EmailLogin = () => {
    //     let URL = process.env.VUE_APP_BASEURL + "loginController/EmailLogin";
    //     axios
    //     .post(URL, {
    //         'user_name': Email.value,
    //         'user_pwd': HashPassword(pwd1.value),
    //         'code': EmailCode.value  
    //     })
    //     .then( ({data}) => {
    //         if(data.code == 200) 
    //         {
    //             router.push("/FileProcessing");
    //         };
    //     })
    //     .catch( error => {
    //         alert("EmailSend BUG!");
    //     });;
    // }
</script>

    
<style scoped>
* {
  margin: 0;
  padding: 0;
}

.imgWrap {
  width: 100%;
  height: 100%;
  position: absolute;
  left: 0;
  top: 0;
  z-index: 0;
}

.imgWrap img {
  width: 100%;
  height: 100%;
}

.login_container {
  width: 450px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  border-radius: 8px;
  background: rgba(0, 0, 0, 0.7);
  min-height: 387px;
}

.number_item {
  margin-bottom: 15px;
  position: relative;
  width: 100%;
  text-align: left;
  border-radius: 4px;
  max-height: 40px;
}

.password_item {
  margin-bottom: 15px;
  position: relative;
  width: 100%;
  text-align: left;
  border-radius: 4px;
  max-height: 40px;
}

.prex-icon img {
  width: 25px;
}

.item {
  margin: 0 auto;
  width: 350px;
  font-size: 16px;
  color: #ffffff;
  vertical-align: bottom;
  border-bottom: 0.5px solid #fff;
}

.item div {
  width: 175px;
  height: 60px;
  display: inline-block;
  font-size: 18px;
  text-align: center;
  line-height: 60px;
  cursor: pointer;
}

.active {
  border-bottom: 1.5px solid #fff;
}

.login_content {
  width: 100%;
}

.login_content div {
  margin-left: 20px;
  margin-right: 20px;
  margin-top: 30px;
  text-align: left;
}

.login_content input[type="text"],
.login_content input[type="password"] {
  width: 78%;
  height: 40px;
  line-height: 40px;
  font-family: "Microsoft Yahei";
  margin-left: 10px;
  border: 0;
  font-size: 14px;
  color: #bebdbd;
  outline: none;
  background: none;
  border-bottom: 1px solid #cdcdcd;
}

.login_content input[type="button"] {
  margin-top: 30px;
  width: 100%;
  height: 50px;
  border-radius: 4px;
  color: white;
  font-size: 20px;
  background: #002954;
  cursor: pointer;
  letter-spacing: 4px;
}

.errors {
  color: red;
  font-size: 14px;
}
</style>
  