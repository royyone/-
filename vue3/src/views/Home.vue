<template>
  <!-- <Header></Header> -->
   <el-button plain @click="downloadTemplate">下载模板</el-button>
   <div class="uploadFile">
    <input type="file" ref="fileInput" multiple @change="handleFileSelect">
    <!-- <el-upload
      v-model:file-list="fileList"
      class="upload-demo"
      action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"
      multiple
      :on-preview="handlePreview"
      :on-remove="handleRemove"
      :before-remove="beforeRemove"
      :limit="3"
      :on-exceed="handleExceed"
    >
      <el-button type="primary">上传文件</el-button>
      <template #tip>
        <div class="el-upload__tip">
          .xlsx文件
        </div>
      </template>
    </el-upload> -->
    <el-button plain @click="uploadFiles">上传文件</el-button>
  </div>

  <el-table 
      :data="tableData" 
      stripe 
      style="width: 100%" height="650px"
      @selection-change="handleSelectionChange"
  >
    <!-- <template slot-scope="scope"> -->
      <el-table-column type="selection" prop="isSelect" width="55" />
      <el-table-column prop="college_name" label="学院" width="200" />
      <el-table-column prop="stu_name" label="姓名" width="300" />
      <el-table-column prop="award" label="奖项" width="200" />
      <el-table-column prop="adviser" label="指导老师" width="200" />
      <!-- <el-table-column prop="team_name" label="队伍名称" width="300" /> -->
      <el-table-column prop="status" label="状态" width="100">
          <template #default="{row,$index}">
              <span v-if="row.status === 0">未生成</span>
              <span v-else-if="row.status === 1">未审核</span>
              <span v-else>已审核</span>
          </template>
      </el-table-column>
      <el-table-column label="" width="150" >
        <template #default="{row,$index}">
          <el-button plain @click="preview(row.file_path)">预览</el-button>
        </template></el-table-column>
  </el-table>
  <el-button plain @click="certificateCreate1()">生成奖状</el-button>
  <el-button plain @click="certificateCreate2()">审核盖章</el-button>
  <el-button plain @click="downloadFile()">下载</el-button>


</template>

<script>
import Header from '@/components/Header.vue';
import request from '@/services/request';
export default {
  components: {
    Header
  },
  data() {
    return {
      files: '',
      tableData: [],
      selectData: []
    };
  },
  created() {
    this.update();
  },
  methods: {
    preview(url) {
      // console.log(url);
      if(url === '') {
        alert("未审核！！！");
        return ;
      } 
      window.open('http://localhost:8081/fileController/preview/'+url);

    },
    downloadFile() {
      if(this.selectData.every(item => { 
        console.log(item.status !== 0);
        return item.status === 2;  
      })) {
          this.selectData.forEach(item => {
              console.log(item);
              window.open('http://localhost:8081/fileController/downloadFile/'+item.file_path);
          })
      }
      else {
          alert("存在未审核盖章的学生！！");
      }
    },
    downloadTemplate() {
      window.open('http://localhost:8081/fileController/downloadTemplate');

    },
    handleFileSelect(event) {
      this.files = event.target.files[0];
    },
    uploadFiles() {
      const URL = '/fileController/fileHandle';
      const formData = new FormData();
      formData.append('excelFile', this.files);
      let data = {"game_id": localStorage.getItem("game_id")};
      formData.append('certificate', JSON.stringify(data));
      console.log(typeof(JSON.stringify(data)));
      request
        .post(URL, formData)
        .then(({data}) => {
          // 处理响应
          console.log(data);
          if(data.code==200) {
            this.update();
          }
          else {
            alert("该比赛已经导入过数据，无法再次导入");
          }
          
        })
        .catch((error) => {
          // 处理错误
        });
    },
    async update() {
      const URL = '/awardController/awardSelect';
      await request
        .post(URL, {
          game_id: localStorage.getItem("game_id")
        })
        .then(({data}) => {
          // 处理响应
          console.log("----!!!---")
          console.log(data);
          console.log(localStorage.getItem("game_id"));
          this.tableData = data.data;
          this.tableData.forEach(item => {
            return item.isSelect = false;
          })
          console.log(this.tableData);
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
      this.selectData.forEach(async item => {
        if(((URL.charAt(URL.length - 1) == '1') && (item.status==0)) || ((URL.charAt(URL.length - 1) == '2') && (item.status==1)) ) {/////////////////////////////////
          await request
          .post(URL, item)
          .then(res => {
            console.log(res.data);
          })
          .catch(error => {
            alert("bug! certificateCreate 请联系管理员");
            console.log(error);
          })
        }
        console.log(item);
        this.update();
      })
    },
      handleSelectionChange(value) {
          this.selectData = value;
          // console.log(this.selectData);
      },
  },
  
}
</script>