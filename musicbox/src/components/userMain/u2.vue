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
  props: {
    uname: { type: String, required: true }
    // type: { type: Boolean, required: true }
  },
  data() {
    return {
      actions: [],
      len: "",
      username: ""
    };
  },

  created() {
    this.fetch();
  },
  methods: {
    async fetch() {
      //获得用户所有的动态
      this.username = this.uname;
      let res = await this.$http1.get(`/getUserActions/${this.username}`);
      this.actions = res.data;
      console.log(this.actions);
    }
  }
};
</script>
