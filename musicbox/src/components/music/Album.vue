<template>
  <div>
    <swiper :options="swiperOption">
      <swiper-slide class="pb-1" v-for="sub in this.data" :key="sub">
        <div
          v-for="item in sub"
          :key="item"
          style="display:inline-block;margin:1%;width:60px;height:60px;
      "
        >
          <router-link :to="{ path: '/album', query: { id: item.id } }">
            <el-avatar :src="item.coverImgUrl" type="primary" shape="square" />
          </router-link>
          <div class="text-grey fs-xxs">{{ item.name.slice(0, 5) }}...</div>
        </div>
      </swiper-slide>
    </swiper>
  </div>
</template>
<script>
export default {
  data() {
    return {
      data: [],
      swiperOption: {
        slidesPerView: "auto",
        centeredSlides: true,
        spaceBetween: 10,
        loop: true,
        speed: 600 //config参数同swiper4,与官网一致
      }
    };
  },
  created() {
    this.fetch();
  },
  methods: {
    async fetch() {
      let res = await this.$http.get("/top/playlist", {
        params: {
          offset: 0,
          limit: 40
        }
      });
      for (let i = 0; i < 4; ++i) {
        let begin = i * 10;
        let end = (i + 1) * 10;
        this.data.push(res.data.playlists.slice(begin, end));
      }
    }
  }
};
</script>
<style>
.image {
  width: 100%;
  display: block;
}
.M-box {
  width: 1280px;
  text-align: center;
}
.M-box2 {
  display: inline-block;
}
</style>
