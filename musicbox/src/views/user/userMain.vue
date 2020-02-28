<template>
  <div>
    <el-container>
      <!-- 歌手头像 -->
      <el-main>
        <img
          :src="this.userAvatar"
          width="100px"
          height="100px"
          class="img-circle"
        />
        <p>
          <i class="el-icon-user">{{ this.username }}</i>
        </p>
      </el-main>

      <el-footer
        ><span>关注 0</span>&nbsp;&nbsp;|&nbsp;&nbsp;
        <span>粉丝 0</span></el-footer
      >
    </el-container>

    <!-- 要不要做个组件 -->
    <el-tabs type="border-card">
      <el-tab-pane>
        <span slot="label"><i class="el-icon-s-home"></i> 我的曲库</span>
        <m-u1></m-u1>
      </el-tab-pane>

      <el-tab-pane>
        <span slot="label"><i class="el-icon-s-custom"></i> 我的动态</span>
        我的行程
      </el-tab-pane>
      <el-tab-pane>
        <span slot="label"><i class="el-icon-s-help"></i> 好友动态</span>
        我的行程
      </el-tab-pane>
      <el-tab-pane>
        <span slot="label"><i class="el-icon-plus"></i> 发布动态</span>
        <m-u4></m-u4>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
<script>
export default {
  data() {
    return {
      username: localStorage.getItem("username"),
      userAvatar: this.$store.state.resources + localStorage.getItem("avatar")
    };
  }
};
</script>
<style>
.bg {
  background-color: rgb(226, 214, 188);
}
/* .table {
  border-collapse: collapse;
  border-spacing: 0;
} */
.el-header,
.el-footer {
  background-color: #e9eef3;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #d3dce6;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  background-color: #e9eef3;
  color: #333;
  text-align: center;
  line-height: 160px;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
</style>
