<template>
  <div class="about">
    <!--  三目运算-->
    <h1>{{ id ? "编辑" : "新建" }}分类</h1>
    <el-form label-width="120px" @submit.native.prevent="save">
      <el-form-item label="上级分类">
        <el-select v-model="model.parent">
          <el-option
            v-for="item in parents"
            :key="item._id"
            :label="item.name"
            :value="item._id"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="名称">
        <el-input v-model="model.name"></el-input>
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
      model: {},
      parents: []
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
        await this.$http.post("/rest/categories", this.model);
        this.$message({
          message: "保存成功",
          type: "success"
        });
      } else {
        /* 请求接口并且更新数据 */
        await this.$http.put(`/rest/categories/${this.id}`, this.model);
        this.$message({
          message: "更新成功",
          type: "success"
        });
      }
      this.$router.push("/categories/list");
    },
    //获取产品的Name
    async fetch() {
      const res = await this.$http.get(`rest/categories/${this.id}`);
      this.model = res.data;
    },
    async fetchParents() {
      const res = await this.$http.get(`rest/categories`);
      this.parents = res.data;
    }
  },
  created() {
    this.fetchParents();
    this.id && this.fetch();
  }
};
</script>
