<template>
  <div>
    <el-container>
      <!-- 歌手头像 -->
      <el-main>
        <img
          :src="album.coverImgUrl"
          width="100px"
          height="100px"
          class="img-circle"
        />
        <p><i class="el-icon-user"></i> {{ album.name }}</p>
        <!-- {{ this.$store.getters.getCurMusic.title }} -->
      </el-main>
    </el-container>

    <!-- 要不要做个组件 -->
    <el-tabs type="border-card">
      <el-tab-pane label="歌手歌曲">
        <div v-for="(item, index) in album.tracks" :key="index">
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
    </el-tabs>
  </div>
</template>
<script>
export default {
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
      album: []
    };
  },
  created() {
    this.fetch();
  },
  methods: {
    async fetch() {
      let res = await this.$http.get("/top/list", {
        params: {
          idx: this.$route.query.id
        }
      });
      this.album = res.data.playlist;
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
