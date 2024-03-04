<template>
    <el-row><el-button round @click="handleOpen">创建</el-button></el-row>
    <el-table 
        :data="tableData" 
        stripe 
        style="width: 100%" height="750">


        <!-- <template slot-scope="scope"> -->
        <el-table-column fixed label="" width="150" >
            <template #default="{row,$index}">
                <el-button plain @click="detail(row.game_id)">查看</el-button>
            </template>
        </el-table-column>
        <el-table-column prop="game_type" label="类别" width="120" />
        <el-table-column prop="game_level" label="级别" width="120" />
        <el-table-column prop="game_name" label="名称" width="320" />
        <el-table-column prop="game_date" label="时间" width="120" />
        <el-table-column prop="game_author" label="创建人" width="100" />
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
            <el-option label="院级" value="院级" />
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
        </el-form>
        <el-button type="primary" @click="submitForm()">添加</el-button>
    </el-dialog>
</template>

<script setup lang = "js">
    import { ref } from 'vue'
    import request from '@/services/request';
    import emitter from '@/utils/emitter';
    import {useRouter} from 'vue-router';
    const router = useRouter();

    // 数据
    let formData = ref({});
    let visible = ref(false);
    let tableData = ref([]);

    // 网页访问权限检查 created
    const URL = "/loginController/teacherCheck";
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
    })

    // 方法

    // 打开创建比赛的弹窗
    const handleOpen = () => {
        visible.value = true;
    };
    const handleClose = () => {
        visible.value = false;
    };
    // 提交表单增加数据
    const submitForm = () => {
        formData.value.game_author = localStorage.getItem("truth_name");
        if(formData.value.game_type==='') {
            alert("添加失败，类别不可为空");
        }
        else if(formData.value.game_level==='') {
            alert("添加失败，级别不可为空");
        }
        else if(formData.value.game_date==='') {
            alert("添加失败，时间不可为空");
        }
        else if(formData.value.game_name==='') {
            alert("添加失败，名称不可为空");
        }
        else {
            const URL = '/gameController/gameInsert';
            request
            .post(URL, formData.value)
            .then(({data}) => {
                visible.value = false;
                if(data.code == 200) {
                    Update();
                }
                else {
                    alert("First submitForm BUG! ");
                    console.log(data);
                }
            })
            .catch((error) => {
                // 处理错误
                console.log(error);
                alert("First submitForm BUG! 请联系管理员");
            });
        }
    };

    //调取数据库获得已创建比赛数据
    const Update = () => {
        const URL = '/gameController/gameSelect';
        request
        .post(URL, {})
        .then( ({ data }) => {
            console.log(data);
            if(data.code == 200) {
                tableData.value = data.data;
            }
            else if(data.code == 401) {
                router.push('/');
            }
            else {
                console.log(data);
            }
        })
        .catch((error) => {
            alert("First Update BUG! 请联系管理员")
        });
    };

    // 删除此条比赛数据
    const Delete = (id) => {
        const URL = '/gameController/gameDelete';
        request
        .post(URL, {
            game_id: id
        })
        .then(({data}) => {
            // console.log(data);
                Update();
            if(data.code == 200) {
                Update();
            }
            else {
                console.log("First Delete BUG!");
                console.log(data);
            }
        })
        .catch((error) => {
            alert("First Delete BUG! 请联系管理员");
        });
    };
    
    // 进入该条比赛项
    const detail = (id) => {
        localStorage.setItem("game_id", id);
        router.push('/Home');
    }


</script>
<style scoped>
.demo-form-inline .el-input {
--el-input-width: 420px;
}

.demo-form-inline .el-select {
--el-select-width: 220px;
}
</style>