<template>
  <div>
    <div v-for="item in this.songs" :key="item">
      <div class="box-card">
        <div slot="header" class="clearfix text-grey">
          <div class=" d-flex ai-center ">
            <el-avatar
              :src="item.al.picUrl"
              :size="50"
              type="primary"
              shape="square"
              style="margin-left: 0px;"
            />

            <div class="pl-2 flex-1">
              <div class="text-black fs-s pb-3">{{ item.al.name }}</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      songs: [{}]
    };
  },

  created() {
    this.fetch();
  },

  methods: {
    async fetch() {
      const res = await this.$http.get("/album", {
        params: {
          id: this.$route.query.id
        }
      });
      this.songs = res.data.songs;
    }
  }
};
</script>
