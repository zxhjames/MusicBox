<template>
  <div>
    <el-card class="box-card">
      <div slot="header" class="clearfix text-grey">{{ name }}</div>
      <video-player
        class="video-player-box"
        ref="videoPlayer"
        :options="playerOptions"
        :playsinline="true"
        style="object-fit:fill"
        :x5-video-player-fullscreen="true"
        customEventName="customstatechangedeventname"
      >
      </video-player>
    </el-card>
  </div>
</template>

<script>
export default {
  props: {
    id: { type: Number, required: true },
    name: { type: String, required: true },
    pic: { type: String, required: true }
  },
  data() {
    return {
      // url: [],
      playerOptions: {
        playbackRates: [0.7, 1.0, 1.5, 2.0], //播放速度
        autoplay: false, //如果true,浏览器准备好时开始回放。
        muted: true, // 默认情况下将会消除任何音频。
        loop: false, // 导致视频一结束就重新开始。
        preload: "auto", // 建议浏览器在<video>加载元素后是否应该开始下载视频数据。auto浏览器选择最佳行为,立即开始加载视频（如果浏览器支持）
        language: "zh-CN",
        aspectRatio: "16:9", // 将播放器置于流畅模式，并在计算播放器的动态大小时使用该值。值应该代表一个比例 - 用冒号分隔的两个数字（例如"16:9"或"4:3"）
        fluid: true, // 当true时，Video.js player将拥有流体大小。换句话说，它将按比例缩放以适应其容器。
        sources: [
          {
            type: "video/mp4",
            src: ""
          }
        ],
        poster: this.pic,
        // notSupportedMessage: "此视频暂无法播放，请稍后再试", //允许覆盖Video.js无法播放媒体源时显示的默认信息。
        controlBar: {
          timeDivider: true,
          durationDisplay: true,
          remainingTimeDisplay: false,
          fullscreenToggle: true //全屏按钮
        }
      }
    };
  },
  created() {
    this.fetch();
  },
  computed: {
    player() {
      return this.$refs.videoPlayer.player;
    }
  },
  methods: {
    async fetch() {
      await this.$http
        .get("/mv/url", {
          params: {
            id: this.id
          }
        })
        .then(res => {
          // this.url = res.data.data.url;
          //   console.log(this.url);
          this.playerOptions.sources[0].src = res.data.data.url;
          this.playerOptions.poster = this.pic;
        });
    }
  }
};
</script>
