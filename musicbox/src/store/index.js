import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = new Vuex.Store({
    MusicUrl: "https://music.163.com/song/media/outer/url?id=",
    GrobalMusic: {
        flag: false,
        music: {
            title: "", //当前音乐标题
            author: "", //当前音乐歌手
            url: "", //当前音乐url
            lrc: "", //当前音乐歌词
            pic: "" //当前音乐的图片
        }
    },
    Page: {
        singerPage: 1,
        musicAlbum: 1
    }
})

export default store