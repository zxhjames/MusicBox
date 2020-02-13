<template>
  <!-- 实现广告幻灯片 vue-swiper 轮播逐渐 -->
  <div>
    <!-- 轮播图 -->
    <m-banner></m-banner>
    <!-- end of swiper -->
    <!-- 分类栏 -->
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

    <!-- 显示歌手 -->
    <m-card icon="yinyue" title="歌手列表">
      <el-tabs v-model="activeName">
        <el-tab-pane
          :label="i.name"
          v-for="i in singerCategories"
          :key="i"
          @tab-click="showSinger"
        >
          <!-- 显示歌手 -->
          <m-singer-pane :id="i.id"></m-singer-pane>
        </el-tab-pane>
      </el-tabs>
    </m-card>

    <!-- <m-list-card icon="caidan" title="新闻咨询" :categories="singerCategories">
    </m-list-card> -->

    <m-card icon="yinyue" title="热门推荐">
      <div
        class="nav-icons bg-white mt-3 d-flex flex-wrap text-center pt-3 text-dark"
      >
        <el-col
          :span="6"
          v-for="item in album.playlists"
          :key="item"
          :offset="index > 0 ? 2 : 0"
        >
          <el-card :body-style="{ padding: '3px' }" class="bgwidth">
            <img :src="item.coverImgUrl" class="image" />
            {{ item.name }}
          </el-card>
        </el-col>
      </div>
    </m-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      //歌单
      album: [],
      //歌手
      singer: [],
      //歌手分类栏
      singerCategories: [
        {
          name: "华语男歌手",
          id: 1001
        },
        {
          name: "华语女歌手",
          id: 1002
        },
        {
          name: "华语组合/乐队",
          id: 1003
        },
        {
          name: "欧美男歌手",
          id: 2001
        },
        {
          name: "欧美女歌手",
          id: 2002
        },
        {
          name: "欧美组合",
          id: 2003
        },
        {
          name: "日本男歌手",
          id: 6001
        },
        {
          name: "日本女歌手",
          id: 6002
        },
        {
          name: "韩国男歌手",
          id: 7001
        },
        {
          name: "韩国女歌手",
          id: 7002
        },
        {
          name: "其他歌手",
          id: 1001
        }
      ]
      //广告栏
    };
  },
  created() {
    this.fetch();
  },

  methods: {
    async fetch() {
      // 获取最热歌单
      await this.$http
        .get(
          "/top/playlist/highquality",
          {
            params: {
              before: 1503639064232,
              limit: 4
            }
          },
          this.album
        )
        .then(res => {
          this.album = res.data;
        });
    }
  }
};
</script>
