<template>
  <div>
    <el-row>
      <el-col :span="24"
        ><div class="grid-content">
          <div class=" d-flex ai-center bar pb-2 pt-2">
            <svg class="icon" aria-hidden="true">
              <use xlink:href="#icon-yinle"></use>
            </svg>

            <div class="pl-2 flex-1">
              <div class="text-gray">听歌排行</div>
              <div class="text-gray fs-xxs">累计听歌1首</div>
            </div>
          </div>
        </div>
        <div class="grid-content">
          <div class="  d-flex ai-center bar pb-2 pt-2">
            <svg class="icon" aria-hidden="true">
              <use xlink:href="#icon-yinle1"></use>
            </svg>

            <div class="pl-2 flex-1">
              <div class="text-gray">我喜欢的音乐</div>
              <div class="text-gray fs-xxs">100首</div>
            </div>
          </div>
        </div>

        <el-divider></el-divider>

        <div class="">
          <span class="text-black ">创建的歌单</span>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  data() {
    return {};
  }
};
</script>
