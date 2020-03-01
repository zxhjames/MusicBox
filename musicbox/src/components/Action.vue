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
              {{ this.item.creator }} |时间:{{ this.time }} |浏览数:{{
                this.item.viewCount
              }}
              |点赞数:{{ this.item.likeCount }} |评论数:{{
                this.item.commentCount
              }}
            </div>
          </div>
        </div>
      </div>
      <div>
        <div v-html="this.item.content" class="content">
          {{ this.item.content }}
        </div>
      </div>
      <div>
        <svg class="icon1" aria-hidden="true" style="margin-left:10px">
          <use xlink:href="#icon-dianzan"></use>
        </svg>
        <svg class="icon1" aria-hidden="true" style="margin-left:10px">
          <use xlink:href="#icon-pinglunqu"></use>
        </svg>
        <svg class="icon1" aria-hidden="true" style="margin-left:10px">
          <use xlink:href="#icon-zhuanfa1"></use>
        </svg>
        <el-button style="float: right; padding: 3px 10px" type="text"
          >编辑</el-button
        >
        <el-button style="float: right; padding: 3px 0" type="text"
          >删除</el-button
        >
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
      time: "",
      pic: ""
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
      //判断是用户打开自己动态还是用户打开社区动态
      if (!this.item.user) {
        this.pic = JSON.parse(localStorage.getItem("usermsg")).avatar;
      } else {
        this.pic = this.$store.state.resources + this.item.user.avatarUrl;
        console.log(this.pic);
      }
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
