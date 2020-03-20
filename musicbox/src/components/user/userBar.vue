<template>
  <div>
    <el-dropdown trigger="click">
      <span class="el-dropdown-link">
        <el-avatar
          :src="this.usermsg.avatarUrl"
          height="40"
          type="primary"
          style="margin-left: 0px;"
        />
      </span>

      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item icon="el-icon-s-home"
          ><router-link to="/UserMain">我的主页</router-link></el-dropdown-item
        >

        <el-dropdown-item icon="el-icon-s-help">我的空间 </el-dropdown-item>
        <el-dropdown-item icon="el-icon-s-release" @click.native="loginOut()"
          >注销账号</el-dropdown-item
        >
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>
<script>
export default {
  data() {
    return {
      usermsg: JSON.parse(localStorage.getItem("usermsg"))
    };
  },
  methods: {
    loginOut() {
      localStorage.removeItem("usermsg");
      localStorage.removeItem("check");
      localStorage.removeItem("username");
      this.$router.push("/Login");
    }
  }
};
</script>

<style>
.el-dropdown-link {
  cursor: pointer;
  color: #409eff;
}
.el-icon-arrow-down {
  font-size: 12px;
}
.demonstration {
  display: block;
  color: #8492a6;
  font-size: 14px;
  margin-bottom: 20px;
}
</style>
