package com.xs.myblog.service;



import com.xs.myblog.pojo.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);

    int updateCommentTop(Integer top,Long commentId);

    void deleteComment(Comment comment, Long id);
}
