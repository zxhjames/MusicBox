<template>
  <div>
    <el-container>
      <!-- 歌手头像 -->
      <el-main>
        <img
          :src="this.usermsg.avatarUrl"
          width="100px"
          height="100px"
          class="img-circle"
        />
        <p>
          <i class="el-icon-user">{{ this.usermsg.username }}</i>
        </p>
      </el-main>

      <el-footer
        ><span>关注 {{ this.usermsg.concerns }}</span>
        <el-divider direction="vertical"></el-divider>
        <span>粉丝 {{ this.usermsg.followers }}</span>
        <el-divider direction="vertical"></el-divider>
        <!-- <span>
          <el-button
            id="Concern"
            type="primary"
            :size="mini"
            style="padding:0.5rem"
            @click="concern"
            >{{ this.status }}</el-button
          >
        </span> -->
      </el-footer>
    </el-container>

    <!-- 要不要做个组件 -->
    <el-tabs type="border-card">
      <el-tab-pane>
        <span slot="label"><i class="el-icon-s-home"></i> 曲库</span>
        <m-u1></m-u1>
      </el-tab-pane>

      <el-tab-pane>
        <span slot="label"><i class="el-icon-s-custom"></i> 动态</span>
        <m-u2 :user="this.usermsg" :type="true"></m-u2>
      </el-tab-pane>
      <el-tab-pane>
        <span slot="label"><i class="el-icon-s-help"></i> 圈子</span>
        <m-u3 :username="this.usermsg.username"></m-u3>
      </el-tab-pane>
      <el-tab-pane>
        <span slot="label"><i class="el-icon-plus"></i> 发布</span>
        <m-u4></m-u4>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
<script>
export default {
  data() {
    return {
      status: "关注他",
      usermsg: JSON.parse(localStorage.getItem("usermsg"))
    };
  },
  methods: {
    concern() {
      if (this.status == "关注他") {
        this.status = "已关注";
        document.getElementById("Concern").style.backgroundColor = "lightcoral";
      } else {
        this.status = "关注他";
        //取消这个操作
        document.getElementById("Concern").style.backgroundColor = "#409eff";
      }
    }
  }
};
</script>
<style>
.bg {
  background-color: rgb(226, 214, 188);
}
/* .table {
  border-collapse: collapse;
  border-spacing: 0;
} */
.el-header,
.el-footer {
  background-color: #e9eef3;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #d3dce6;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  background-color: #e9eef3;
  color: #333;
  text-align: center;
  line-height: 100px;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}

.el-button--primary {
  background-color: #409eff;
  border-color: #409eff;
}
</style>
