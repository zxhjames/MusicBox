<template>
  <!-- 一级评论业务 -->
  <div>
    <!-- 放置用户评论 -->
    <div class=" d-flex ai-center pt-3 pb-1">
      <el-input
        type="textarea"
        :rows="2"
        placeholder="请输入评论内容"
        v-model="textarea"
      >
      </el-input>
    </div>

    <div class=" d-flex  pr-0 ">
      <el-button plain @click="submit">评论</el-button>
      <el-button plain @click="clear">清空</el-button>
    </div>

    <!-- 一级评论 -->
  </div>
</template>
<script>
export default {
  props: {
    //动态的id
    id: { type: Number, required: true },
    //评论级数
    type: { type: Number, required: true }
  },
  data() {
    return {
      textarea: "",
      comments: {
        commentator: JSON.parse(localStorage.getItem("usermsg")).username,
        type: this.type,
        parentId: this.id
      },
      Allcomments: {}
    };
  },
  created() {
    this.fetch();
  },
  methods: {
    async submit() {
      this.comments.content = this.textarea;
      console.log(this.comments);
      let res = await this.$http1.post(`/pushComments`, this.comments);
      console.log(res.data);
      if (res.data.code == 200) {
        this.$message({
          type: "success",
          message: "评论成功!"
        });
        //刷新页面
      } else {
        this.$message({
          type: "error",
          message: res.data.message
        });
        this.clear();
      }
    },
    //清空评论板
    clear() {
      this.textarea = "";
    },

    async fetch() {
      //获取这个动态的所有评论,传递两个参数一个 id 一个type
      const res = await this.$http1.get(
        `/getAllcommentsById/${this.id}/${this.type}`
      );
      //要将数组转换为json
      console.log(res.data);
    }
  }
};
</script>
