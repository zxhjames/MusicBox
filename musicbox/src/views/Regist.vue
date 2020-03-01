<template>
  <div class="login-container">
    <el-card header="请先登录/注册" class="login-card" style="width:80%">
      <el-form>
        <el-form-item label="头像文件">
          <input type="file" @change="getFile($event)" />
        </el-form-item>
        <el-form-item label="用户名">
          <el-input v-model="model.username"></el-input>
        </el-form-item>

        <el-form-item label="密码">
          <el-input type="password" v-model="model.password"></el-input>
        </el-form-item>
        <button
          class="button button-primary button-pill button-small"
          @click="submit($event)"
        >
          提交
        </button>
      </el-form>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      model: {},
      imageUrl: "",
      file: ""
    };
  },
  methods: {
    getFile: function(event) {
      this.file = event.target.files[0];
      console.log(this.file);
    },
    submit: function(event) {
      //阻止元素发生默认的行为
      event.preventDefault();
      let formData = new FormData();
      formData.append("file", this.file);
      formData.append("username", this.model.username);
      formData.append("password", this.model.password);
      if (
        this.file == null ||
        this.model.username == "" ||
        this.model.password == ""
      ) {
        this.$message({
          type: "warning",
          message: "注册信息不完整"
        });
        return;
      }
      this.$http1
        .post("/register", formData)
        .then(res => {
          console.log(res.data);
          if (res.data.code == 200) {
            this.$message({
              type: "success",
              message: "注册成功"
            });
            this.$router.push("/login");
          }
        })
        .catch(() => {
          this.$message({
            type: "error",
            message: "用户名已存在请你换一个"
          });
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
<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
