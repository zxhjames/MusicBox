<template>
  <div>
    <el-tabs type="border-card">
      <div v-for="(item, index) in data" :key="index">
        <div class="box-card">
          <div slot="header" class="clearfix text-grey">
            <div class=" d-flex ai-center ">
              <el-avatar
                :src="item.picUrl"
                :size="50"
                type="primary"
                shape="square"
                style="margin-left: 0px;"
              />

              <div class="pl-2 flex-1">
                <div class="text-black fs-x pb-3">{{ item.name }}</div>
                <span
                  class="text-grey fs-xs pb-3"
                  v-for="it in item.song.artists"
                  :key="it"
                  >{{ it.name }}&nbsp;</span
                >
                <el-divider direction="vertical"></el-divider>
                <span class="text-grey fs-xs pb-3"
                  >[{{ item.song.album.name }}]</span
                >
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
    </el-tabs>
  </div>
</template>
<script>
export default {
  data() {
    return { data: [] };
  },
  created() {
    this.fetch();
  },
  methods: {
    async fetch() {
      let res = await this.$http.get("/personalized/newsong");
      this.data = res.data.result;
    },
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
