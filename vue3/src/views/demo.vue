<template>
    <button type = "text" @click.stop.prevent="downloadFile">下载</button>
  </template>
  
  <script>
  import axios from 'axios';


  export default {
    methods: {
    downloadFile (){
    let downloadUrl = "/fileController/fileLoad"
 
    axios
    .get(downloadUrl)
    .then(res => {
        const blob = new Blob([res.data]);
        //创建一个<a></a>标签
        let a = document.createElement("a");
        // 将流文件写入a标签的href属性值
        a.href = URL.createObjectURL(blob);
        //设置文件名
        a.download = "template.txt";
        // 隐藏a标签
        a.style.display = "none";
        // 将a标签追加到文档对象中
        document.body.appendChild(a); 
        // 模拟点击了a标签，会触发a标签的href的读取，浏览器就会自动下载了
        a.click();
        //用完就删除a标签
        a.remove();
    })
  }
}
  }

  </script>
  