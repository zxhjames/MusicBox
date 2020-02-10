<template>
  <div>
    <!-- <span>{{ id }}</span> -->
    <div
      class="nav-icons bg-white mt-3 d-flex flex-wrap text-center pt-3 text-dark"
    >
      <el-col
        :span="6"
        v-for="item in singer.artists"
        :key="item"
        :offset="index > 0 ? 2 : 0"
      >
        <el-card :body-style="{ padding: '3px' }" class="bgwidth">
          <img :src="item.img1v1Url" class="image" />
          {{ item.name }}
        </el-card>
      </el-col>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    id: { type: Number, required: true }
  },

  data() {
    return {
      singer: []
    };
  },

  created() {
    this.fetch();
  },

  methods: {
    async fetch() {
      await this.$http
        .get("/artist/list", {
          params: {
            cat: this.id,
            limit: 4
          }
        })
        .then(res => {
          this.singer = res.data;
        });
    }
  }
};
</script>
