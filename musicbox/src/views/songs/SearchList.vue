<template>
  <div class="pl-3 pr-4">
    <table class="table table-condensed">
      <tr border="1px" v-for="(item, index) in list" :key="index">
        <th>
          <hr />
          <span>
            <div>
              <font size="2rem" color="grey">{{ item.name }}</font>
            </div></span
          >
        </th>
        <hr />
        <span v-for="elem in item.artists" :key="elem">
          <font size="1rem" color="grey">{{ elem.name }}</font>
        </span>
        <th>
          <div class="pt-5">
            <i @click="audio(item.id)" class="el-icon-video-play"></i>
          </div>
        </th>
      </tr>
    </table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      list: []
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
