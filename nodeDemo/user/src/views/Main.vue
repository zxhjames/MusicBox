<template>
  <div>
    <div class="topbar bg-black py-2 px-3 d-flex ai-center">
      <img src="../assets/images/logo1.jpg" height="30" />
      <div class="pl-2 flex-1">
        <div class="text-white">云音乐</div>
        <div class="text-grey-1 fs-xxs">音乐改变人生</div>
      </div>

      <button type="button" class="btn bg-grey1">立即下载</button>
    </div>
    <div class="bg-red pt-2 pb-2">
      <div class="nav d-flex text-white jc-around pb-1">
        <div class="nav-item active">
          <!-- 表示首页一个链接 -->
          <router-link to="/" class="nav-link" tag="div">首页</router-link>
        </div>

        <div class="nav-item">
          <!-- 表示首页一个链接 -->
          <router-link to="/" class="nav-link" tag="div">社区</router-link>
        </div>

        <div class="nav-item">
          <!-- 表示首页一个链接 -->
          <router-link to="/" class="nav-link" tag="div">宝贝</router-link>
        </div>
      </div>
    </div>
    <router-view></router-view>
  </div>
</template>

<script></script>
