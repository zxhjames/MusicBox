<template>
  <div class>
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

    <el-tabs type="border-card">
      <el-tab-pane label="歌手歌曲">
        <table class="table table-condensed">
          <tr
            border="1px"
            v-for="(item, index) in singer.hotSongs"
            :key="index"
          >
            <th>
              <span
                ><div>{{ item.name }}</div></span
              >
            </th>
            <th><i @click="audio(item.id)" class="el-icon-video-play"></i></th>
            <hr />
          </tr>
        </table>
      </el-tab-pane>
      <el-tab-pane label="歌手专辑"> </el-tab-pane>
      <el-tab-pane label="歌手Mv"> </el-tab-pane>
      <el-tab-pane label="歌手详情"> </el-tab-pane>
    </el-tabs>

    <m-player :musicBox="this.musicBox"></m-player>
    <!-- <div class="bottombar" v-if="this.COMMON.isShowPlayer">
      <aplayer
        :repeat="repeat - one"
        :autoplay="true"
        :music="{
          title: this.music.title,
          author: this.music.author,
          url: this.music.url,
          lrc: this.music.lrc,
          pic: this.music.pic
        }"
      >
      </aplayer>
    </div> -->
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
      singer: []
    };
  },

  created() {
    this.fetch();
  },

  methods: {
    //获取歌手详情与单曲
    async fetch() {
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
    },

    //异步播放
    audio(id) {
      this.$http
        .get("/song/detail", {
          params: {
            ids: id
          }
        })
        .then(res => {
          this.COMMON.GrobalMusic.flag = this.musicBox.flag = true;
          this.COMMON.GrobalMusic.music.pic = this.musicBox.music.pic =
            res.data.songs[0].al.picUrl;
          this.COMMON.GrobalMusic.music.title = this.musicBox.music.title =
            res.data.songs[0].name;
          this.COMMON.GrobalMusic.music.author = this.musicBox.music.author =
            res.data.songs[0].ar[0].name;
          this.COMMON.GrobalMusic.music.url = this.musicBox.music.url =
            this.COMMON.MusicUrl + id + ".mp3";

          // localStorage.setItem['flag'] = true;
          // localStorage.setItem['pic'] = res.data.songs[0].al.picUrl;
          // localStorage.setItem['title'] = res.data.songs[0].name;
          // localStorage.setItem['author'] = res.data.songs[0].ar[0].name;
          // localStorage.setItem['url'] = this.COMMON.MusicUrl + id + ".mp3";
        });
      // console.log(this.$store.GrobalMusic);
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
