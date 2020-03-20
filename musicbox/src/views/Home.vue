<template>
  <!-- 实现广告幻灯片 vue-swiper 轮播逐渐 -->
  <div>
    <!-- 轮播图 -->
    <m-banner></m-banner>
    <div
      class="nav-icons bg-white mt-3 d-flex flex-wrap text-center pt-3 text-dark"
    >
      <div class="nav-item mb-3">
        <router-link :to="{ path: '/recommendSongs' }">
          <img src="../assets/images/音乐.png" height="30" />

          <div class="text-gray fs-xs">推荐歌曲</div>
        </router-link>
      </div>

      <div class="nav-item mb-3">
        <img src="../assets/images/社区.png" height="30" />
        <div class="text-gray fs-xs">云村MV</div>
      </div>

      <div class="nav-item mb-3">
        <img src="../assets/images/新闻.png" height="30" />
        <div class="text-gray fs-xs">云村音讯</div>
      </div>

      <div class="nav-item mb-3">
        <img src="../assets/images/关于.png" height="30" />
        <div class="text-gray fs-xs">关于作者</div>
      </div>
    </div>

    <m-card icon="yinyue" title="热门歌手">
      <!-- 显示歌手 -->
      <m-hotsinger></m-hotsinger>
      <!-- <m-singer-pane :id=""></m-singer-pane> -->
    </m-card>

    <!-- 显示歌手 -->
    <!-- params : {id  分类id ,  curPage 当前页面} -->
    <m-card icon="yinyue" title="歌手分类">
      <el-row>
        <el-col :span="4" v-for="item in this.singerCategories" :key="item"
          ><div class=" grid-content fs-xxs display:inline-block;">
            <router-link
              :to="{
                path: 'singerCategory',
                query: { id: item.id }
              }"
            >
              {{ item.name }}
            </router-link>
          </div>
        </el-col>
      </el-row>
    </m-card>

    <m-card icon="yinyue" title="精选专辑">
      <m-album></m-album>
    </m-card>

    <m-card icon="yinyue" title="每日榜单">
      <el-row>
        <el-col :span="8" v-for="item in this.topRank" :key="item"
          ><div class=" grid-content fs-xxs display:inline-block;">
            <router-link
              :to="{
                path: 'TopRank',
                query: { id: item.id }
              }"
            >
              {{ item.name }}
            </router-link>
          </div>
        </el-col>
      </el-row>
    </m-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
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
          name: "华语组合",
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
        }
      ],
      topRank: [
        { id: 0, name: "云音乐新歌榜" },
        { id: 1, name: "云音乐热歌榜" },
        { id: 2, name: "原创歌曲榜" },
        { id: 3, name: "云音乐飙升榜" },
        { id: 4, name: "云音乐电音榜" },
        { id: 5, name: "UK排行榜周榜" },
        { id: 6, name: "美国Billboard周榜" },
        { id: 7, name: "KTV嗨榜" },
        { id: 8, name: "iTunes榜" },
        { id: 9, name: "Hit榜" },
        { id: 10, name: "日本Oricon周榜" },
        { id: 11, name: "韩国Melon排行榜周榜" },
        { id: 12, name: "韩国Mnet排行榜周榜" },
        { id: 13, name: "韩国Melon原声周榜" },
        { id: 14, name: "中国TOP排行榜(港台榜)" },
        { id: 15, name: "中国TOP排行榜(内地榜)" },
        { id: 16, name: "香港电台中文歌曲龙虎榜" },
        { id: 17, name: "华语金曲榜" }
        // { id: 19, name: "法国 NRJ EuroHot 30周榜" },
        // { id: 20, name: "台湾Hito排行榜" },
        // { id: 21, name: "Beatport全球电子舞曲榜" },
        // { id: 22, name: "云音乐ACG音乐榜" },
        // { id: 23, name: "云音乐说唱榜" },
        // { id: 24, name: "云音乐古典音乐榜" },
        // { id: 25, name: "云音乐电音榜" },
        // { id: 26, name: "抖音排行榜" },
        // { id: 27, name: "新声榜" },
        // { id: 28, name: "云音乐韩语榜" },
        // { id: 29, name: "英国Q杂志中文版周榜" },
        // { id: 30, name: "电竞音乐榜" },
        // { id: 31, name: "云音乐欧美热歌榜" },
        // { id: 32, name: "云音乐欧美新歌榜" },
        // { id: 33, name: "说唱TOP榜" }
      ],
      mutter: "a"
    };
  }
};
</script>

<style>
.el-row {
  margin-bottom: 0px;
  &:last-child {
    margin-bottom: 0;
  }
}
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: #99a9bf;
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  min-height: 20px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
</style>
