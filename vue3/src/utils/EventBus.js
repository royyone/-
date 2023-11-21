// 创建一个都能访问到的事件总线（空 Vue实例）
import Vue from 'vue'

const Bus = new Vue();

export default Bus;