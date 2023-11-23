<template>
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
        <td> <a href="">预览</a></td>
      </tr>
    </tbody>
    </table>
    <input type="checkbox" v-model="isAll"> 全选
    <button @click="certificateCreate">生成奖状</button>
    <button @click="certificateCreate">审核盖章</button>
  </div>
  <h1>Home!!!</h1>
</template>

<script>
import axios from 'axios';
export default {
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
    handleFileSelect(event) {
      this.files = event.target.files[0];
    },
    uploadFiles() {
      const URL = '/fileController/fileHandle';
      const formData = new FormData();
      formData.append('excelFile', this.files);
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
        .post(URL, {})
        .then((res) => {
          // 处理响应
          console.log(res.data);
          this.data = res.data.data;

        })
        .catch((error) => {
          // 处理错误
          alert("!!!");
          // console.log(error.message);
        });
    }
  },
  certificateCreate() {
    const URL = '/fileController/certificateCreate';
    this.data.forEach(item => {
      if(this.checkSelect(item)) {
        alert('冲突！');
        return ;
      }
      axios
        .post(URL, item)
        .then(res => {
          console.log(res.data.msg);
        })
        .catch(error => {
          alert("bug! certificateCreate");
        })
    })
  },
  checkSelect(item) {
    if(item.status === 1 && item.isSelect) {
      ; // 有bug
    }
  }
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