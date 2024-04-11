<template>
  <div class="home" style="height: 100vh">
    <el-container style="min-height: 100%;">
      <el-aside :width="sideWidth + 'px'" style="background-color: rgb(238, 241, 246); min-height: 100%">
        <Aside :isCollapse="isCollapse" />
      </el-aside>
      <el-container>
        <el-header style="border-bottom: 1px solid #ccc; ">
          <Header :isCollapse="isCollapse" :collapeseBtnClass="collapeseBtnClass" />
        </el-header>
        
        <el-main>
          <!-- 表示当前页面路由在这展示 -->
          <router-view />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import Aside from '@/components/Aside';
import Header from '@/components/Header';
export default {
  data() {
    return {
      collapeseBtnClass: 'el-icon-s-fold',
      isCollapse: false,
      sideWidth: 200
    }
  },
  created() {
    

  },
  components: {
    Aside,
    Header,
  },
  methods: {
    collapse() { //点击收起/展开菜单
      this.isCollapse = !this.isCollapse
      if (this.isCollapse) {
        this.sideWidth = 64
        this.collapeseBtnClass = 'el-icon-s-unfold'
      } else {
        this.sideWidth = 200
        this.collapeseBtnClass = 'el-icon-s-fold'
      }
    },
    
  }
};
</script>

