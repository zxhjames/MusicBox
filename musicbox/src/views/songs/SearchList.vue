<template>
  <div>
    <div v-for="(item, index) in list" :key="index" class="pt-2">
      <div class="box-card">
        <div slot="header" class="clearfix text-grey">
          <div class=" d-flex ai-center ">
            <div class="pl-2 flex-1">
              <div class="text-black fs-x pb-3">{{ item.name }}</div>
              <span
                class="text-grey fs-xs pb-3"
                v-for="it in item.artists"
                :key="it"
              >
                <router-link :to="{ path: 'singer', query: { id: it.id } }">
                  {{ it.name }}</router-link
                >&nbsp;</span
              >
              <el-divider direction="vertical"></el-divider>
              <span class="text-grey fs-xs pb-3"> [{{ item.album.name }}]</span>
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
  </div>
</template>

<script>
export default {
  data() {
    return {
      list: [{}]
    };
  },
  created() {
    this.fetch();
  },
  methods: {
    async fetch() {
      await this.$http
        .get("/search", {
          params: {
            keywords: this.$route.query.text,
            limit: 15
          }
        })
        .then(res => {
          this.list = res.data.result.songs;
        });
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
