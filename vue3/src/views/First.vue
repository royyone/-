<template>
    <el-row><el-button round @click="Create">创建</el-button></el-row>
    <el-table :data="tableData" style="width: 100%" height="250">
      <el-table-column fixed prop="date" label="" width="150" ><el-button plain>查看</el-button></el-table-column>
      <el-table-column prop="Level" label="类别" width="120" />
      <el-table-column prop="Level" label="级别" width="120" />
      <el-table-column prop="Time" label="时间" width="120" />
      <el-table-column prop="Name" label="名称" width="320" />
      <el-table-column prop="Author" label="创建人" width="300" />
      <el-table-column prop="State" label="状态" width="120" />
      <el-table-column label="" width="150" ><el-button plain>删除</el-button></el-table-column>
    </el-table>
    <!-- 创建比赛的弹窗 -->
    <el-dialog :model-value="visible" title="比赛创建" @close="handleClose" :before-close="handleClose">

    <el-form :inline="true" :model="formData" class="demo-form-inline">
        
        <el-form-item label="类别">
          <el-select
            v-model="formData.Type"
            placeholder="一类"
            clearable
          >
            <el-option label="一类" value="一类" />
            <el-option label="二类" value="二类" />
          </el-select>
        </el-form-item>

        <el-form-item label="级别">
          <el-select
            v-model="formData.Level"
            placeholder="校级"
            clearable
          >
            <el-option label="校级" value="校级" />
            <el-option label="省级" value="省级" />
            <el-option label="国家级" value="国家级" />
          </el-select>
        </el-form-item>

       

        <el-form-item label="比赛时间">
          <el-date-picker
            v-model="formData.date"
            type="date"
            placeholder="比赛时间"
            clearable
          />
        </el-form-item><br/>
        <el-form-item label="比赛名称">
          <el-input v-model="formData.gameName" placeholder="" clearable />
        </el-form-item>
      <!-- <el-form-item>
      <el-button type="primary" @click="onSubmit">Query</el-button>
      </el-form-item> -->
  </el-form>
    <el-button type="primary" @click="submitForm()">添加</el-button>
  </el-dialog>
</template>
  
<script>
// import {
//   Check,
//   Delete,
//   Edit,
//   Message,
//   Search,
//   Star,
// } from '@element-plus/icons-vue'
import { reactive } from 'vue'
export default {  
  data() {
    return {
      formData: reactive({
        gameName: '',
        Level: '',
        Type: '',
        date: '',
      }),
      visible: false,
      tableData: [
            {
                date: '2016-05-03',
                Level: '校级',
                Time: '2016-05-03',
                Name: '杭州师范大学程序设计竞赛校赛',
                Author: '潘志程',
                State: '已发布',
            },
        ]
    };
  },
  methods: {
    
    created() {
      this.update();
    },
    // 打开创建比赛的弹窗
    Create() {
      this.visible = true;
    },
    handleClose() {
      this.visible = false;
    },
    // 提交表单增加数据
    submitForm() {
      if(this.formData.Type='') {
        alert("添加失败，类别不可为空");
      }
      else if(this.formData.Level='') {
        alert("添加失败，级别不可为空");
      }
      else if(this.formData.Time='') {
        alert("添加失败，时间不可为空");
      }
      else if(this.formData.gameName='') {
        alert("添加失败，名称不可为空");
      }
      else {
        const URL = '/gameController/gameHandle';
        axios
          .post(URL, this.formData)
          .then((res) => {
            // 处理响应
            console.log("添加成功");
            console.log(res.data);
            this.update();
          })
          .catch((error) => {
            // 处理错误
          });
          alert("添加成功！");
          console.log(this.formData);
      }
    },

    //调取数据库获得已创建比赛数据
    Update() {
      
    },
    // 删除此条比赛数据
    Delete() {

    },
    // 进入该条比赛项
    Option() {

    }
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