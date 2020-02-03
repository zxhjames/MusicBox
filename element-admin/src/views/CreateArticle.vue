<template>
  <el-form
    ref="form"
    :model="article"
    label-width="80px"
    @submit.native.prevent="saveArticle"
  >
    <el-form-item label="文章标题">
      <el-input v-model="article.title"></el-input>
    </el-form-item>

    <el-form-item label="文章内容">
      <el-input v-model="article.body" type="textarea"></el-input>
    </el-form-item>

    <el-form-item>
      <el-button type="primary" native-type="submit">立即创建</el-button>
      <el-button>取消</el-button>
    </el-form-item>
  </el-form>
</template>
<script>
export default {
  data() {
    return {
      article: {}
    };
  },
  methods: {
    saveArticle() {
      //发送请求和请求体
      // eslint-disable-next-line no-unused-vars
      this.$http.post("/articles", this.article).then(res => {
        this.$message({
          message: "文章创建成功!",
          type: "success"
        });
        //成功后跳转
        this.$router.push("/articles/index");
      });
      console.log(this.article);
    }
  }
};
</script>
