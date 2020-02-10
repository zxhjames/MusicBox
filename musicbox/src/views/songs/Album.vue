<template>
  <div>
    <span>{{ item.playlist.length() }}</span>
  </div>
</template>

<script>
export default {
  data() {
    return {
      item: []
    };
  },

  created() {
    this.fetch();
  },

  methods: {
    //   获取歌单详情
    async fetch() {
      await this.$http
        .get(
          "/playlist/detail",
          {
            params: {
              id: sessionStorage.getItem("albumId")
            }
          },
          this.item
        )
        .then(res => {
          this.item = res.data;
        });
    }
  }
};
</script>
