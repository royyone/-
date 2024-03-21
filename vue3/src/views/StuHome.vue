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
            </template>
        </el-table-column>
        <el-table-column label="" width="150" >
            <template #default="{row,$index}">
                <el-button plain @click="downloadFile(row.file_path)">下载</el-button>
            </template>
        </el-table-column>
    <!-- </template> -->
  </el-table>
  
</template>

<script>

</script>

<script setup lang = "js">
    import request from '@/services/request';
    import {useRouter} from'vue-router';
    import {ref} from 'vue';
    const router = useRouter();

    // 数据
    let tableData = ref([]);

    // 页面访问权限检查 created
    const URL = "/loginController/studentCheck";
    request
    .post(URL)
    .then( ({data}) => {
    if(data.code == 200) {
        console.log(data.msg);
        Update();
    }
    else if(data.code == 401) {
        alert(data.msg);
        router.push("/");
    }
    else {
        alert("First created BUG!");
        console.log(data);
    }
    }) 
    .catch( error => {
        console.log(error);
        alert("First created BUG! 请联系管理员");
    });

    // 数据更新
    const Update = () => {
        const URL = '/awardController/stuSelect';
        request
        .post(URL, {
            // 本地存储数据
            stu_name: localStorage.getItem("truth_name")
        })
        .then( ({ data }) => {
                console.log(data);
                if(data.code == 200) {
                    tableData.value = data.data;
                }
                else if(data.code == 401) {
                    router.push('/');
                }
                else {
                    alert("StuHome Update BUG!");
                    console.log(data);
                }
        })
        .catch((error) => {
            alert("StuHome Update BUG! 请联系管理员");
        });
    };


    // 预览文件
    const preview = (url) => {
        console.log(url);
        window.open(process.env.VUE_APP_BASEURL+'fileController/preview/'+url);
    };

    // 下载文件
    const downloadFile = (url) => {
        window.open(process.env.VUE_APP_BASEURL+'fileController/downloadFile/'+url);
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