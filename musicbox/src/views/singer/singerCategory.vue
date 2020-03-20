<template>
  <div>
    <div class="pl-4">
      <div
        v-for="item in this.data"
        :key="item"
        style="display:inline-block;margin:3%;width:60px;height:60px;
      "
      >
        <router-link :to="{ path: 'singer', query: { id: item.id } }">
          <el-avatar
            :src="item.picUrl"
            type="primary"
            shape="square"
            :size="50"
          />
        </router-link>
        <div class="text-grey fs-xxs ">
          {{ item.name }}
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      data: [{}]
      //   dict: [
      //     "A",
      //     "B",
      //     "C",
      //     "D",
      //     "E",
      //     "F",
      //     "G",
      //     "H",
      //     "I",
      //     "J",
      //     "K",
      //     "L",
      //     "M",
      //     "N",
      //     "O",
      //     "P",
      //     "Q",
      //     "R",
      //     "S",
      //     "T",
      //     "U",
      //     "V",
      //     "W",
      //     "X",
      //     "Y",
      //     "Z"
      //   ]
    };
  },
  created() {
    this.fetch();
  },
  methods: {
    async fetch() {
      let res = await this.$http.get("/artist/list", {
        params: {
          cat: this.$route.query.id,
          offset: 0,
          limit: 32
        }
      });
      this.data = res.data.artists;
    }
  }
};
</script>
