package com.xs.myblog.controller.admin;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xs.myblog.pojo.Blog;
import com.xs.myblog.pojo.Type;
import com.xs.myblog.pojo.User;
import com.xs.myblog.service.BlogService;
import com.xs.myblog.service.TagService;
import com.xs.myblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin/blogs")
public class BlogController {

    private final String UPDATE_BLOG = "admin/blogs-input";
    private final String LIST = "admin/blogs";
    private final String REDIRECT_LIST = "redirect:/admin/blogs";

    @Autowired
    private BlogService blogService;
    @Autowired
    TypeService typeService;
    @Autowired
    private TagService tagService;

    @GetMapping("")
    public String blogs(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        String orderBy = "update_time desc";
        PageHelper.startPage(pageNum, 5, orderBy);
        List<Blog> list = blogService.getAllBlog();

        PageInfo<Blog> pageInfo = new PageInfo<Blog>(list);
        model.addAttribute("types", typeService.getAllType());
        model.addAttribute("page", pageInfo);
        return LIST;
    }

    @PostMapping("/search")
    public String search(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,
                         @RequestParam(value = "typeId")Long typeId,Blog blog, Model model) {
        Type type = new Type();
        type.setId(typeId);
        blog.setType(type);
        List<Blog> blogList = blogService.searchBlog(blog);
        PageHelper.startPage(pageNum, 5);
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(blogList);
        model.addAttribute("page", pageInfo);
        return "admin/blogs :: blogList";//部分更新
    }

    @PostMapping("")
    public String addBlog(Blog blog, HttpSession session, RedirectAttributes attributes) {
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));
        int count = 0;

        if (blog.getId() == null) {
            count =  blogService.saveBlog(blog);
            tagService.insertBlogTag(blog.getId(), blog.getTagIds());
        } else {
            count = blogService.updateBlog(blog.getId(), blog);
        }
        if (count == 0) {
            //新增失败
            attributes.addFlashAttribute("message", "操作失败");
        }else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return REDIRECT_LIST;
    }

    private void setTypeAndTag(Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
    }

    @GetMapping("/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        setTypeAndTag(model);
        Blog blog = blogService.getBlogById(id);

        blog.init();
        model.addAttribute("blog",blog);
        return UPDATE_BLOG;
    }

    @GetMapping("/input")
    public String input(Model model) {
        model.addAttribute("blog", new Blog());
        setTypeAndTag(model);
        return UPDATE_BLOG;
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message");
        return REDIRECT_LIST;
    }
}
