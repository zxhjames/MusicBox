<script>
//全局数据，存储歌曲的信息
const MusicUrl = "https://music.163.com/song/media/outer/url?id=";
const GrobalMusic = {
  flag: false,
  music: {
    title: "", //当前音乐标题
    author: "", //当前音乐歌手
    url: "", //当前音乐url
    lrc: "", //当前音乐歌词
    pic: "" //当前音乐的图片
  }
};
export default {
  MusicUrl, //网易云音乐接口
  GrobalMusic //aplayer是否全局显示
};
</script>
