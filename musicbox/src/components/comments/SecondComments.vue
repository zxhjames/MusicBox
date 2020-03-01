<template>
  <!-- 二级评论业务 -->
  <div>
    <div class="pl-5 text-black">
      <el-collapse v-model="activeNames" @change="handleChange">
        <el-collapse-item title="所有回复" name="1">
          <div>
            与现实生活一致：与现实生活的流程、逻辑保持一致，遵循用户习惯的语言和概念；
          </div>
          <div>
            在界面中一致：所有的元素和结构需保持一致，比如：设计样式、图标和文本、元素的位置等。
          </div>
        </el-collapse-item>
      </el-collapse>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    //上级评论的id
    // id: { type: Number, required: true }
  },
  data() {
    return {};
  }
};
</script>
