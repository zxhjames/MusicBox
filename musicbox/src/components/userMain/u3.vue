<template>
  <div>
    <!-- 关注的好友动态 -->
    <div v-for="item in this.users" :key="item">
      <div class=" d-flex ai-center ">
        <router-link
          :to="{
            path: '/OthersMain',
            query: { username: item.username }
          }"
        >
          <el-avatar
            :src="item.avatarUrl"
            height="40"
            type="primary"
            shape="square"
            style="margin-left: 0px;"
          />
        </router-link>

        <div class="pl-2 flex-1 pt-0">
          <div class="text-blue pb-2">{{ item.username }}</div>
          <div class="text-grey fs-xs pr-1">
            关注:{{ item.concerns }}
            <el-divider direction="vertical"></el-divider>
            粉丝:{{ item.followers }}
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    username: { type: String, required: true }
  },
  data() {
    return {
      users: [{}]
    };
  },

  created() {
    this.fetch();
  },
  methods: {
    async fetch() {
      const res = await this.$http1.get(
        `/getRelation/${this.username}`,
        this.username
      );
      this.users = res.data;
    }
  }
};
</script>
