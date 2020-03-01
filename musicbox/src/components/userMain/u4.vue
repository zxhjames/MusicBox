<template>
  <div>
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="动态名称">
        <el-input v-model="form.title"></el-input>
      </el-form-item>
      <el-form-item label="动态内容">
        <vue-editor v-model="form.content" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">立即创建</el-button>
        <el-button type="primary" @click="clear">清空</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
export default {
  data() {
    return {
      form: {
        username: JSON.parse(localStorage.getItem("usermsg")).username,
        title: "",
        content: ""
      }
    };
  },

  methods: {
    async onSubmit() {
      let res = await this.$http1.post("/actions", this.form);
      if (res.data.code == 200) {
        this.$message({
          type: "success",
          message: res.data.message
        });
        //刷新页面
      } else {
        this.$message({
          type: "error",
          message: res.data.message
        });
      }
    },
    async clear() {
      this.form.content = "";
      this.form.title = "";
    }
  }
};
</script>
