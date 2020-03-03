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
    <el-divider></el-divider>
    <!-- 一级评论 -->
    <div v-for="(item, index) in this.Allcomments" :key="index">
      <!-- {{ item.id }}...{{ item.type }} -->
      <div class="d-flex pt-3">
        <div class=" d-flex ai-center ">
          <el-avatar
            :src="item.user.avatarUrl"
            shape="square"
            :size="30"
            type="primary"
            style="margin-left: 0px;"
          />
          <div class="pl-2 flex-1">
            <div class="text-black">{{ item.commentator }}</div>
            <div class="text-grey fs-xxs ">
              {{ new Date(item.gmtCreate).toLocaleString() }}
              <svg class="icon1" aria-hidden="true" style="margin-left:10px">
                <use xlink:href="#icon-dianzan1"></use>
              </svg>
              {{ item.likeCount }}
            </div>
          </div>
        </div>
      </div>

      <div>
        <div class="d-flex pt-2">
          <div class=" d-flex ai-center pl-0">
            <div class="pl-0 flex-1">
              <!--加上这个样式实现换行功能 -->
              <div
                class="text-black"
                style="word-wrap:break-word; word-break:break-all;"
              >
                {{ item.content }}
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="pt-2">
        <el-collapse v-model="activeName">
          <el-collapse-item>
            <template slot="title">
              <el-button
                type="text"
                class="text-grey fs-xxs"
                @click="openSecondCommentBoard()"
                >所有回复
              </el-button>
            </template>
            <div>
              <!-- 二级评论插槽 -->
              <m-secondcomments :id="item.id" :type="1"></m-secondcomments>
            </div>
          </el-collapse-item>
        </el-collapse>
      </div>
      <el-divider></el-divider>
    </div>
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
      mark: false,
      textarea: "",
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
    async submit() {
      this.comments.content = this.textarea;
      // console.log(this.comments);
      let res = await this.$http1.post(`/pushComments`, this.comments);
      // console.log(res.data);

      //页面临时刷新数据，如何把数据放到数组前面？防止用户多次刷新
      let obj = {
        user: {
          avatarUrl: JSON.parse(localStorage.getItem("usermsg")).avatar
        },
        gmtCreate: new Date().getTime(),
        likeCount: 0,
        commentator: this.commentator,
        content: this.comments.content
      };

      //插入到数组的前面
      this.Allcomments.unshift(obj);

      if (res.data.code == 200) {
        this.$message({
          type: "success",
          message: "评论成功!"
        });
        this.clear();
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

    //展开二级评论版
    openSecondCommentBoard() {
      this.mark = !this.mark;
    },

    async fetch() {
      //获取这个动态的所有评论,传递两个参数一个 id 一个type
      const res = await this.$http1.get(
        `/getAllcommentsById/${this.id}/${this.type}`
      );
      //要将数组转换为json
      this.Allcomments = res.data;
      this.Allcomments.reverse();
    }
  }
};
</script>

<style>
/* 自定义折叠板样式 */
.el-collapse-item__header {
  height: 20px;
}

.el-icon-arrow-right:before {
  content: "";
}

.el-collapse-item__header {
  border-bottom: 0;
  border-top: 0;
}

.el-collapse {
  border-bottom: 0px solid #ebeef5;
}
</style>
