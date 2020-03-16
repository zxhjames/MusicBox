<template>
  <div>
    <div class="box-card">
      <div slot="header" class="clearfix text-grey">
        <div class=" d-flex ai-center ">
          <el-avatar
            :src="item.artist.picUrl"
            :size="40"
            type="primary"
            shape="square"
            style="margin-left: 0px;"
          />

          <div class="pl-2 flex-1">
            <div class="text-black fs-s pb-3">{{ item.name }}</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  props: {
    item: {
      type: Object,
      required: true
    }
  },
  data() {
    return {};
  }
};
</script>
