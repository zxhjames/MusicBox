<template>
  <div>
    <h1>管理员列表</h1>
    <!-- 显示分类 -->
    <el-table :data="items">
      <el-table-column prop="_id" label="ID"> </el-table-column>
      <!-- 注意要带.name -->
      <el-table-column prop="username" label="管理员姓名"> </el-table-column>
      <el-table-column prop="password" label="管理员密码"> </el-table-column>
      <el-table-column label="操作" fixed="right">
        <template slot-scope="scope">
          <!-- 根据id进行操作 -->
          <el-button
            type="text"
            @click="$router.push(`/admin_users/edit/${scope.row._id}`)"
            size="small"
            >编辑</el-button
          >
          <el-button type="text" @click="remove(scope.row._id)" size="small"
            >删除</el-button
          >
        </template>
      </el-table-column>
      <!-- 显示编辑和删除 -->
    </el-table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      items: []
    };
  },
  created() {
    this.fetch();
  },
  methods: {
    async fetch() {
      //请求数据库显示数据
      await this.$http.get("rest/admin_users", this.items).then(res => {
        this.items = res.data;
      });
    },

    //删除分类
    remove(id) {
      // eslint-disable-next-line no-unused-vars
      this.$http.delete(`rest/admin_users/${id}`).then(res => {
        this.$message({
          message: "删除成功",
          type: "success"
        });
        //删除完后刷新页面
        this.fetch();
      });
    }
  }
};
</script>
