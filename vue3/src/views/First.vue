<template>
    <el-row><el-button round @click="handleOpenGame">创建比赛</el-button></el-row>
    <el-row><el-button round @click="handleOpenCreateUser">创建用户</el-button></el-row>
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
        <el-table-column prop="organizer" label="承办单位" width="200" />
        <el-table-column label="" width="150" >
            <template #default="{row,$index}">
                <el-button plain @click="Delete(row.game_id)">删除</el-button>
            </template>
        </el-table-column>
        <!-- </template> -->
    </el-table>

    <!-- 创建比赛的弹窗 -->
    <el-dialog :model-value="visible" title="比赛创建" @close="handleCloseGame" :before-close="handleCloseGame">

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

        <el-form-item label="承办单位">
            <el-select
                v-model="formData.organizer"
                placeholder=""
                clearable
            >
            <el-option label="信息科学与技术学院" value="信息科学与技术学院" />
            <el-option label="人文学院" value="人文学院" />
            <el-option label="生命与环境科学学院" value="生命与环境科学学院" />
            <el-option label="音乐学院" value="音乐学院" />
            <el-option label="美术学院" value="美术学院" />
            <el-option label="体育学院" value="体育学院" />
            <el-option label="外国语学院" value="外国语学院" />
            <el-option label="沈钧儒法学院" value="沈钧儒法学院" />
            <el-option label="护理学院" value="护理学院" />
            <el-option label="公共卫生学院" value="公共卫生学院" />
            <el-option label="基础医学院" value="基础医学院" />
            <el-option label="材料与化学化工学院" value="材料与化学化工学院" />
            <el-option label="阿里巴巴商学院" value="阿里巴巴商学院" />
            <el-option label="药学院" value="药学院" />
            <el-option label="文化创意与传媒学院" value="文化创意与传媒学院" />
            <el-option label="数学学院" value="数学学院" />
            <el-option label="物理学院" value="物理学院" />
            <el-option label="经济学院" value="经济学院" />
            <el-option label="经亨颐教育学院" value="经亨颐教育学院" />   
            <el-option label="哈尔科夫学院" value="哈尔科夫学院" />
            <el-option label="附属医院（临床医学院、口腔医学院）" value="附属医院（临床医学院、口腔医学院）" />
            <el-option label="工学院" value="工学院" />
            <el-option label="基础部" value="基础部" />
            <el-option label="马克思主义学院" value="马克思主义学院" />
            <el-option label="国际教育学院" value="国际教育学院" />
            </el-select>
        </el-form-item>
        </el-form>
        <el-button type="primary" @click="submitForm()">添加</el-button>
    </el-dialog>
    <el-dialog :model-value="visibleCreateUser" title="账号创建" @close="handleCloseCreateUser" :before-close="handleCloseCreateUser">
        <el-form 
        :label-position="labelPosition" 
        label-width="auto"
        style="max-width: 500px"   
        :model="formDataUser" class="">
            <el-form-item label="用户名">
                <el-input v-model="formDataUser.user_name" placeholder="" clearable />
            </el-form-item>
            <el-form-item label="密码">
                <el-input type="password" v-model="formDataUser.user_pwd" placeholder="" clearable />
            </el-form-item>
            <el-form-item label="确认密码">
                <el-input type="password" v-model="user_pwd2" placeholder="" clearable />
            </el-form-item>
            <el-form-item label="姓名">
                <el-input v-model="formDataUser.truth_name" placeholder="" clearable />
            </el-form-item>
            <el-form-item label="所属部门">
            <el-select
                v-model="formDataUser.user_college"
                placeholder=""
                clearable
            >
            <el-option label="信息科学与技术学院" value="信息科学与技术学院" />
            <el-option label="人文学院" value="人文学院" />
            <el-option label="生命与环境科学学院" value="生命与环境科学学院" />
            <el-option label="音乐学院" value="音乐学院" />
            <el-option label="美术学院" value="美术学院" />
            <el-option label="体育学院" value="体育学院" />
            <el-option label="外国语学院" value="外国语学院" />
            <el-option label="沈钧儒法学院" value="沈钧儒法学院" />
            <el-option label="护理学院" value="护理学院" />
            <el-option label="公共卫生学院" value="公共卫生学院" />
            <el-option label="基础医学院" value="基础医学院" />
            <el-option label="材料与化学化工学院" value="材料与化学化工学院" />
            <el-option label="阿里巴巴商学院" value="阿里巴巴商学院" />
            <el-option label="药学院" value="药学院" />
            <el-option label="文化创意与传媒学院" value="文化创意与传媒学院" />
            <el-option label="数学学院" value="数学学院" />
            <el-option label="物理学院" value="物理学院" />
            <el-option label="经济学院" value="经济学院" />
            <el-option label="经亨颐教育学院" value="经亨颐教育学院" />   
            <el-option label="哈尔科夫学院" value="哈尔科夫学院" />
            <el-option label="附属医院（临床医学院、口腔医学院）" value="附属医院（临床医学院、口腔医学院）" />
            <el-option label="工学院" value="工学院" />
            <el-option label="基础部" value="基础部" />
            <el-option label="马克思主义学院" value="马克思主义学院" />
            <el-option label="国际教育学院" value="国际教育学院" />
            </el-select>
        </el-form-item>
            <el-button type="primary" @click="submitFormUser()">添加</el-button>
        </el-form>
    </el-dialog>
