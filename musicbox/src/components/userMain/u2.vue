<template>
  <div>
    <div
      v-for="(item, index) in this.actions"
      :key="index"
      style="margin-bottom:3rem"
    >
      <m-action :item="item" :user="user"></m-action>
    </div>
  </div>
</template>
<script>
export default {
  props: {
    user: { type: Object, required: true }
    // type: { type: Boolean, required: true }
  },
  data() {
    return {
      actions: [{}]
    };
  },

  created() {
    this.fetch();
  },
  methods: {
    async fetch() {
      //获得用户所有的动态
      const res = await this.$http1.get(
        `/getUserActions/${this.user.username}`
      );
      this.actions = res.data;
    }
  }
};
</script>
