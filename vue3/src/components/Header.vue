<template>
    <el-header class="Head">11
        <div id="headWord">获奖查询平台</div>
        <div id="myName">myName</div>
        <img src="../assets/headImg.jpg" class="headImg">
    </el-header>
  


</template>

<script>
export default {

}
</script>

<style scoped lang="scss">
.Head {
    margin: 0 auto;
    height: 50px;
    background: grey;
    #headWord{
        display: inline;
        text-align: center;
        height: 10px;
        width: 50px;

    }
    #myName{
        margin-top: 10px;
        vertical-align: middle;
        display: inline;
    }
    .headImg {
        margin: 10px;
        border-radius: 15px;
        vertical-align: middle;
        display: inline;
        width: 30px;
        height: 30px;
        float: right;
        margin-left: auto;
        margin-right: 50px;
    }
}

</style>