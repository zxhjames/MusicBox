package com.neteasecommunity.james.model;

import java.io.Serializable;

public class Share implements Serializable {
    private static final long serialVersionUID = -120269475644671859L;
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column share.id
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column share.title
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column share.creator
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    private String creator;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column share.gmt_create
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column share.gmt_modified
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    private Long gmtModified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column share.comment_count
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    private Integer commentCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column share.view_count
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    private Integer viewCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column share.like_count
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    private Integer likeCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column share.type
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    private Integer type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column share.parent_id
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    private Integer parentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column share.repost_count
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    private Integer repostCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column share.content
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    private String content;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column share.id
     *
     * @return the value of share.id
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column share.id
     *
     * @param id the value for share.id
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column share.title
     *
     * @return the value of share.title
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column share.title
     *
     * @param title the value for share.title
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column share.creator
     *
     * @return the value of share.creator
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column share.creator
     *
     * @param creator the value for share.creator
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column share.gmt_create
     *
     * @return the value of share.gmt_create
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column share.gmt_create
     *
     * @param gmtCreate the value for share.gmt_create
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column share.gmt_modified
     *
     * @return the value of share.gmt_modified
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    public Long getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column share.gmt_modified
     *
     * @param gmtModified the value for share.gmt_modified
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column share.comment_count
     *
     * @return the value of share.comment_count
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    public Integer getCommentCount() {
        return commentCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column share.comment_count
     *
     * @param commentCount the value for share.comment_count
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column share.view_count
     *
     * @return the value of share.view_count
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    public Integer getViewCount() {
        return viewCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column share.view_count
     *
     * @param viewCount the value for share.view_count
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column share.like_count
     *
     * @return the value of share.like_count
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    public Integer getLikeCount() {
        return likeCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column share.like_count
     *
     * @param likeCount the value for share.like_count
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column share.type
     *
     * @return the value of share.type
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column share.type
     *
     * @param type the value for share.type
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column share.parent_id
     *
     * @return the value of share.parent_id
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column share.parent_id
     *
     * @param parentId the value for share.parent_id
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column share.repost_count
     *
     * @return the value of share.repost_count
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    public Integer getRepostCount() {
        return repostCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column share.repost_count
     *
     * @param repostCount the value for share.repost_count
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    public void setRepostCount(Integer repostCount) {
        this.repostCount = repostCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column share.content
     *
     * @return the value of share.content
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column share.content
     *
     * @param content the value for share.content
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}