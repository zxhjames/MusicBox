<template>
  <div>
    <el-card class="box-card">
      <div slot="header" class="clearfix text-grey">
        人物介绍
      </div>
      {{ this.detail.briefDesc }}
    </el-card>
    <el-divider></el-divider>
    <el-card class="box-card">
      <div slot="header" class="clearfix text-grey">
        主要成就
      </div>
      {{ this.detail.introduction[1].txt }}
    </el-card>
  </div>
</template>
<script>
export default {
  props: {
    detail: {
      type: Object,
      required: true
    }
  },
  data() {
    return {};
  }
};
</script>
