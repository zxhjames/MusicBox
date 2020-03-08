<template>
  <!-- 显示所有动态 -->
  <div>
    <div
      v-for="(item, index) in this.actions"
      :key="index"
      style="margin-bottom:3rem"
    >
      <m-action :item="item" :user="item.user"></m-action>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return { actions: [{}], user: {} };
  },
  created() {
    this.fetch();
  },
  methods: {
    //获取所有的用户动态
    async fetch() {
      let res = await this.$http1.get(`/getAllUserActions`);
      this.actions = res.data;
      // this.user = this.actions.user;
      // console.log(this.actions);
    }
  }
};
</script>
