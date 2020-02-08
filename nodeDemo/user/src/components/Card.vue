<template>
  <div class="card p-3 bg-white mt-3">
    <div class="card-header d-flex ai-center pb-3">
      <i class="iconfont" :class="`icon-${icon}`"></i>
      <div class="fs-xxl flex-1 px-2">{{ title }}</div>
      <i class="iconfont icon-cainan"></i>
    </div>

    <div class="card-body pt-3">
      <!-- 插槽 -->
      <slot></slot>
    </div>
  </div>
</template>
<script>
export default {
  //标题组件必填
  props: {
    title: { type: String, required: true },
    icon: { type: String, required: true }
  }
};
</script>
<style lang="scss">
@import "../assets/scss/_variables.scss";
.card {
  .card-header {
    border-bottom: 1px solid $border-color;
  }

  border-bottom: 1px solid $border-color;
}
</style>
