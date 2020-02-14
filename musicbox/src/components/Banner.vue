<template>
  <!-- 显示首页广告 -->
  <div>
    <el-carousel height="300">
      <!-- 获取数组长度 -->
      <el-carousel-item v-for="item in Banners.banners.slice(4)" :key="item">
        <img class="w-100" :src="item.pic" height="300" />
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script>
export default {
  // props: {
  //   //这里可以传递json吗
  //   //接受nodejs传来的banner
  //   bannerJson: { type: JSON, required: true }
  // },
  data() {
    return {
      Banners: []
    };
  },
  created() {
    this.fetch();
  },
  methods: {
    async fetch() {
      await this.$http
        .get("/banner", {
          params: {
            type: 2
          }
        })
        .then(res => {
          this.Banners = res.data;
        });
    }
  }
};
</script>
/* 轮播图 */
<style>
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
</style>
