<template>
    <el-row><el-button round @click="Create">创建</el-button></el-row>
    <el-table :data="tableData" style="width: 100%" height="750">
      <!-- <template slot-scope="scope"> -->
        <el-table-column fixed label="" width="150" >
          <template #default="{row,$index}">
            <el-button plain @click="detail(row.game_id)">查看</el-button>
          </template></el-table-column>
        <el-table-column prop="game_type" label="类别" width="120" />
        <el-table-column prop="game_level" label="级别" width="120" />
        <el-table-column prop="game_date" label="时间" width="120" />
        <el-table-column prop="game_name" label="名称" width="320" />
        <el-table-column prop="game_author" label="创建人" width="300" />
        <el-table-column prop="game_state" label="状态" width="120" />
        <el-table-column label="" width="150" >
          <template #default="{row,$index}">
            <el-button plain @click="Delete(row.game_id)">删除</el-button>
          </template>
        </el-table-column>
      <!-- </template> -->
      
    </el-table>
    <!-- 创建比赛的弹窗 -->
    <el-dialog :model-value="visible" title="比赛创建" @close="handleClose" :before-close="handleClose">

    <el-form :inline="true" :model="formData" class="demo-form-inline">
        
        <el-form-item label="类别">
          <el-select
            v-model="formData.game_type"
            placeholder="一类"
            clearable
          >
            <el-option label="一类" value="一类" />
            <el-option label="二类" value="二类" />
          </el-select>
        </el-form-item>

        <el-form-item label="级别">
          <el-select
            v-model="formData.game_level"
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
            v-model="formData.game_date"
            type="date"
            placeholder="比赛时间"
            clearable
          />
        </el-form-item><br/>
        <el-form-item label="比赛名称">
          <el-input v-model="formData.game_name" placeholder="" clearable />
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
import { reactive, watch } from 'vue'
import request from '@/services/request';
export default {  
  data() {
    return {
      formData: reactive({
        game_name: '',
        game_evel: '',
        game_type: '',
        game_date: '',
      }),
      visible: false,
      tableData: [
        ]
    };
  },
  created() {  
      this.Update();
    },
  methods: { 
    
    // 打开创建比赛的弹窗
    Create() {
      this.visible = true;
    },
    handleClose() {
      this.visible = false;
    },
    // 提交表单增加数据
    submitForm() {
      this.formData.game_author = '戴泽耀';
      if(this.formData.game_type==='') {
        alert("添加失败，类别不可为空");
      }
      else if(this.formData.game_level==='') {
        alert("添加失败，级别不可为空");
      }
      else if(this.formData.game_date==='') {
        alert("添加失败，时间不可为空");
      }
      else if(this.formData.game_name==='') {
        alert("添加失败，名称不可为空");
      }
      else {
        const URL = '/gameController/gameInsert';
        request
          .post(URL, this.formData)
          .then((res) => {
            // 处理响应
            // console.log("添加成功");
            console.log(res.data);
            this.visible = false;
            this.Update();
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
      const URL = '/gameController/gameSelect';
      request
        .post(URL, {})
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
          // 处理错误
          // alert("!!!"); 
          // console.log()
          console.log(error);
        });
    },
    // 删除此条比赛数据
    Delete(id) {
      // console.log("调用Delete(id)");
      const URL = '/gameController/gameDelete';
      request
        .post(URL, {
          game_id: id
        })
        .then(({data}) => {
          console.log(data);
          this.Update();
        })
        .catch((error) => {
          // alert("Delete BUG!!!");
          console.log(error);
        });
    },
    // 进入该条比赛项
    detail(id) {
      sessionStorage.setItem("game_id", id);
      // todo token鉴权
      this.$router.push('/Home');
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