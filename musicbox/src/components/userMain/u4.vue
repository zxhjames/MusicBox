<template>
  <div>
    <vue-editor v-model="content" /><button @click="submit()">提交</button>
  </div>
</template>
<script>
export default {
  data() {
    return {
      content: ""
    };
  },

  methods: {
    async submit() {
      alert(this.content);
    }
  }
};
</script>
