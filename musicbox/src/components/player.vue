<template>
  <div class="bottombar" v-if="this.$store.getters.getFlag">
    <aplayer
      :autoplay="true"
      :showlrc="1"
      :mode="circle"
      :mutex="true"
      :music="{
        title: this.$store.getters.getCurMusic[
          this.$store.getters.getMusicIndex
        ].title,
        artist: this.$store.getters.getCurMusic[
          this.$store.getters.getMusicIndex
        ].artist,
        src: this.$store.getters.getCurMusic[this.$store.getters.getMusicIndex]
          .src,
        pic: this.$store.getters.getCurMusic[this.$store.getters.getMusicIndex]
          .pic,
        lrc: this.$store.getters.getCurMusic[this.$store.getters.getMusicIndex]
          .lrc
      }"
      @ended="AutoChange()"
    >
    </aplayer>

    <div class="swiper-button-next" @click="ChangeSong('back')"></div>
    <div class="swiper-button-prev" @click="ChangeSong('next')"></div>
  </div>
</template>

<script>
import aplayer from "vue-aplayer";
export default {
  components: { aplayer: aplayer },

  data() {
    return {
      swiperOption: {
        notNextTicl: true,
        loop: false,
        paginationClickable: true
      }
    };
  },
  created() {
    this.mounted();
  },

  methods: {
    async mounted() {
      let aplayer = this.$refs.player.control;
      //async   await 异步加载，先加载出player再使用
      aplayer.autoplay = true;
      this.swiperOption.initialSlide = this.$store.getters.getMusicIndex;
      aplayer.play();
    },
    async ChangeSong(status) {
      await this.$store.commit("operateMusic", status);
    },
    async AutoChange() {
      await this.$store.commit("AutoChange");
      // this.$forceUpdate();

      let aplayer = await this.$refs.player.control;
      //async   await 异步加载，先加载出player再使用
      aplayer.autoplay = true;
      aplayer.play();
    }
  }
};
</script>
<style lang="scss">
.bottombar {
  position: sticky;
  bottom: 0;
  z-index: 999;
}
</style>
