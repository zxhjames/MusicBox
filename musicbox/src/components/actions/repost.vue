<template>
  <!-- 显示用户转发动态的组件 -->
  <div>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <div class=" d-flex ai-center ">
          <router-link
            :to="{
              path: '/OthersMain',
              query: { username: this.item.user.username }
            }"
          >
            <el-avatar
              :src="this.item.user.avatarUrl"
              :size="30"
              type="primary"
              shape="square"
              style="margin-left: 0px;"
            />
          </router-link>

          <div class="pl-2 flex-1">
            <div class="text-black">{{ this.item.title }}</div>
            <div class="text-grey fs-xxs pr-1">
              By&nbsp;{{ this.item.creator }} |时间:{{
                new Date(this.item.gmtCreate).toLocaleString()
              }}
              |浏览:{{ this.item.viewCount }}
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
    sid: { type: Number, required: true }
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
      let res = await this.$http1.get(`/getRepostByParentId/${this.sid}`);
      this.item = res.data;
    }
  }
};
</script>
