<template>
  <!-- 二级评论业务 -->
  <div>
    <div class="d-flex pt-2 pl-2 pr-2">
      <el-input placeholder="请输入回复内容" v-model="input" clearable>
      </el-input>
      <el-button size="mini" @click="submit">回复</el-button>
    </div>

    <div v-for="item in Allcomments" :key="item">
      <div class="d-flex pt-2 pl-2 pr-2">
        <div class=" d-flex ai-center ">
          <el-avatar
            :src="item.user.avatarUrl"
            :size="25"
            type="primary"
            style="margin-left: 0px;"
            shape="square"
          />
          <div class="pl-2 flex-1">
            <div class="text-black fs-xs">{{ item.commentator }}</div>
            <div class="text-grey fs-xxs pr-1">
              {{ new Date(item.gmtCreate).toLocaleString() }}
              <svg class="icon1" aria-hidden="true" style="margin-left:10px">
                <use xlink:href="#icon-dianzan1"></use>
              </svg>
              {{ item.likeCount }}
            </div>
          </div>
        </div>
      </div>
      <div class="d-flex pl-2 pr-2">{{ item.content }}</div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    //上级评论的id
    id: { type: Number, required: true },
    //二级回复标识符
    type: { type: Number, required: true }
  },
  data() {
    return {
      input: "",
      comments: {
        commentator: JSON.parse(localStorage.getItem("usermsg")).username,
        type: this.type,
        parentId: this.id
      },
      Allcomments: [{}]
    };
  },
  created() {
    this.fetch();
  },
  methods: {
    //获得一级评论的所有回复
    async fetch() {
      // console.log(this.id + "   " + this.type);
      const res = await this.$http1.get(
        `/getAllcommentsById/${this.id}/${this.type}`
      );
      this.Allcomments = res.data;
      if (this.Allcomments) {
        this.Allcomments.reverse();
      }
      //js在控制台输出[object,object]的情况,那么在console.log里面不要用+号
      // console.log(res.data);
    },

    //上传回复
    async submit() {
      this.comments.content = this.input;
      let res = await this.$http1.post(`/pushComments`, this.comments);
      if (res.data.code == 200) {
        this.$message({
          type: "success",
          message: "回复成功!"
        });
      } else {
        this.$message({
          type: "error",
          message: res.data.message
        });
      }

      //评论上传之后直接在回调数组中添加实时评论
      let obj = {
        user: {
          avatarUrl: JSON.parse(localStorage.getItem("usermsg")).avatarUrl
        },
        gmtCreate: new Date().getTime(),
        likeCount: 0,
        commentator: this.commentator,
        content: this.comments.content
      };

      this.Allcomments.unshift(obj);
    }
  }
};
</script>
