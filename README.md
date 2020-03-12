# MusicBox(仿网易云音乐app)
### 技术栈(Springboot+Vue+mybatis+mysql+redis)
还有一些问题待优化,由于是自己写的,项目现在有点臃肿,有一些脏代码
* 数据库查询优化
* 前端js代码优化
* redis与mysql从主一致性,使用延迟加载要保证数据一致性
* 组件复用性
> gitclone https://github.com/zxhjames/MusicBox

>cd musicbox && npm run serve 
>cd NeteaseCloudMusicApi-master && node app.js

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

