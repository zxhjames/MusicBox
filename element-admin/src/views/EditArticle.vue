<template>
  <el-form
    @submit.native.prevent="saveArticle"
    ref="form"
    :model="article"
    label-width="80px"
  >
    <el-form-item label="文章标题">
      <el-input v-model="article.title"></el-input>
    </el-form-item>
    <el-form-item label="文章内容">
      <el-input type="textarea" v-model="article.body"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" native-type="submit">保存</el-button>
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
      this.$http
        .put(`articles/${this.$route.params.id}`, this.article)
        // eslint-disable-next-line no-unused-vars
        .then(res => {
          this.$message({
            message: "文章创建成功!",
            type: "success"
          });
          //成功后跳转,再考虑将所有文章显示出来
          this.$router.push("/articles/index");
        });
    },

    fetch() {
      this.$http.get(`/articles/${this.$route.params.id}`).then(res => {
        this.article = res.data;
      });
    }
  },
  created() {
    this.fetch();
  }
};
</script>
