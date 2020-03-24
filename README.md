# MusicBox(仿网易云音乐app)
### 项目演示地址(http://175.24.67.250/)
### 技术栈(Springboot+Vuejs+mysql+redis+nginx+网易云开源接口)
从无到有造了这个轮子,花了挺长时间,代码不是很优雅,未来或许会重构
### 优化部分
* 使用nginx存储网站静态资源,部署了三台tomcat做负载均衡
* redis存储高热点数据,用户token等,缓解mysql压力
* vue组件多采用父子组件通信策略,解除各组件之间依赖,达到复用效果
* 页面懒加载,加快页面响应速度
* 前后端分离架构
### 尚未解决
* mysql,redis主从一致
* 高并发
> gitclone https://github.com/zxhjames/MusicBox
> cd musicbox 
> npm run serve 
> cd NeteaseCloudMusicApi-master && node app.js

```sql
create table Community.user
(
    id   int auto_increment  primary key,
    username     varchar(50)   not null,
    password     varchar(50)   not null,
    gmt_create   bigint        not null,
    gmt_modified bigint        not null,
    avatar_url   varchar(250)  null,
    `rank`       int default 0 null,
    concerns     int default 0 null,
    followers    int default 0 null,
    constraint userUnique
        unique (username),
    constraint user_username_uindex
        unique (username)
)
    comment '用户表' charset = utf8;

create table Community.share
(
    id            int auto_increment
        primary key,
    title         varchar(50)   not null,
    content       longtext      null,
    creator       varchar(50)   null,
    gmt_create    bigint        null,
    gmt_modified  bigint        null,
    comment_count int default 0 null,
    view_count    int default 0 null,
    like_count    int default 0 null,
    type          int           null,
    parent_id     int           null,
    repost_count  int default 0 null
)
    comment '社区动态表' charset = utf8;

create table Community.comments
(
    id           int auto_increment
        primary key,
    parent_id    int           null,
    type         int default 0 not null,
    gmt_create   bigint        null,
    gmt_modified bigint        null,
    commentator  varchar(50)   not null,
    content      longtext      not null,
    like_count   int default 0 null
)
    comment '社区评论表' charset = utf8;

create table Community.relationship
(
    id   int auto_increment
        primary key,
    uid1 int not null,
    uid2 int not null,
    type int not null
)
    comment '用户关系表';


```

