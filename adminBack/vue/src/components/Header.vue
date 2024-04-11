<template>
  <div style="font-size: 12px; display: flex">
    <div style="flex: 1; font-size: 20px">
        <span :class="collapeseBtnClass" style="cursor: pointer" @click="collapse"></span>
        <el-breadcrumb separator="/" style="display: inline-block; margin-left: 10px;">
            <h2>
              <el-breadcrumb-item :to="'/'">首页</el-breadcrumb-item>
              <el-breadcrumb-item>{{ currentPathName }}</el-breadcrumb-item>
            </h2>
          </el-breadcrumb>
    </div>
          <el-dropdown style="width: 70px; cursor: pointer">
            <span>王小虎</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item style="margin: 3px">管理员信息</el-dropdown-item>
              <el-dropdown-item>退出</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
    </div>
</template>

<script>
export default {
    name: "Header",
    data() {
      return {
        currentPathName: ''
      }
    },
    watch: {
      '$route': function() {
        this.currentPathName = localStorage.getItem("currentPathName")
      }
    },
    props: {
        collapeseBtnClass: String,
        collapse: Boolean
    },
    methods: {
      
    },
    created() {
      
    }
}
</script>

<style>

</style>