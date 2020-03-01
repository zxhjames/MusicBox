<template>
  <div>
    <div
      v-for="(item, index) in this.actions"
      :key="index"
      style="margin-bottom:3rem"
    >
      <m-action :item="item"></m-action>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      username: JSON.parse(localStorage.getItem("usermsg")).username,
      actions: [],
      len: ""
    };
  },

  created() {
    this.fetch();
  },
  methods: {
    async fetch() {
      //获得用户所有的动态
      let res = await this.$http1.get(`/getUserActions/${this.username}`);
      this.actions = res.data;
      console.log(res.data);
    }
  }
};
</script>
