<template>
  <!-- 社区动态组件 -->
  <div>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <div class=" d-flex ai-center ">
          <el-avatar
            :src="this.pic"
            height="40"
            type="primary"
            style="margin-left: 0px;"
          />
          <div class="pl-2 flex-1">
            <div class="text-black">{{ this.item.title }}</div>
            <div class="text-grey fs-xxs pr-1">
              By&nbsp;{{ this.item.creator }} |时间:{{ this.time }} |浏览:{{
                this.item.viewCount
              }}
            </div>
          </div>
        </div>
      </div>
      <div>
        <div v-if="this.item.type == 0">
          <div
            class="text-black"
            style="word-wrap:break-word; word-break:break-all;"
          >
            <div v-html="this.item.content" class="content"></div>
          </div>
        </div>
        <div v-if="this.item.type == 1"><m-repost></m-repost></div>
      </div>
      <div>
        <svg class="icon1" aria-hidden="true" style="margin-left:10px">
          <use xlink:href="#icon-dianzan1"></use>
        </svg>
        {{ this.item.likeCount }}
        <svg
          class="icon1"
          aria-hidden="true"
          style="margin-left:10px"
          @click="showComments"
        >
          <use xlink:href="#icon-review"></use>
        </svg>
        {{ this.item.commentCount }}
        <svg class="icon1" aria-hidden="true" style="margin-left:10px">
          <use xlink:href="#icon-zhuanfa2"></use>
        </svg>
        <el-button
          v-if="!this.item.user"
          style="float: right; padding: 3px 0"
          type="text"
          @click.native="deleteAction"
          >删除</el-button
        >
      </div>

      <!-- 评论功能 -->
      <!-- flag面板状态(折叠/展开) id动态的id -->
      <div v-if="this.flag">
        <m-firstcomments :id="this.item.id" :type="0"></m-firstcomments>
      </div>
    </el-card>
  </div>
</template>
<script>
// import { formatDate } from "../utils/data";
export default {
  props: {
    item: { type: Object, required: true }
  },
  data() {
    return {
      flag: false,
      time: "",
      pic: "",
      id: ""
    };
  },
  created() {
    this.fetch();
  },
  methods: {
    fetch() {
      //转换时间
      var unixTimestamp = new Date(this.item.gmtCreate);
      this.time = unixTimestamp.toLocaleString();
      console.log(this.item);
      this.id = parseInt(this.item.id);
      //判断是用户打开自己动态还是用户打开社区动态
      if (!this.item.user) {
        this.pic = JSON.parse(localStorage.getItem("usermsg")).avatar;
      } else {
        this.pic = this.$store.state.resources + this.item.user.avatarUrl;
        // console.log(this.pic);
      }
    },
    showComments() {
      this.flag = !this.flag;
    },
    async deleteAction() {
      //删除动态
      this.$confirm("删除此条动态吗, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(async () => {
          //执行删除操作
          let res = await this.$http1.delete(`/deleteActions/${this.item.id}`);
          if (res.data.code == 200) {
            this.$message({
              type: "success",
              message: "删除成功!"
            });
          }
          this.$router.go(0);
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    }
  }
};
</script>
<style>
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both;
}

.box-card {
  width: 100%;
}
.content img {
  display: block;
  max-width: 100% !important;
  width: 100%;
}
</style>
