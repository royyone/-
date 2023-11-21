<template>
  <ctHeader></ctHeader>
  <div class = 'fileChoice'>
      <input type="file" ref="fileInput" @change="fileChange" accept=".xlsx">
      <button @click="fileHandle">上传</button>
  </div>
  <!-- <table>
      <thead><tr>
        <th></th>
        <th>编号</th>
        <th>学院</th>
        <th>队伍名</th>
        <th>姓名</th>
        <th>奖项</th>
        <th>说明</th>
        <th>状态</th>
        <th></th>
      </tr></thead>
  </table> -->
  <div class = "tableBox">
    <table>
      <thead style="position: sticky; top: 0;"><tr>
        <th></th>
        <th>编号</th>
        <th>学院</th>
        <th>队伍名</th>
        <th>姓名</th>
        <th>奖项</th>
        <th>说明</th>
        <th>状态</th>
        <th></th>
      </tr></thead>
      <tbody>
        <tr v-for="(item, index) in testdata" :key="item.id">
          <input type="checkbox" v-model="item.fla" name = 'same' :value="item.fla">
          <td>{{index+1}}</td>
          <td>{{item.collegeName}}</td>
          <td>{{item.teamName}}</td>
          <td>{{item.stuName}}</td>
          <td>{{item.rkName}}</td>
          <td>{{item.explainText}}</td>
          <td>{{item.status}}</td>
          <td><a :href="getPath(index)" style="text-decoration: none;">预览</a></td>

        </tr>
      </tbody>
    </table>
    <div class="addBtn">
        <input type="checkbox" v-model="isAll"> 全选
        <button @click="certificateCreate">奖状生成</button>
        <button @click="certificateCreate">盖章</button>
    </div>
    <div>
      <button @click = "Test">test</button>
    </div>
  </div>
</template>

<script>
import ctHeader from './ctHeader.vue';
import axios from 'axios';
export default {
  created() {
    this.update();
  },
  data() {
    return {
      selectedFile: '',
      testdata: [
        {
          id: 1,
          match_name: '',
          college_name: '信息科学与技术学院',
          team_name: 'Acm实验室',
          stu_name: '潘志程 潘志程 潘志程',
          adviser: '',
          award: '金奖',
          explainText: '',
          status: '未审核',
          ctPath: '',
          fla: false
        },
        {
          id: 2,
          collegeName: '信息科学与技术学院',
          teamName: 'Acm实验室',
          stuName: '潘志程 潘志程 潘志程',
          rkName: '金奖',
          explainText: '',
          status: '未审核',
          ctPath: '',
          fla: false
        }
      ]
    };
    
  },
  computed: {
    isAll: {
      get() {
        return this.testdata.every(item => item.fla);
      },
      set(value) {
        this.testdata.forEach(item => item.fla = value);
      }
    }
  },  
  methods: {
    fileChange(event) {
      // console.log('执行');
      this.selectedFile = event.target.files[0];
    },
    fileHandle() {
      let URL = 'http://localhost:8081/fileController/fileHandle';
      let formdata = new FormData()
      formdata.append('excelFile', this.selectedFile)
      axios
        .post(URL, formdata)
        .then(res => {
          this.update();
        })
        .catch(error => {
          alert('上传失败');
          console.error(error.message);
        });
      
    },
    getPath(index) {
      // todo 获取pdf奖状查看
      return "https://www.baidu.com";
    },
    update() {
      // 获取新的数据
      let URL = 'http://localhost:8081/awardController/awardSelect';
      axios
      .post(URL,{})
      .then(res => {
        this.testdata = res.data.data;
        console.log('渲染');
        console.log(this.testdata);
      })
      .catch(error => {
        alert(error.message+'出错啦! update');
      })
    },
    certificateCreate() {
      // todo 生成奖状
      if(this.checkCreate()) return ;
      console.log('test149');
      let URL = 'http://localhost:8081/fileController/certificateCreate';
      this.testdata.forEach(item => {
        console.log(item);
        // let param = new URLSearchParams()
        // param.append('id', item.id);
        // param.append('match_name', item.match_name);
        // param.append('college_name', item.college_name);
        // param.append('stu_name', item.stu_name);
        // param.append('team_name', item.team_name);
        // param.append('adviser', item.adviser);
        // param.append('award', item.award);
        // param.append('status', item.status);
          axios
          .post(URL,{
              id: item.id,
              match_name: item.match_name,
              college_name: item.college_name,
              stu_name: item.stu_name,
              team_name: item.team_name,
              adviser: item.adviser,
              award: item.award,
              status: item.status,
          })
          .then(res => {
            alert('成功');
          })
          .catch(error => {
            console.log(item);
            alert('创建pdf失败');
            console.log(error.message);
          })
      });
      this.update();
    },
    checkCreate() {
      // 检查是否有未审核就盖章或者重复审核的情况；
      return this.testdata.every(item => {
        item.fla && item.status === '未审核' || !item.fla && item.status === '未盖章'
      })
    }
  },
  components: {
    ctHeader
  }
}
</script>

<style lang = 'scss'>
.fileChoice{
  background-color: aqua;
}
.tableBox{
  margin-top: 100px;
  margin-left: 10%;
  margin-right: 10%;
  width: 80%;
  height: 500px;
  overflow: auto;
  // background-color: red;
  border: 2px solid #0483d4;
  border-collapse: collapse;
  table {
    // border: 2px solid #0483d4;
    border-collapse: collapse;
    width: 100%;
  }
  th, td, input{
    border: 1px solid #0483d4;
    text-align: left;
    padding: 8px;
  }
  th {
    background-color: #f2f2f2;
  }
  tr:nth-child(even) {
    background-color: #f2f2f2;
  }
}
.addBtn{
  background-color: red;
}

</style>