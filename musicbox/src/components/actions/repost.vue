<template>
  <!-- 显示用户转发动态的组件 -->
  <div>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <div class=" d-flex ai-center ">
          <el-avatar
            :src="this.user.avatarUrl"
            height="40"
            type="primary"
            style="margin-left: 0px;"
          />

          <div class="pl-2 flex-1">
            <div class="text-black">{{ this.item.title }}</div>
            <div class="text-grey fs-xxs pr-1">
              By&nbsp;{{ this.item.creator }} |时间:{{ this.time }} |浏览:{{
                this.item.viewCount
              }}
            </div>
          </div>
        </div>
      </div>
      <div>
        <div
          class="text-black"
          style="word-wrap:break-word; word-break:break-all;"
        >
          <div v-html="this.item.content" class="content"></div>
        </div>
      </div>
    </el-card>
  </div>
</template>
<script>
export default {
  props: {
    username: { type: String, required: true }
  },
  data() {
    return {
      item: {}
    };
  },
  created() {
    this.fetch();
  },
  methods: {
    async fetch() {
      //获取单个动态
      // let res = await this.$http1.get(`/getU/${this.username}`);
      // this.item = res.data;
    }
  }
};
</script>
