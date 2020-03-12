<template>
  <div>
    <el-container>
      <!-- 歌手头像 -->
      <el-main>
        <img
          :src="this.usermsg.avatarUrl"
          width="100px"
          height="100px"
          class="img-circle"
        />
        <p>
          <i class="el-icon-user">{{ this.usermsg.username }}</i>
        </p>
      </el-main>

      <el-footer
        ><span>关注 {{ this.usermsg.concerns }}</span>
        <el-divider direction="vertical"></el-divider>
        <span>粉丝 {{ this.usermsg.followers }}</span>
        <el-divider direction="vertical"></el-divider>
        <span>
          <el-button
            id="Concern"
            type="primary"
            :size="mini"
            style="padding:0.5rem"
            @click="concern"
            >{{ this.status }}</el-button
          >
        </span>
      </el-footer>
    </el-container>

    <!-- 要不要做个组件 -->
    <el-tabs type="border-card">
      <el-tab-pane>
        <span slot="label"><i class="el-icon-s-home"></i> ta的曲库</span>
        <m-u1></m-u1>
      </el-tab-pane>

      <el-tab-pane>
        <span slot="label"><i class="el-icon-s-custom"></i> 他的动态</span>
        <div
          v-for="item in this.actions"
          :key="item"
          style="margin-bottom:3rem"
        >
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <div class=" d-flex ai-center ">
                <el-avatar
                  :src="usermsg.avatarUrl"
                  height="40"
                  type="primary"
                  style="margin-left: 0px;"
                />

                <div class="pl-2 flex-1">
                  <div class="text-black">{{ item.title }}</div>
                  <div class="text-grey fs-xxs pr-1">
                    By&nbsp;{{ item.creator }} |时间:{{
                      new Date(item.gmtCreate).toLocaleString()
                    }}
                    |浏览:{{ item.viewCount }}
                  </div>
                </div>
              </div>
            </div>
            <div>
              <div v-if="item.type == 0">
                <div
                  class="text-black"
                  style="word-wrap:break-word; word-break:break-all;"
                >
                  <div v-html="item.content" class="content"></div>
                </div>
              </div>
              <div v-if="item.type == 1">
                <m-repost :sid="item.parentId"></m-repost>
              </div>
            </div>
            <div>
              <svg class="icon1" aria-hidden="true" style="margin-left:10px">
                <use xlink:href="#icon-dianzan1"></use>
              </svg>
              {{ item.likeCount }}

              <svg class="icon1" aria-hidden="true" style="margin-left:10px">
                <use xlink:href="#icon-zhuanfa2"></use>
              </svg>

              <el-collapse v-model="activeName">
                <el-collapse-item>
                  <template slot="title">
                    <el-button type="text" class="text-grey fs-s"
                      ><svg
                        class="icon1"
                        aria-hidden="true"
                        style="margin-left:10px"
                        @click="showComments()"
                      >
                        <use xlink:href="#icon-review"></use>
                      </svg>
                      {{ item.commentCount }}
                    </el-button>
                  </template>
                  <div>
                    <m-firstcomments :id="item.id" :type="0"></m-firstcomments>
                  </div>
                </el-collapse-item>
              </el-collapse>
            </div>
          </el-card>
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
<script>
export default {
  data() {
    return {
      flag: false,
      status: "关注他",
      usermsg: {},
      actions: [{}]
    };
  },
  created() {
    this.fetch();
  },

  methods: {
    async fetch() {
      //获取用户的所有信息
      let res = await this.$http1.get(
        `/getUserInfoByUsername/${this.$route.query.username}`
      );
      this.usermsg = res.data;
      console.log("我是usermsg");
      console.log(this.usermsg);
      const act = await this.$http1.get(
        `/getUserActions/${this.usermsg.username}`
      );
      this.actions = act.data;
      console.log("我是actions");
      console.log(this.actions);
    },
    concern() {
      if (this.status == "关注他") {
        this.status = "已关注";
        document.getElementById("Concern").style.backgroundColor = "lightcoral";
      } else {
        this.status = "关注他";
        document.getElementById("Concern").style.backgroundColor = "#409eff";
      }
    },
    showComments() {}
  }
};
</script>
<style>
.bg {
  background-color: rgb(226, 214, 188);
}
/* .table {
  border-collapse: collapse;
  border-spacing: 0;
} */
.el-header,
.el-footer {
  background-color: #e9eef3;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #d3dce6;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  background-color: #e9eef3;
  color: #333;
  text-align: center;
  line-height: 100px;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}

.el-button--primary {
  background-color: #409eff;
  border-color: #409eff;
}
</style>
