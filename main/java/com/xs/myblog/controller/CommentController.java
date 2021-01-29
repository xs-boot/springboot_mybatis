package com.xs.myblog.controller;


import com.xs.myblog.pojo.Blog;
import com.xs.myblog.pojo.Comment;
import com.xs.myblog.pojo.User;
import com.xs.myblog.service.BlogService;
import com.xs.myblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;

//    @Value("${comment.avatar}")
    private final String avatar = "/images/avatar.png";

    @GetMapping("/{blogId}")
    public String comments(@PathVariable Long blogId, Model model) {
        model.addAttribute("comments", commentService.listCommentByBlogId(blogId));
        return "blog :: commentList";
    }

    @PostMapping("")
    public String post(Comment comment, HttpSession session) {
        Long blogId = comment.getBlog().getId();
        comment.setBlog(blogService.getBlogById(blogId));
        User user = (User) session.getAttribute("user");
        if (user != null) {
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        } else {
            comment.setAvatar(avatar);
        }
        commentService.saveComment(comment);
        return "redirect:/comments/" + blogId;
    }

    @RequestMapping("/updateTop/{top}/{commentId}/{blogId}")
    public String updateTop(@PathVariable Integer top, @PathVariable Long commentId,
                            @PathVariable Long blogId,Model model) {
        commentService.updateCommentTop(top,commentId);
        Blog blog = blogService.getAllBlogAndUserById(1, blogId);

        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        model.addAttribute("comments", comments);
        model.addAttribute("blog", blog);
        return "blog" ;
    }

    //    删除评论
    @GetMapping("/{blogId}/{id}/delete")
    public String delete(@PathVariable Long blogId, @PathVariable Long id, Comment comment, RedirectAttributes attributes, Model model) {

        commentService.deleteComment(comment,id);

        Blog blog = blogService.getAllBlogAndUserById(1, blogId);
        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        model.addAttribute("comments", comments);
        model.addAttribute("blog", blog);

        return "blog";
    }
}
