package com.xs.myblog.dao;


import com.xs.myblog.pojo.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentDao {


    List<Comment> findByBlogIdAndParentCommentNull(Long blogId);


    Comment findById(Long parentCommentId);

    int save(Comment comment);

    //根据创建时间倒序来排
    List<Comment> findByBlogIdParentIdNull(@Param("blogId") Long blogId, @Param("blogParentId") Long blogParentId);

    //查询一级回复
    List<Comment> findByBlogIdParentIdNotNull(@Param("blogId") Long blogId, @Param("id") Long id);

    //查询二级回复
    List<Comment> findByBlogIdAndReplayId(@Param("blogId") Long blogId,@Param("childId") Long childId);

    //置顶与取消置顶
    int updateCommentTop(Integer top,Long commentId);

    void deleteCommentById(Long commentId);
}
