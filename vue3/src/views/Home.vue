<template>
  <Header></Header>
   <div class="uploadFile">
    <input type="file" ref="fileInput" multiple @change="handleFileSelect">
    <button @click="uploadFiles">上传文件</button>
  </div>
  <div class="dataTable">
    <table>
    <thead>
      <tr>
        <th></th>
        <th>编号</th>
        <th>学院</th>
        <th>姓名</th>
        <th>奖项</th>
        <th>指导老师</th>
        <th>队伍名称</th>
        <th></th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="(item, index) in data" :key="data.id">
        <td><input type="checkbox" v-model="item.isSelect"></td>
        <td>{{ index+1 }}</td>
        <td>{{ item.college_name }}</td>
        <td>{{ item.stu_name }}</td>
        <td>{{ item.award }}</td>
        <td>{{ item.adviser }}</td>
        <td>{{ item.team_name }}</td>
        <td> 
          <span v-if="item.status===0"> 未审核 </span>
          <span v-else-if="item.status===1"> 未盖章 </span>
          <span v-else> 已盖章 </span>
        </td>
        <td> <a  @click="preview(item.file_path)" >预览</a></td>
      </tr>
    </tbody>
    </table>
  </div>
  <div>
    <input type="checkbox" v-model="isAll"> 全选
    <button @click="certificateCreate1()">生成奖状</button>
    <button @click="certificateCreate2()">审核盖章</button>
    <button @click="downloadFile">下载</button>
  </div>
  <h1>Home!!!</h1>
</template>

<script>
import axios from 'axios';
import Header from '@/components/Header.vue';
export default {
  components: {
    Header
  },
  data() {
    return {
      files: '',
      data: []
    };
  },
  created() {
    this.update();
  },
  computed: {
    isAll: {
      get() {
        return this.data.every(item => item.isSelect)
      },
      set(value) {
        this.data.forEach(item => item.isSelect = value);
      }
    }
  },
  methods: {
    preview(url) {
      console.log(url);
      if(url === '') {
        alert("未审核！！！");
        return ;
      } 
      window.open('http://localhost:8081/fileController/preview/'+url);
    },
    downloadFile() {
      if(!this.data.every(item => { 
        console.log(!item.isSelect ||  item.isSelect === true && item.status !== 0);
        return !item.isSelect ||  item.isSelect === true && item.status !== 0;  
      })) {
        alert("未审核信息无法下载!");
        return ;
      }
      this.data.forEach(item => {
        // const URL = '/fileController/downloadFile/' + item.file_path;
        // const URL = '/fileController/downloadFile/certificate_18.pdf';
        if(item.isSelect === false) return ;
        console.log(item);
        window.open('http://localhost:8081/fileController/downloadFile/'+item.file_path);
        // axios
        //   .get(URL, {
        //     headers: {
        //       'Content-Disposition': 'attachment'
        //     }
        //   })
        //   .then(res => {
        //     // console.log(res.data);
        //   })
        //   .catch(error => {
        //     alert("error => downloadFile");
        //   })
      })
      
    },
    handleFileSelect(event) {
      this.files = event.target.files[0];
    },
    uploadFiles() {
      const URL = '/fileController/fileHandle';
      const formData = new FormData();
      formData.append('excelFile', this.files);
      let data = {"game_id":1};
      formData.append('certificate', JSON.stringify(data));
      console.log(typeof(JSON.stringify(data)));
      axios
        .post(URL, formData)
        .then((res) => {
          // 处理响应
          console.log(res.data);
          this.update();
        })
        .catch((error) => {
          // 处理错误
        });
    },
    update() {
      const URL = '/awardController/awardSelect';
      axios
        .post(URL, {
          game_id: sessionStorage.getItem("game_id")
        })
        .then((res) => {
          // 处理响应
          // console.log(res.data);
          console.log(sessionStorage.getItem("game_id"));
          this.data = res.data.data;
          this.data.forEach(item => {
            return item.isSelect = false;
          })
          console.log(this.data);
        })
        .catch((error) => {
          // 处理错误
          alert("!!!");
          // console.log(error.message);
        });
    },
    certificateCreate1() {
      // console.log("11");
      // alert("A!");
      const URL = '/fileController/certificateCreate1';
      this.certificateCreate(URL);
    },
    certificateCreate2() {
      const URL = '/fileController/certificateCreate2';
      this.certificateCreate(URL);
    },
    certificateCreate(URL) {
      console.log(URL);
      this.data.forEach(async item => {
        if(item.isSelect === false) return ;
        if(URL.charAt(URL.length - 1) != item.status+1) {
          alert('冲突！');
          return ;
        }
        console.log(item);
        await axios
          .post(URL, item)
          .then(res => {
            
            console.log(res.data);
          })
          .catch(error => {
            // alert("bug! certificateCreate");
            console.log(error);
          })
        this.update();
      })
    },
  },
  
}
</script>

<style scoped lang="scss">
.uploadFile {
  margin-bottom: 20px;
}
.table {
  width: 100%;
  border-collapse: collapse;
}

th, td {
  padding: 8px;
  text-align: left;
  border-bottom: 1px solid #ccc;
}

th {
  background-color: #f2f2f2;
}

a {
  text-decoration: none;
  color: blue;
}

a:hover {
  text-decoration: underline;
}

h1 {
  margin-top: 30px;
}
</style>