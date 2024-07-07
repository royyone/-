<template>
  <div id="app">
    <header>
      <h1>文件加解密</h1>
      <el-button round @click="trans1">加密文件</el-button>
      <el-button round @click="trans2">解密文件</el-button>
    </header>


    <main>
      
      <div v-if="visiable"> 
        <input type="file" ref="fileInput" @change="handleFileSelect">
        <label > 口令 </label>
        <input type="password" v-model="pwdInput">
        <br/>
        <br/>
        <el-button type="primary" @click="enFile">加密</el-button>
      </div>
      <div v-else> 
        <input type="file" ref="fileInput" @change="handleFileSelect">
        <label > 口令 </label>
        <input type="password" v-model="pwdInput">
        <br/>
        <br/>
        <el-button type="primary" @click="deFile">解密</el-button>
      </div>
      
    </main>

    
  </div>
</template>

<script setup lang = "js">
  import { ref } from 'vue'
  import request from '@/services/request';

  let visiable = ref(true);
  let files = ref('');
  let pwdInput = ref();
  const trans1 = ()=> {
    visiable.value = true;
  }
  const trans2 = ()=> {
    visiable.value = false;
  }
  const handleFileSelect = (event) => {
      files.value = event.target.files[0];
  };
  const enFile = ()=> {
    const URL = '/finalworkController/encryptFile';
    const formData = new FormData();
    formData.append('enFile', files.value);
    formData.append('enpwd', pwdInput.value);

    request
      .post(URL, formData)
      .then(({data}) => {
        if(data.code == 200) {
          window.open(process.env.VUE_APP_BASEURL+'finalworkController/download/' + files.value.name);
          alert("文件加密成功");
        }
        else {
            alert("encryptFile BUG! ");
            console.log(data);
        }
        })
        .catch((error) => {
            // 处理错误
            console.log(error);
            alert("encryptFile BUG! 请联系管理员");
        });
  }
  const deFile = ()=> {
    const URL = '/finalworkController/decryptFile';
    const formData = new FormData();
    formData.append('enFile', files.value);
    formData.append('enpwd', pwdInput.value);

    request
      .post(URL, formData)
      .then(({data}) => {
        if(data.code == 200) {
          window.open(process.env.VUE_APP_BASEURL+'finalworkController/download/' + files.value.name);
          alert("文件解密成功");
        }
        else {
            alert("decryptFile BUG! ");
            console.log(data);
        }
        })
        .catch((error) => {
            // 处理错误
            console.log(error);
            alert("decryptFile BUG! 请联系管理员");
        });
  }
</script>

<style scoped>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  text-align: center;
}

header, nav, main, footer {
  padding: 20px;
  margin: 10px;
}
</style>
