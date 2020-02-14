<template>
  <div>
    <!-- <span>{{ id }}</span> -->
    <div
      class="nav-icons bg-white mt-3 d-flex flex-wrap text-center pt-3 text-dark"
    >
      <div class="flexbox" style="margin-right:3%;margin-left:1%">
        <i class="el-icon-back" @click="change('back')"></i>
      </div>
      <el-col
        :span="4"
        style="margin-right:2%;margin-left:2%"
        v-for="item in singer.artists"
        :key="item"
        :offset="index > 0 ? 2 : 0"
      >
        <router-link :to="{ path: 'singer', query: { id: item.id } }"
          ><el-card :body-style="{ padding: '0' }">
            <img :src="item.img1v1Url" class="image" />
            <!-- 字体大小如何自适应 -->
            <font size="1%">{{ item.name }}</font>
          </el-card>
        </router-link>
      </el-col>

      <div class="flexbox" style="margin-right:1%;margin-left:3%">
        <i class="el-icon-right" @click="change('next')"></i>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    id: { type: Number, required: true }
  },

  data() {
    return {
      singer: [],
      cur: this.COMMON.Page.singerPage
    };
  },

  created() {
    this.fetch();
  },

  methods: {
    async fetch() {
      await this.$http
        .get("/artist/list", {
          params: {
            cat: this.id,
            limit: 4,
            offset: this.COMMON.Page.singerPage
          }
        })
        .then(res => {
          this.singer = res.data;
        });
    },

    change(status) {
      if (status == "back" && this.COMMON.Page.singerPage >= 4) {
        this.COMMON.Page.singerPage -= 4;
      } else if (status == "next") {
        this.COMMON.Page.singerPage += 4;
      }
      // this.COMMON.Page.singerPage = status;
      // this.$forceUpdate();
      //暂时没有想到更好的方法，我想要的是局部刷新
      this.fetch();
    }
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

/* 显示图片 */
<style>
.time {
  font-size: 11px;
  color: #999;
}

.bottom {
  margin-top: 5px;
  line-height: 12px;
}

.button {
  padding: 0;
  float: right;
}

.image {
  width: 100%;
  display: block;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both;
}

.flexbox {
  display: -webkit-flex;
  display: flex;
  -webkit-align-items: center;
  align-items: center;
  -webkit-justify-content: center;
  justify-content: center;
}
</style>
