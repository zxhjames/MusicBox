package com.neteasecommunity.james.model;

public class Comments {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comments.id
     *
     * @mbg.generated Fri Feb 28 20:51:08 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comments.parent_id
     *
     * @mbg.generated Fri Feb 28 20:51:08 CST 2020
     */
    private Integer parentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comments.type
     *
     * @mbg.generated Fri Feb 28 20:51:08 CST 2020
     */
    private Integer type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comments.gmt_create
     *
     * @mbg.generated Fri Feb 28 20:51:08 CST 2020
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comments.gmt_modified
     *
     * @mbg.generated Fri Feb 28 20:51:08 CST 2020
     */
    private Long gmtModified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comments.commentator
     *
     * @mbg.generated Fri Feb 28 20:51:08 CST 2020
     */
    private String commentator;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comments.like_count
     *
     * @mbg.generated Fri Feb 28 20:51:08 CST 2020
     */
    private Integer likeCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comments.content
     *
     * @mbg.generated Fri Feb 28 20:51:08 CST 2020
     */
    private String content;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comments.id
     *
     * @return the value of comments.id
     *
     * @mbg.generated Fri Feb 28 20:51:08 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comments.id
     *
     * @param id the value for comments.id
     *
     * @mbg.generated Fri Feb 28 20:51:08 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comments.parent_id
     *
     * @return the value of comments.parent_id
     *
     * @mbg.generated Fri Feb 28 20:51:08 CST 2020
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comments.parent_id
     *
     * @param parentId the value for comments.parent_id
     *
     * @mbg.generated Fri Feb 28 20:51:08 CST 2020
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comments.type
     *
     * @return the value of comments.type
     *
     * @mbg.generated Fri Feb 28 20:51:08 CST 2020
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comments.type
     *
     * @param type the value for comments.type
     *
     * @mbg.generated Fri Feb 28 20:51:08 CST 2020
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comments.gmt_create
     *
     * @return the value of comments.gmt_create
     *
     * @mbg.generated Fri Feb 28 20:51:08 CST 2020
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comments.gmt_create
     *
     * @param gmtCreate the value for comments.gmt_create
     *
     * @mbg.generated Fri Feb 28 20:51:08 CST 2020
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comments.gmt_modified
     *
     * @return the value of comments.gmt_modified
     *
     * @mbg.generated Fri Feb 28 20:51:08 CST 2020
     */
    public Long getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comments.gmt_modified
     *
     * @param gmtModified the value for comments.gmt_modified
     *
     * @mbg.generated Fri Feb 28 20:51:08 CST 2020
     */
    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comments.commentator
     *
     * @return the value of comments.commentator
     *
     * @mbg.generated Fri Feb 28 20:51:08 CST 2020
     */
    public String getCommentator() {
        return commentator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comments.commentator
     *
     * @param commentator the value for comments.commentator
     *
     * @mbg.generated Fri Feb 28 20:51:08 CST 2020
     */
    public void setCommentator(String commentator) {
        this.commentator = commentator == null ? null : commentator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comments.like_count
     *
     * @return the value of comments.like_count
     *
     * @mbg.generated Fri Feb 28 20:51:08 CST 2020
     */
    public Integer getLikeCount() {
        return likeCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comments.like_count
     *
     * @param likeCount the value for comments.like_count
     *
     * @mbg.generated Fri Feb 28 20:51:08 CST 2020
     */
    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comments.content
     *
     * @return the value of comments.content
     *
     * @mbg.generated Fri Feb 28 20:51:08 CST 2020
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comments.content
     *
     * @param content the value for comments.content
     *
     * @mbg.generated Fri Feb 28 20:51:08 CST 2020
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}