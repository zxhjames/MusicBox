<template>
  <div>
    {{ this.item }}
    {{ this.usermsg }}
  </div>
</template>
<script>
export default {
  props: {
    item: {
      type: Object,
      required: true
    },
    usermsg: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      flag: false,
      time: "",
      pic: "",
      id: ""
    };
  },
  // created() {
  //   this.fetch();
  // },
  methods: {
    fetch() {
      //转换时间
      var unixTimestamp = new Date(this.item.gmtCreate);
      this.time = unixTimestamp.toLocaleString();
      this.id = parseInt(this.item.id);
    },
    showComments() {
      this.flag = !this.flag;
    }
  }
};
</script>
