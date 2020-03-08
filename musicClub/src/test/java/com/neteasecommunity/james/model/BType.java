package com.neteasecommunity.james.model;

public enum BType {
    LIKE_ARTICLE("文章点赞"),
    LIKE_ARTICLE_REPORT("文章回复点赞"),
    LIKE_VIDEO("视频点赞"),
    LIKE_VIDEO_REPORT("视频回复点赞");
    private String btype;
    BType(String btype){
        this.btype = btype;
    }

    public String getBtype() {
        return btype;
    }

    public void setBtype(String btype) {
        this.btype = btype;
    }
}
