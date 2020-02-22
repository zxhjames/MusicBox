<template>
  <div class>
    <el-container>
      <!-- 歌手头像 -->
      <el-main>
        <img
          :src="album.playlist.coverImgUrl"
          width="100px"
          height="100px"
          class="img-circle"
        />
        <p><i class="el-icon-user"></i> {{ album.playlist.name }}</p>
        <!-- {{ this.$store.getters.getCurMusic.title }} -->
      </el-main>
    </el-container>

    <!-- 要不要做个组件 -->
    <el-tabs type="border-card">
      <el-tab-pane label="歌手歌曲">
        <table class="table table-condensed">
          <tr border="1px" v-for="item in album.playlist.tracks" :key="item">
            <th>
              <hr />
              <span
                ><div>{{ item.name }}</div></span
              >
            </th>
            <th>
              <div class="pt-5">
                <i @click="audio(item.id)" class="el-icon-video-play"></i>
              </div>
            </th>
          </tr>
        </table>
      </el-tab-pane>
    </el-tabs>
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
      musicBox: {
        flag: false,
        music: {
          title: "",
          author: "",
          url: "",
          lrc: "",
          pic: ""
        }
      },

      activeName: "first",
      album: []
      //mv播放器的配置
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
          "/playlist/detail",
          {
            params: {
              id: this.$route.query.id
            }
          },
          this.album
        )
        .then(res => {
          this.album = res.data;
        });
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
          // this.musicBox.flag = true;
          // this.musicBox.music.pic = res.data.songs[0].al.picUrl;
          // this.musicBox.music.title = res.data.songs[0].name;
          // this.musicBox.music.artist = res.data.songs[0].ar[0].name;
          // this.musicBox.music.src = this.$store.state.MusicUrl + id + ".mp3";
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
