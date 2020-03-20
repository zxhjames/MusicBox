<template>
  <div>
    <div class=" topbar bg-dark d-flex ai-center bar pb-2 pt-2">
      <m-userBar></m-userBar>
      <div class="pl-2 flex-1">
        <div class="text-white">{{ usermsg.username }}</div>
        <div class="text-white fs-xxs">
          LV.{{ usermsg.rank }}&nbsp;<span @click="dateCheck">{{
            this.check
          }}</span>
        </div>
      </div>
      <div><m-search></m-search></div>
    </div>

    <div class="bg-red pt-2 pb-2">
      <div class="nav d-flex text-white jc-around pb-1">
        <div class="nav-item">
          <!-- 表示首页一个链接 -->
          <router-link to="/Main" class="nav-link" tag="div">首页</router-link>
        </div>

        <div class="nav-item">
          <!-- 表示首页一个链接 -->
          <router-link to="/Community" class="nav-link" tag="div"
            >社区</router-link
          >
        </div>

        <div class="nav-item">
          <!-- 表示首页一个链接 -->
          <router-link to="/Store" class="nav-link" tag="div">宝贝</router-link>
        </div>
      </div>
    </div>

    <!-- 子路由 -->
    <router-view></router-view>
    <m-player></m-player>
  </div>
</template>

<script>
export default {
  //传入user
  data() {
    return {
      username: localStorage.getItem("usermsg").username,
      items: {},
      usermsg: JSON.parse(localStorage.getItem("usermsg")),
      check: localStorage.getItem("check")
    };
  },
  methods: {
    async dateCheck() {
      if (this.check == "已签到") {
        this.$message({
          message: "你已经签到了哦",
          type: "warn"
        });
      } else {
        await this.$http1.get(`/pushDateOnline/${this.usermsg.username}`);
        localStorage.setItem("check", "已签到");
        this.check = "已签到";
        this.$message({
          message: "签到成功!坚持每日登录有机会抽奖哦",
          type: "success"
        });
      }
    }
  }
};
</script>
<style lang="scss">
.topbar {
  position: sticky;
  top: 0;
  z-index: 999;
}
</style>
