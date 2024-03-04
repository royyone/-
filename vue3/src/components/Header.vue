<template>
    <div class="common-layout">
     <el-container>
       <el-header>
         <el-row>
             <el-col :span="8" :offset="3">
                 <el-image style="width: 200px; height: 100px" :src="require('../assets/school.png')" fit="contain" />
             </el-col>
             <el-col :span="8"><div class="grid-content ep-bg-purple-light" /></el-col>
             <el-col :span="8"><div class="grid-content ep-bg-purple" /></el-col>
         </el-row>
       </el-header>
       <el-main>Main</el-main>
       <el-footer>Footer</el-footer>
     </el-container>
   </div>
 </template>
 
 <script>
 import Header from '@/components/Header.vue';
 
 export default {
     data() {
         return {
             url: ""
         }
     },
     setup() {
         ;
     },
     components: {
         Header
     },
 }
 </script>
 
 
 <style scoped lang="scss">
 .el-header{
     height: 114px;
     background-color: #f4f4f5;
 }
 .el-main{
     height: 100%;
     background-color: #ecf5ff;
 }
 </style>