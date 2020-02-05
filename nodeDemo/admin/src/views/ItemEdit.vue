<template>
  <div class="about">
    <!--  三目运算-->
    <h1>{{ id ? "编辑" : "新建" }}分类</h1>
    <el-form label-width="120px" @submit.native.prevent="save">
      <el-form-item label="名称">
        <el-input v-model="model.name"></el-input>
      </el-form-item>

      <el-form-item label="图标">
        <el-upload
          class="avatar-uploader"
          action="http://localhost:3000/admin/api/upload"
          :show-file-list="false"
          :on-success="afterUpload"
        >
          <img v-if="model.icon" :src="model.icon" class="avatar" />
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
    // 上传成功后返回一个页面
    async afterUpload(res) {
      // eslint-disable-next-line no-console
      // console.log(res);
      //vue显示赋值
      this.$set(this.model, "icon", res.url);
      this.model.icon = res.url;
    },
    async save() {
      /* 请求接口并且提交数据 */
      if (!this.id) {
        await this.$http.post("/rest/items", this.model);
        this.$message({
          message: "保存成功",
          type: "success"
        });
      } else {
        /* 请求接口并且更新数据 */
        await this.$http.put(`/rest/items/${this.id}`, this.model);
        this.$message({
          message: "更新成功",
          type: "success"
        });
      }
      this.$router.push("/items/list");
    },
    //获取产品的Name
    async fetch() {
      const res = await this.$http.get(`rest/items/${this.id}`);
      this.model = res.data;
    }
  },
  created() {
    this.id && this.fetch();
  }
};
</script>
