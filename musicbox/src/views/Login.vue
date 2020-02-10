<template>
  <div class="login-container">
    <el-card header="请先登录" class="login-card">
      <el-form @submit.native.prevent="login">
        <el-form-item label="用户名">
          <el-input v-model="model.username"></el-input>
        </el-form-item>

        <el-form-item label="密码">
          <el-input type="password" v-model="model.password"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" native-type="submit">登录</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      model: {}
    };
  },
  methods: {
    async login() {
      //发送登录请求
      await this.$http
        .get("/login/cellphone", {
          params: {
            phone: this.model.username,
            password: this.model.password
          }
        })
        .then(res => {
          //如果状态码是200表示登录成功
          if (res.data.code == 200) {
            this.$message({
              type: "success",
              message: "登录成功"
            });
            //存取用户的token
            localStorage.token = res.data.token;
            //存取用户的id,方便以后的操作
            sessionStorage.setItem("uid", res.data.profile.userId);
            this.$router.push("/Main");
          } else {
            //否则保错 {
            this.$message({
              type: "error",
              message: res.data.msg
            });
          }
        });
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
