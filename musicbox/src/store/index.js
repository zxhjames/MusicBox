import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = new Vuex.Store({
    state: {
        resources: "http://localhost:8081/img/user/",
        //音乐url的Api
        MusicUrl: "https://music.163.com/song/media/outer/url?id=",
        //当前播放信息
        GrobalMusic: {
            flag: false, //播放板显示状态
            playinglist: [], //当前播放列表
            currentIndex: -1, //当前播放器歌曲索引
            mode: {
                sequence: 0,
                circle: 1,
                random: 2
            },
            music: {
                title: "", //当前音乐标题
                artist: "", //当前音乐歌手
                src: "", //当前音乐url
                lrc: "", //当前音乐歌词
                pic: "" //当前音乐的图片
            }
        },
        //歌手分页
        Page: {
            singerPage: 1,
            musicAlbum: 1
        },
        //专辑分页
        Page1: {
            albumPage: 1
        }
    },
    getters: {
        getFlag: state => state.GrobalMusic.flag,
        getCurMusic: state => state.GrobalMusic.playinglist,
        getMusicIndex: state => state.GrobalMusic.currentIndex
    },
    mutations: {
        changeMusic(state, cur) {
            //切换当前音乐
            state.GrobalMusic.flag = cur.flag
            state.GrobalMusic.music.title = cur.music.title;
            state.GrobalMusic.music.artist = cur.music.artist;
            state.GrobalMusic.music.src = cur.music.src;
            state.GrobalMusic.music.pic = cur.music.pic;
            state.GrobalMusic.music.lrc = cur.music.lrc;
        },
        Filter(arr, url) {
            let hash = {};
            return arr.reduce(function (item, next) {
                hash[next[url]] ? '' : hash[next[name]] == true && item.push(next)
                return item;
            }, []);
        },
        addMusic(state, cur) {
            //切换音乐后触发一个添加歌曲列表的事件，考虑去重
            state.GrobalMusic.flag = true;
            let len = state.GrobalMusic.playinglist.length;
            for (let i = 0; i < len; ++i) {
                if (state.GrobalMusic.playinglist[i].src === cur.src) {
                    state.GrobalMusic.currentIndex = i;
                    return;
                }
            }
            state.GrobalMusic.playinglist.push(cur);
            ++state.GrobalMusic.currentIndex;
        },
        //切换歌曲
        operateMusic(state, status) {
            if (status == 'next' && store.getters.getMusicIndex > 0) {
                --state.GrobalMusic.currentIndex;
            } else if (status == 'back' && store.getters.getMusicIndex < store.state.GrobalMusic.playinglist.length - 1) {
                ++state.GrobalMusic.currentIndex;
            }
        },
        //每次歌曲播放完之后自动切换歌曲
        AutoChange(state) {
            state.GrobalMusic.currentIndex > 0 ? state.GrobalMusic.currentIndex-- : '';
        }
    }

})

export default store