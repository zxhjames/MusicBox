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
      if (res.data.code == 200) {
        if (res.data.data != "loginCache") {
          //客户端存储用户本地信息
          let usermsg = {
            username: this.model.username,
            token: res.data.data.token,
            rank: res.data.data.rank,
            avatarUrl: res.data.data.avatarUrl,
            concerns: res.data.data.concerns,
            followers: res.data.data.followers
          };
          usermsg = JSON.stringify(usermsg);
          console.log("登录信息");
          console.log(usermsg);
          localStorage.setItem("usermsg", usermsg);
        }
        this.$message({
          message: "登录成功",
          type: "success"
        });
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
