<template>
  <div class="about">
    <!--  三目运算-->
    <h1>{{ id ? "编辑" : "新建" }}管理员信息</h1>
    <el-form label-width="120px" @submit.native.prevent="save">
      <el-form-item label="姓名">
        <el-input v-model="model.username"></el-input>
      </el-form-item>

      <el-form-item label="密码">
        <el-input type="password" v-model="model.password"></el-input>
      </el-form-item>
      <el-form-item label="">
        <el-button type="primary" native-type="submit">
          {{ id ? "更新" : "保存" }}
        </el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
/* 绑定input属性的数据 */
export default {
  data() {
    return {
      model: {}
    };
  },
  props: {
    //注入id
    id: {}
  },

  methods: {
    async save() {
      /* 请求接口并且提交数据 */
      if (!this.id) {
        await this.$http.post("/rest/admin_users", this.model);

        this.$message({
          message: "保存成功",
          type: "success"
        });
      } else {
        /* 请求接口并且更新数据 */
        await this.$http.put(`/rest/admin_users/${this.id}`, this.model);

        this.$message({
          message: "更新成功",
          type: "success"
        });
      }
      this.$router.push("/admin_users/list");
    },
    //获取产品的Name
    async fetch() {
      const res = await this.$http.get(`rest/admin_users/${this.id}`);
      this.model = res.data;
    }
  },
  created() {
    this.id && this.fetch();
  }
};
</script>
