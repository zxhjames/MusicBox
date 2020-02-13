//显示播放器
<template>
  <div class="bottombar" v-if="musicBox.flag">
    <aplayer
      :autoplay="true"
      :music="{
        title: musicBox.music.title,
        author: musicBox.music.author,
        url: musicBox.music.url,
        pic: musicBox.music.pic
      }"
    >
    </aplayer>
  </div>
</template>

<script>
import aplayer from "vue-aplayer";
export default {
  components: { aplayer: aplayer },
  props: {
    musicBox: {
      flag: { type: Boolean, required: true },
      music: {
        title: { type: Boolean, required: true },
        author: { type: Boolean, required: true },
        url: { type: String, required: true },
        lrc: { type: String, required: false },
        pic: { type: String, required: true }
      }
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
