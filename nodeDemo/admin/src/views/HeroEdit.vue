<template>
  <div class="about">
    <!--  三目运算-->
    <h1>{{ id ? "编辑" : "新建" }}英雄</h1>
    <el-form label-width="120px" @submit.native.prevent="save">
      <!-- 英雄名称 -->
      <el-form-item label="英雄名称">
        <el-input v-model="model.name"></el-input>
      </el-form-item>

      <!-- 英雄称号 -->
      <el-form-item label="英雄称号">
        <el-input v-model="model.title"></el-input>
      </el-form-item>

      <!-- 英雄类型 使用多选框 -->
      <el-form-item label="英雄类型">
        <el-select v-model="model.categories" multiple>
          <!-- 从分类的数据中取出 -->
          <el-option
            v-for="item in categories"
            :key="item._id"
            :label="item.name"
            :value="item._id"
          ></el-option>
        </el-select>
      </el-form-item>

      <!-- 英雄使用的难度 -->
      <el-form-item label="使用难度">
        <el-input v-model="model.scores.difficult"></el-input>
      </el-form-item>

      <el-form-item label="头像">
        <el-upload
          class="avatar-uploader"
          action="http://localhost:3000/admin/api/upload"
          :show-file-list="false"
          :on-success="afterUpload"
        >
          <img v-if="model.avatar" :src="model.avatar" class="avatar" />
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
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
      model: {
        name: "",
        avatar: "",
        scores: {}
      },
      categories: []
    };
  },
  props: {
    //注入id
    id: {}
  },

  methods: {
    // 上传成功后返回一个页面
    async afterUpload(res) {
      // eslint-disable-next-line no-console
      // console.log(res);
      //vue显示赋值
      this.$set(this.model, "avatar", res.url);
      this.model.icon = res.url;
    },
    async save() {
      /* 请求接口并且提交数据 */
      if (!this.id) {
        await this.$http.post("/rest/heroes", this.model);
        this.$message({
          message: "保存成功",
          type: "success"
        });
      } else {
        /* 请求接口并且更新数据 */
        await this.$http.put(`/rest/heroes/${this.id}`, this.model);
        this.$message({
          message: "更新成功",
          type: "success"
        });
      }
      this.$router.push("/heroes/list");
    },
    //获取产品的Name
    async fetch() {
      const res = await this.$http.get(`rest/heroes/${this.id}`);
      this.model = res.data;
    },
    async fetchCategories() {
      const res = await this.$http.get(`rest/categories`);
      /* 思考这可能拿到的是所有的分类，但是我们要拿到的是英雄的分类 */
      this.categories = res.data;
    }
  },
  created() {
    this.fetchCategories();
    this.id && this.fetch();
  }
};
</script>

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