</template>

<script setup lang = "js">
    import { ref } from 'vue'
    import request from '@/services/request';
    import emitter from '@/utils/emitter';
    import { ElMessage, ElMessageBox } from 'element-plus';
    import {useRouter} from 'vue-router';

    const router = useRouter();

    // 数据
    let formData = ref({});
    let visible = ref(false);
    let tableData = ref([]);
    let visibleCreateUser = ref(false);
    let formDataUser = ref({});
    let user_pwd2 = ref() ; // 用户创建密码校验
    const labelPosition = ref('right');

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
    const handleOpenGame = () => {
        visible.value = true;
    };
    const handleCloseGame = () => {
        visible.value = false;
    };

    // 打开创建用户的弹窗
    const handleOpenCreateUser = () => {
        visibleCreateUser.value = true;
    };
    const handleCloseCreateUser = () => {
        visibleCreateUser.value = false;
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
        else if(formData.value.organizer === '') {
            alert("添加失败，承办单位不可为空");
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
        if( localStorage.getItem("isadmin") === '2') {
            alert("权限不足无法进行删除，请联系管理员");
            return ;
        }
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
    // 创建用户
    const submitFormUser = () => {
        if( formDataUser.value.user_pwd !== user_pwd2.value) {
            alert("密码错误请修正！");
            return ;
        }
        visibleCreateUser.value = false;
        console.log(formDataUser.value);
        let URL = "userController/userCheck";
        request
        .post(URL, formDataUser.value)
        .then( ({data}) => {
            if(data.code == 200) {
                // 嵌套请求创建用户
                URL = "userController/userCreate";
                request
                .post(URL, formDataUser.value)
                .then( ({ data }) => {
                    console.log(data);
                    if(data.code == 200) {
                        ElMessageBox.alert('用户名：'+formDataUser.value.user_name+'\n'+'密码：'+formDataUser.value.user_pwd, '请注意保存好用户名和密码');
                    }
                    else if(data == 501) {
                        ElMessageBox.alert(data.msg, '');
                    }
                    else {
                        alert("First submitFormUser BUG2!");
                    }
                })
                .catch( ({data}) => {
                    alert("First submitFormUser BUG2! 请联系管理员");
                })
            }
            else if(data.code == 501) {
                alert(data.msg);
            }
            else {
                alert("First submitFormUser BUG1!");
            }
        })
        .catch( error => {
            console.log(error);
            alert("First submitFormUser BUG1! 请联系管理员");
        })
        formDataUser.value = {};
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