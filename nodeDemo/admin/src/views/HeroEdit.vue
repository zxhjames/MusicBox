<template>
  <div class="about">
    <!--  三目运算-->
    <h1>{{ id ? "编辑" : "新建" }}英雄</h1>
    <el-form label-width="120px" @submit.native.prevent="save">
      <!-- value默认选中第一个 -->
      <el-tabs value="skills" type="border-card">
        <el-tab-pane label="基础信息" name="basic">
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
            <el-rate
              style="margin-top:0.6rem"
              :max="9"
              show-score
              v-model="model.scores.difficult"
            ></el-rate>
          </el-form-item>

          <el-form-item label="技能">
            <el-rate
              style="margin-top:0.6rem"
              :max="9"
              show-score
              v-model="model.scores.skills"
            ></el-rate>
          </el-form-item>

          <el-form-item label="攻击">
            <el-rate
              style="margin-top:0.6rem"
              :max="9"
              show-score
              v-model="model.scores.attack"
            ></el-rate>
          </el-form-item>

          <el-form-item label="生存">
            <el-rate
              style="margin-top:0.6rem"
              :max="9"
              show-score
              v-model="model.scores.survive"
            ></el-rate>
          </el-form-item>

          <!-- 英雄顺风出装 使用多选框 -->
          <el-form-item label="顺风出装">
            <el-select v-model="model.items1" multiple>
              <!-- 从分类的数据中取出 -->
              <el-option
                v-for="item in items"
                :key="item._id"
                :label="item.name"
                :value="item._id"
              ></el-option>
            </el-select>
          </el-form-item>

          <!-- 逆风出装 -->
          <el-form-item label="逆风出装">
            <el-select v-model="model.items2" multiple>
              <!-- 从分类的数据中取出 -->
              <el-option
                v-for="item in items"
                :key="item._id"
                :label="item.name"
                :value="item._id"
              ></el-option>
            </el-select>
          </el-form-item>

          <!--使用技巧 -->
          <el-form-item label="使用技巧">
            <el-input type="textarea" v-model="model.usageTips"></el-input>
          </el-form-item>

          <!--对抗技巧 -->
          <el-form-item label="对抗技巧">
            <el-input type="textarea" v-model="model.battleTips"></el-input>
          </el-form-item>

          <!--团战思路-->
          <el-form-item label="团战思路">
            <el-input type="textarea" v-model="model.teamTips"></el-input>
          </el-form-item>
        </el-tab-pane>

        <el-tab-pane label="技能" name="skills">
          <el-button @click="model.skills.push({})"
            ><i class="el-icon-plus"></i>添加技能</el-button
          >
          <el-row type="flex" style="flex-wrap:wrap">
            <!-- 取索引 -->
            <el-col :md="12" v-for="(item, i) in model.skills" :key="i">
              <el-form-item label="名称">
                <el-input v-model="item.name"></el-input>
              </el-form-item>

              <el-form-item label="图标">
                <el-upload
                  class="avatar-uploader"
                  action="http://localhost:3000/admin/api/upload"
                  :show-file-list="false"
                  :on-success="res => $set(item, 'icon', res.url)"
                >
                  <img v-if="item.icon" :src="item.icon" class="avatar" />
                  <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                </el-upload>
              </el-form-item>

              <el-form-item label="描述">
                <el-input v-model="item.description" type="textarea"></el-input>
              </el-form-item>

              <el-form-item label="小提示">
                <el-input v-model="item.tips" type="textarea"></el-input>
              </el-form-item>

              <!-- 删除按钮 -->
              <el-form-item>
                <el-button
                  size="small"
                  type="danger"
                  @click="model.skills.splice(i, 1)"
                  >删除</el-button
                >
              </el-form-item>
            </el-col>
          </el-row>
        </el-tab-pane>
      </el-tabs>

      <el-form-item style="margin-top:1rem">
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
        scores: {
          difficult: 0
        },
        skills: []
      },
      categories: [],
      items: []
      // usageTips: {}
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
    },
    async fetchItems() {
      const res = await this.$http.get(`rest/items`);
      /* 思考这可能拿到的是所有的分类，但是我们要拿到的是英雄的分类 */
      this.items = res.data;
    }
  },
  created() {
    this.fetchCategories();
    this.fetchItems();
    this.id && this.fetch();
  }
};
</script>


