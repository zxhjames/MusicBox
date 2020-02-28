<template>
  <div class="login-container">
    <el-card header="请先登录/注册" class="login-card" style="width:80%">
      <el-form>
        <el-form-item label="用户名">
          <el-input v-model="model.username"></el-input>
        </el-form-item>

        <el-form-item label="密码">
          <el-input type="password" v-model="model.password"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="login()">登录</el-button>
          <el-button type="primary" @click="regist()">注册</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      model: {
        username: "",
        password: "",
        token: ""
      }
    };
  },
  methods: {
    async login() {
      //发送登录请求
      this.model.token = localStorage.getItem("token");
      const res = await this.$http1.post("/login", this.model);
      console.log(res.data);
      if (res.data.data != "redisOK") {
        localStorage.token = res.data.data.token;
        localStorage["rank"] = res.data.data.rank;
        localStorage["avatar"] = res.data.data.avatar_url;
      }
      if (res.data.code == 200) {
        this.$message({
          message: "登录成功",
          type: "success"
        });
        localStorage["username"] = this.model.username;
        this.$router.push("/Main");
      } else {
        this.$message({
          message: res.data.message,
          type: "error"
        });
      }
    },
    async regist() {
      this.$router.push("/regist");
    }
  }
};
</script>
<style>
.login-card {
  font-family: "Courier New", Courier, monospace;
  width: 50rem;
  margin: 10rem auto;
}
</style>
