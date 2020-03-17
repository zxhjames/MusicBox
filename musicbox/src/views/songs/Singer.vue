<template>
  <div>
    <el-container>
      <!-- 歌手头像 -->
      <el-main>
        <img
          :src="singer.artist.picUrl"
          width="100px"
          height="100px"
          class="img-circle"
        />
        <p><i class="el-icon-user"></i> {{ singer.artist.name }}</p>
      </el-main>
    </el-container>

    <!-- 要不要做个组件 -->
    <el-tabs type="border-card">
      <el-tab-pane label="歌手歌曲">
        <div v-for="(item, index) in singer.hotSongs" :key="index">
          <div class="box-card">
            <div slot="header" class="clearfix text-grey">
              <div class=" d-flex ai-center ">
                <el-avatar
                  :src="item.al.picUrl"
                  :size="50"
                  type="primary"
                  shape="square"
                  style="margin-left: 0px;"
                />

                <div class="pl-2 flex-1">
                  <div class="text-black fs-s pb-3">{{ item.name }}</div>
                  <span v-for="it in item.ar" :key="it"
                    >{{ it.name }}&nbsp;</span
                  >
                  <el-divider direction="vertical"></el-divider>
                  [{{ item.al.name }}]
                </div>
                <el-button
                  icon="el-icon-video-play"
                  circle
                  @click="audio(item.id)"
                ></el-button>
              </div>
            </div>
          </div>
          <el-divider></el-divider>
        </div>
      </el-tab-pane>

      <el-tab-pane label="歌手专辑">
        <div v-for="(item, index) in this.album" :key="index">
          <div class="box-card">
            <div slot="header" class="clearfix text-grey">
              <div class=" d-flex ai-center ">
                <el-avatar
                  :src="item.blurPicUrl"
                  :size="50"
                  type="primary"
                  shape="square"
                  style="margin-left: 0px;"
                />

                <div class="pl-2 flex-1">
                  <div class="text-black fs-s pb-3">{{ item.name }}</div>
                  <span v-for="it in item.artists" :key="it"
                    >{{ it.name }}&nbsp;</span
                  >
                </div>

                <el-button
                  icon="el-icon-service"
                  circle
                  @click="getSongs(item.id, index)"
                ></el-button>
              </div>

              <div class="pt-3" v-if="flag">
                <el-card class="box-card">
                  <div v-for="item in songs" :key="item">
                    {{ item.al.name }}
                  </div>
                </el-card>
              </div>
            </div>
            <el-divider></el-divider>
          </div>
        </div>
      </el-tab-pane>

      <el-tab-pane label="歌手Mv">
        <div v-for="item in this.mvs" :key="item">
          <m-video :id="item.id" :name="item.name" :pic="item.imgurl"></m-video>
        </div>
      </el-tab-pane>
      <el-tab-pane label="歌手详情">
        <m-singerDetail :detail="this.singerDetail"></m-singerDetail>
      </el-tab-pane>
    </el-tabs>

    <!-- <m-player></m-player> -->
  </div>
</template>

<script>
// import aplayer from "vue-aplayer";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.min.js";
export default {
  // components: { aplayer: aplayer },
  data() {
    return {
      flag: false,
      musicBox: {
        flag: false,
        music: {
          title: "",
          artist: "",
          src: "",
          lrc: "",
          pic: ""
        }
      },

      activeName: "first",
      singer: [],
      album: [{}],
      songs: [{}],
      mvs: [],
      singerDetail: {}
    };
  },
  created() {
    this.fetch();
  },

  methods: {
    async fetch() {
      //获取歌手单 曲
      await this.$http
        .get(
          "/artists",
          {
            params: {
              id: this.$route.query.id
            }
          },
          this.singer
        )
        .then(res => {
          this.singer = res.data;
        });

      //获取歌手的专辑信息
      await this.$http
        .get("/artist/album", {
          params: {
            id: this.$route.query.id,
            limit: 10
          }
        })
        .then(res => {
          this.album = res.data.hotAlbums;
        });

      // 获取歌手mv
      const res = await this.$http.get("/artist/mv", {
        params: {
          id: this.$route.query.id
        }
      });
      this.mvs = res.data.mvs;

      //获取歌手的详细介绍
      const detail = await this.$http.get("/artist/desc", {
        params: {
          id: this.$route.query.id
        }
      });
      this.singerDetail = detail.data;
    },

    //获取专辑里面的所有歌曲
    async getSongs(id, index) {
      this.flag = !this.flag;
      alert(index);
      const res = await this.$http.get("/album", {
        params: {
          id: id
        }
      });
      this.songs = res.data.album;
    },

    //异步播放
    async audio(id) {
      let curMusic;
      await this.$http
        .get("/song/detail", {
          params: {
            ids: id
          }
        })
        .then(res => {
          //将歌曲加入队列并且开始播放
          curMusic = {
            pic: res.data.songs[0].al.picUrl,
            title: res.data.songs[0].name,
            artist: res.data.songs[0].ar[0].name,
            src: this.$store.state.MusicUrl + id + ".mp3",
            lrc: ""
          };
        });
      const res = await this.$http.get("/lyric?id=" + id);
      curMusic.lrc = res.data.lrc.lyric;
      await this.$store.commit("addMusic", curMusic);
    }
  }
};
</script>

<style>
.bg {
  background-color: #333;
}
/* .table {
  border-collapse: collapse;
  border-spacing: 0;
} */
.el-header,
.el-footer {
  background-color: #b3c0d1;
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
  line-height: 160px;
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
</style>
<style lang="scss">
.bottombar {
  position: sticky;
  bottom: 0;
  z-index: 999;
}
</style>
