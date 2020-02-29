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
      // this.actions = res.data;
      // let len = res.data.length;
      // let obj;
      // for (let i = 0; i < len; ++i) {
      //   // console.log(i.creator);
      //   obj = {
      //     id: res.data[i].id,
      //     title: res.data[i].title,
      //     creator: res.data[i].creator,
      //     gmtCreate: res.data[i].gmtCreate,
      //     gmtModified: res.data[i].gmtModified,
      //     commentCount: res.data[i].commentCount,
      //     viewCount: res.data[i].viewCount,
      //     likeCount: res.data[i].likeCount,
      //     type: res.data[i].type,
      //     content: res.data[i].content
      //   };
      //   this.actions.push(obj);
      // }
      this.actions = res.data;
      console.log(res.data);
    }
  }
};
</script>
