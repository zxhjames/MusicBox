<template>
  <!-- 实现广告幻灯片 vue-swiper 轮播逐渐 -->
  <div>
    <swiper :options="swiperOption">
      <!-- slides -->
      <swiper-slide>
        <img
          class="w-100"
          src="../assets/images/109951164685321751.jpg"
          height="150"
        />
      </swiper-slide>
      <swiper-slide>
        <img
          class="w-100"
          src="../assets/images/109951164685321751.jpg"
          height="150"
        />
      </swiper-slide>
      <swiper-slide>
        <img
          class="w-100"
          src="../assets/images/109951164685321751.jpg"
          height="150"
        />
      </swiper-slide>
      <div
        class="swiper-pagination pagination-home text-right px-3 pb-2"
        slot="pagination"
      ></div>
    </swiper>
    <!-- end of swiper -->
    <div
      class="nav-icons bg-white mt-3 d-flex flex-wrap text-center pt-3 text-dark"
    >
      <div class="nav-item mb-3">
        <img src="../assets/images/音乐.png" height="30" />
        <div>今日新歌</div>
      </div>

      <div class="nav-item mb-3">
        <img src="../assets/images/社区.png" height="30" />
        <div>发现音乐</div>
      </div>

      <div class="nav-item mb-3">
        <img src="../assets/images/新闻.png" height="30" />
        <div>全球音讯</div>
      </div>

      <div class="nav-item mb-3">
        <img src="../assets/images/关于.png" height="30" />
        <div>关于我们</div>
      </div>
    </div>

    <!-- end of the bar -->

    <m-list-card icon="caidan" title="新闻咨询" :categories="newsCats">
      <template #items="{category}">
        <div class="py-2" v-for="(news, i) in category.newsList" :key="i">
          <span>[{{ news.categoryName }}}]</span>
          <span>|</span>
          <span>{{ news.title }}</span>
          <span>{{ news.date }}</span>
        </div>
      </template>
    </m-list-card>
    <m-card icon="yinyue" title="歌手列表"> </m-card>
    <m-card icon="caidan" title="歌手列表"> </m-card>
    <m-card icon="caidan" title="歌手列表"> </m-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      swiperOption: {
        pagination: {
          el: ".pagination-home"
        }
        // some swiper options...
      },
      newsCats: [
        {
          name: "热门",
          //eslint disable next line
          newsList: new Array(5).fill(1).map(v => ({
            categoryName: "公告",
            title: "今日不停服务器",
            date: "06/01"
          }))
        },
        {
          name: "新闻",
          //eslint disable next line
          newsList: new Array(5).fill(1).map(v => ({
            categoryName: "新闻",
            title: "今日不停服务器",
            date: "06/01"
          }))
        },
        {
          name: "热门",
          //eslint disable next line
          newsList: new Array(5).fill(1).map(v => ({
            categoryName: "公告",
            title: "今日不停服务器",
            date: "06/01"
          }))
        },
        {
          name: "热门",
          //eslint disable next line
          newsList: new Array(5).fill(1).map(v => ({
            categoryName: "公告",
            title: "今日不停服务器",
            date: "06/01"
          }))
        }
      ]
    };
  }
};
</script>

<style lang="scss">
@import "../assets/scss/_variables.scss";
.pagination-home {
  opacity: 1;
  border-radius: 0.1538rem;
  .swiper-pagination-bullet {
    background: map-get($colors, "white");
    &.swiper-pagination-bullet-active {
      background: map-get($colors, "info");
    }
  }
}

.nav-icons {
  .nav-item {
    width: 25%;
  }
}
</style>
