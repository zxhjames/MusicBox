<template>
  <div>
    <el-form ref="form" :model="form">
      <el-form-item label="名称">
        <el-input v-model="form.title"></el-input>
      </el-form-item>
      <vue-editor v-model="form.content" />
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
        // user: {
        //   avatarUrl: JSON.parse(localStorage.getItem("usermsg")).avatarUrl,
        //   username: JSON.parse(localStorage.getItem("usermsg")).username,
        //   rank: JSON.parse(localStorage.getItem("usermsg")).rank,
        //   concerns: JSON.parse(localStorage.getItem("usermsg")).concerns,
        //   followers: JSON.parse(localStorage.getItem("usermsg")).followers
        // },
        creator: JSON.parse(localStorage.getItem("usermsg")).username,
        title: "",
        content: "",
        type: 0
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
        this.$router.push("/Community");
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
