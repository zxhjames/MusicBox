<template>
  <!-- 实现广告幻灯片 vue-swiper 轮播逐渐 -->
  <div>
    <!-- 轮播图 -->
    <m-banner></m-banner>
    <m-bar></m-bar>
    <!-- 显示歌手 -->
    <!-- params : {id  分类id ,  curPage 当前页面} -->
    <m-card icon="yinyue" title="歌手分类">
      <el-tabs v-model="activeName">
        <el-tab-pane
          :label="category.name"
          v-for="category in singerCategories"
          :key="category"
          @tab-click="showSinger"
        >
          <!-- 显示歌手 -->
          <m-singer-pane :id="category.id"></m-singer-pane>
        </el-tab-pane>
      </el-tabs>
    </m-card>

    <m-card icon="yinyue" title="歌单分类">
      <m-album></m-album>
    </m-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
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
      ],
      //歌单分类栏
      albumCategories: [
        {
          id: 1,
          name: "流行"
        },
        {
          id: 2,
          name: "摇滚"
        },
        {
          id: 1001,
          name: "民谣"
        },
        {
          id: 2004,
          name: "电子"
        },
        {
          id: 10001,
          name: "独立"
        },
        {
          id: 2008,
          name: "轻音乐"
        },
        {
          id: 9001,
          name: "影视原声"
        },
        {
          id: 11002,
          name: "ACG"
        },
        {
          id: 1031,
          name: "怀旧"
        }
      ]
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
