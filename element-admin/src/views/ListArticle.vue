<template>
  <div>
    <el-table :data="articles">
      <el-table-column prop="title" label="标题" width="140"> </el-table-column>
      <el-table-column prop="body" label="内容" width="120"> </el-table-column>
      <el-table-column label="操作" fixed="right" width="100">
        <template slot-scope="scope">
          <!-- 根据id进行操作 -->
          <el-button type="text" @click="edit(scope.row._id)" size="small"
            >编辑</el-button
          >
          <el-button type="text" @click="remove(scope.row._id)" size="small"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>
export default {
  data() {
    return {
      articles: []
    };
  },
  created() {
    this.fetch();
  },
  methods: {
    fetch() {
      //刷新页面
      this.$http.get("articles", this.articles).then(res => {
        this.articles = res.data;
      });
    },
    edit(id) {
      //直接跳转到edit界面
      this.$router.push(`/articles/${id}/edit`);
    },
    remove(id) {
      // eslint-disable-next-line no-unused-vars
      this.$http.delete(`articles/${id}`).then(res => {
        this.$message({
          message: "删除成功",
          type: "success"
        });
        this.fetch();
      });
    }
  }
};
</script>
