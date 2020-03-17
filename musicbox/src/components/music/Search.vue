<template>
  <div>
    <el-select
      class="pt-1 pb-1 pr-2"
      style="width:150px"
      v-model="value"
      filterable="true"
      remote="true"
      reserve-keyword
      placeholder="歌曲名/歌手名"
      :remote-method="remoteMethod"
      :loading="loading"
    >
      <el-option v-for="item in options" :key="item.id" :value="item.name">
      </el-option>
    </el-select>

    <router-link
      @click.native="flushCom"
      :to="{ path: 'searchList', query: { text: this.value } }"
    >
      <el-button
        v-if="this.value.length"
        icon="el-icon-search"
        circle
      ></el-button
    ></router-link>
  </div>
</template>

<script>
export default {
  data() {
    return {
      options: [],
      value: [],
      loading: false
    };
  },
  mounted() {},
  methods: {
    remoteMethod(query) {
      if (query !== "") {
        this.$http
          .get("/search", {
            params: {
              keywords: query.trim()
            }
          })
          .then(res => {
            this.options = res.data.result.songs;
          });
      } else {
        this.options = [];
      }
    },
    flushCom: function() {
      //router.go(n)是路由的一个方法，意思是在history记录中前进或者后退多少步，0就表示还是当前，类似window.history.go(n)
      this.$router.go(0);
    }
  }
};
</script>
