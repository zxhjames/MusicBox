<template>
  <div class="about">
    <!--  三目运算-->
    <h1>{{ id ? "编辑" : "新建" }}广告位</h1>
    <el-form label-width="120px" @submit.native.prevent="save">
      <el-tabs>
        <!-- 广告名称位 -->
        <el-tab-pane label="广告名称" value="basic">
          <el-form-item label="名称" name="basic">
            <el-input v-model="model.name"></el-input>
          </el-form-item>

          <!-- 广告信息位 -->
          <el-form-item label="广告">
            <el-button @click="model.items.push({})"
              ><i class="el-icon-plus"></i>添加广告信息</el-button
            >
            <el-row type="flex" style="flex-wrap:wrap">
              <!-- 取索引 -->
              <el-col :md="12" v-for="(item, i) in model.items" :key="i">
                <el-form-item label="超链接">
                  <el-input v-model="item.url"></el-input>
                </el-form-item>

                <el-form-item label="图标">
                  <el-upload
                    class="avatar-uploader"
                    action="http://localhost:3000/admin/api/upload"
                    :show-file-list="false"
                    :on-success="res => $set(item, 'image', res.url)"
                  >
                    <img v-if="item.image" :src="item.image" class="avatar" />
                    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                  </el-upload>
                </el-form-item>
                <!-- 删除按钮 -->
                <el-form-item>
                  <el-button
                    size="small"
                    type="danger"
                    @click="model.items.splice(i, 1)"
                    >删除</el-button
                  >
                </el-form-item>
              </el-col>
            </el-row>
          </el-form-item>
        </el-tab-pane>
      </el-tabs>

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
        items: []
      }
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
        await this.$http.post("/rest/ads", this.model);
        this.$message({
          message: "保存成功",
          type: "success"
        });
      } else {
        /* 请求接口并且更新数据 */
        await this.$http.put(`/rest/ads/${this.id}`, this.model);

        this.$message({
          message: "更新成功",
          type: "success"
        });
      }
      this.$router.push("/ads/list");
    },
    //获取产品的Name
    async fetch() {
      const res = await this.$http.get(`rest/ads/${this.id}`);
      this.model = Object.assign({}, this.model, res.data);
    }
  },
  created() {
    this.id && this.fetch();
  }
};
</script>
