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
  
<script setup lang = "js">
    import Header from '@/components/Header.vue';
    import request from '@/services/request';
    import {ref} from 'vue';
    import {useRouter} from'vue-router';
    const router = useRouter();
    component: { Header };

    let files = ref('');
    let tableData = ref([]);
    let selectData = ref([]);

    const URL = "/loginController/teacherCheck";
    request
    .post(URL)
    .then( ({data}) => {
    if(data.code == 200) {
        console.log(data.msg);
        update();
    }
    else if(data.code == 401) {
        alert(data.msg);
        router.push("/");
    }
    else {
        alert("Home created BUG!");
        console.log(data);
    }
    }) 
    .catch( error => {
        console.log(error);
        alert("Home created BUG! 请联系管理员");
    });

    
    const preview = (url) => {
        // console.log(url);
        if(url === '') {
            alert("该学生尚未生成奖状，无法预览");
            return ;
        } 
        const token = localStorage.getItem("token");
        window.open('http://localhost:8081/fileController/preview/'+url, '_blank');
    };
    const downloadFile = () => {
        if(selectData.value.every(item => { 
            console.log(item.status !== 0);
            return item.status === 2;  
        })) {
            selectData.value.forEach(item => {
                console.log(item);
                window.open('http://localhost:8081/fileController/downloadFile/'+item.file_path);
            })
        }
        else {
            alert("存在未审核盖章的学生！！");
        }
    };
    
    const downloadTemplate = () => {
        window.open('http://localhost:8081/fileController/downloadTemplate');
    };

    const handleFileSelect = (event) => {
        files.value = event.target.files[0];
    };

    const gameLock = (game_id) => {
        const URL = '/gameController/gameLock';
        request
        .post(URL, {
            game_id: game_id
        })
        .then(({data}) => {
            if(data.code == 200) {
                console.log(data);
            }
            else {
                alert("Home gameLock BUG!");
                console.log(data);
            }
        })
        .catch( error => {
            alert("Home gameLock BUG! 请联系管理员");
        })
    };

    const uploadFiles = () => {
        const URL = '/fileController/fileHandle';
        const formData = new FormData();
        formData.append('excelFile', files.value);
        let data = {"game_id": localStorage.getItem("game_id")};
        formData.append('certificate', JSON.stringify(data));
        console.log(typeof(JSON.stringify(data)));
        request
        .post(URL, formData)
        .then(({data}) => {
            console.log(data);
            if(data.code == 200) {
                update();
                gameLock(localStorage.getItem("game_id"));
            }
            else if(data.code == 501){
                alert(data.msg);
            }
            else {
                alert("Home UploadFiles BUG!");
                console.log(data);
            }
        })
        .catch((error) => {
            console.log(error);
            alert("Home UploadFiles BUG! 请联系管理员");
        });
    };
      
    const update = () => {
        console.log(1);
        const URL = '/awardController/awardSelect';
        request
        .post(URL, {
            game_id: localStorage.getItem("game_id")
        })
        .then(({data}) => {
            if(data.code == 200) {
                tableData.value = data.data;
                console.log(tableData.value);
            }
            else {
                alert("Home update BUG!");
                console.log(data);
            }
            
        })
        .catch((error) => {
            alert("Home update BUG!请联系管理员");
        });
    };
    const certificateCreate1 = () => {
        const URL = '/fileController/certificateCreate1';
        certificateCreate(URL);
    };
    const certificateCreate2 = () => {
        const URL = '/fileController/certificateCreate2';
        certificateCreate(URL);
    };
    const certificateCreate = (URL) => {
        console.log(URL);
        selectData.value.forEach(async item => {
        if(((URL.charAt(URL.length - 1) == '1') && (item.status==0)) || ((URL.charAt(URL.length - 1) == '2') && (item.status==1)) ) {
            await request
            .post(URL, item)
            .then(({data}) => {
                // console.log(data);
                if(data.code == 200) {
                    // alert("添加成功");
                }
                else {
                    alert("certificateCreate BUG! ");
                }
            })
            .catch(error => {
                alert("certificateCreate BUG! 请联系管理员");
            })
        }
            update();
        })
    };
    const handleSelectionChange = (value) => {
        selectData.value = value;

    };
        
</script>