<template>
  <!-- 社区动态组件 -->
  <div>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <div class=" d-flex ai-center ">
          <router-link
            :to="{
              path: '/OthersMain',
              query: { username: this.item.creator }
            }"
          >
            <el-avatar
              :src="this.user.avatarUrl"
              height="40"
              type="primary"
              style="margin-left: 0px;"
            />
          </router-link>

          <div class="pl-2 flex-1">
            <div class="text-black">
              {{ this.item.title }}
              <svg
                class="icon1"
                aria-hidden="true"
                style="margin-left:10px"
                v-if="this.item.type == 1"
              >
                <use xlink:href="#icon-zhuanfa3"></use>
              </svg>
            </div>
            <div class="text-grey fs-xxs pr-1">
              By&nbsp;{{ this.item.creator }} |时间:{{
                new Date(item.gmtCreate).toLocaleString()
              }}
              |浏览:{{ this.item.viewCount }}
            </div>
          </div>
        </div>
      </div>
      <div class="pb-3">
        <div v-if="this.item.type == 0">
          <div
            class="text-black"
            style="word-wrap:break-word; word-break:break-all;"
          >
            <div v-html="this.item.content" class="content"></div>
          </div>
        </div>
        <div v-if="this.item.type == 1">
          <m-repost :sid="this.item.parentId"></m-repost>
        </div>
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
        <svg
          class="icon1"
          aria-hidden="true"
          style="margin-left:10px"
          @click="repost"
        >
          <use xlink:href="#icon-zhuanfa2"></use>
        </svg>
        {{ this.item.repostCount }}
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
    item: { type: Object, required: true },
    user: { type: Object, required: true }
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
    },

    //转发动态
    repost() {
      this.$confirm("确定要转发这条动态?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消"
      })
        .then(async () => {
          this.item.type = 1;
          this.item.parentId = this.item.id;
          this.item.creator = JSON.parse(
            localStorage.getItem("usermsg")
          ).username;
          let res = await this.$http1.post("/actions", this.item);
          if (res.data.code == 200) {
            this.$message({
              type: "success",
              message: "转发成功!"
            });
            this.$router.push("/Community");
          } else {
            this.$message({
              type: "error",
              message: res.data.message
            });
          }
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消转发"
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
