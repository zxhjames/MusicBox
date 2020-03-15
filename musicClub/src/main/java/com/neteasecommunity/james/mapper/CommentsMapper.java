package com.neteasecommunity.james.mapper;

import com.neteasecommunity.james.model.Comments;
import com.neteasecommunity.james.model.CommentsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface CommentsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comments
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    long countByExample(CommentsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comments
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    int deleteByExample(CommentsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comments
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comments
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    int insert(Comments record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comments
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    int insertSelective(Comments record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comments
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    List<Comments> selectByExampleWithBLOBsWithRowbounds(CommentsExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comments
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    List<Comments> selectByExampleWithBLOBs(CommentsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comments
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    List<Comments> selectByExampleWithRowbounds(CommentsExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comments
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    List<Comments> selectByExample(CommentsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comments
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    Comments selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comments
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    int updateByExampleSelective(@Param("record") Comments record, @Param("example") CommentsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comments
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    int updateByExampleWithBLOBs(@Param("record") Comments record, @Param("example") CommentsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comments
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    int updateByExample(@Param("record") Comments record, @Param("example") CommentsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comments
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    int updateByPrimaryKeySelective(Comments record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comments
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    int updateByPrimaryKeyWithBLOBs(Comments record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comments
     *
     * @mbg.generated Wed Mar 11 15:48:51 CST 2020
     */
    int updateByPrimaryKey(Comments record);
}