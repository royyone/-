<template>

    <el-table :data="tableData" style="width: 100%" height="750">
      <!-- <template slot-scope="scope"> -->
        <el-table-column fixed label="" width="100" ></el-table-column>
        <el-table-column prop="game_type" label="类别" width="120" />
        <el-table-column prop="game_level" label="级别" width="120" />
        <el-table-column prop="game_date" label="时间" width="120" />
        <el-table-column prop="game_name" label="名称" width="320" />
        <el-table-column prop="game_author" label="创建人" width="300" />
        <el-table-column prop="status" label="状态" width="120" />
        <el-table-column prop="award" label="奖项" width="120" />
        <el-table-column label="" width="150" >
          <template #default="{row,$index}">
            <el-button plain @click="preview(row.file_path)">预览</el-button>
          </template></el-table-column>
        <el-table-column label="" width="150" >
          <template #default="{row,$index}">
            <el-button plain @click="downloadFile(row.file_path)">下载</el-button>
          </template>
        </el-table-column>
      <!-- </template> -->
      
    </el-table>
    
</template>
  
<script>
import request from '@/services/request';
export default {  
  data() {
    return {
      tableData: [
        ]
    };
  },
  created() {  
      this.Update();
    },
  methods: { 
    //调取数据库获得学生比赛获奖数据
    Update() {
      const URL = '/awardController/stuSelect';
      request
        .post(URL, {
            stu_name: sessionStorage.getItem("truth_name")
        })
        .then( ({ data }) => {
          console.log(data);
          if(data.code == 200) {
            this.tableData = data.data;
          }
          else if(data.code == 401) {
            this.$router.push('/');
          }
          console.log(this.tableData);
        })
        .catch((error) => {
            console.log(error);
          alert("stuHome BUG! 请联系管理员");
        });
    },
    // 
    preview(url) {
      console.log(url);
      window.open('http://localhost:8081/fileController/preview/'+url);
    },
    downloadFile(url) {
        window.open('http://localhost:8081/fileController/downloadFile/'+url);
    },
  }
};

  
</script>
<style scoped>
.demo-form-inline .el-input {
  --el-input-width: 420px;
}

.demo-form-inline .el-select {
  --el-select-width: 220px;
}
</style>