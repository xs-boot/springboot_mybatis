package com.xs.myblog.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xs.myblog.pojo.Blog;
import com.xs.myblog.service.BlogService;
import com.xs.myblog.service.TagService;
import com.xs.myblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String index(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum, Model model) {
        PageHelper.startPage(pageNum, 5, "update_time desc");
        List<Blog> allBlog = blogService.getAllBlogAndUser(1);
        PageInfo<Blog> pageInfo = new PageInfo<>(allBlog);
        model.addAttribute("page", pageInfo);
        model.addAttribute("types", typeService.listTypeTop(5));
        model.addAttribute("tags", tagService.listTagTop(8));
        model.addAttribute("recommendBlogs", blogService.listRecommendBlogTop(6));

        return "index";
    }

    @RequestMapping("/search/{pageNum}")
    public String search(@PathVariable(required = false) String pageNum,
                         @RequestParam(required = false) String query,
                         Model model,HttpSession session) {
        Integer x = Integer.parseInt(pageNum);
        if (pageNum == null)
            x = 1;
        String q1 = (String) session.getAttribute("query");
        String q2 = (String) model.getAttribute("query");
        if (q1 != null)
            query = q1;
        if (q2 != null)
            query = q2;
        PageHelper.startPage(x, 5, "update_time desc");
        List<Blog> blogList = blogService.searchBlogByQuery(query);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        model.addAttribute("query", query);
        model.addAttribute("page", pageInfo);
        session.setAttribute("query", query);

        return "search";
    }

    @RequestMapping("/search")
    public String search2(@RequestParam String query,
                          Model model,HttpSession session ) {
        PageHelper.startPage(1, 100, "update_time desc");
        List<Blog> blogList = blogService.searchBlogByQuery(query);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        model.addAttribute("query", query);
        session.setAttribute("query", query);
        model.addAttribute("page", pageInfo);

        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {
        model.addAttribute("blog", blogService.getAndConvert(id));
        return "blog";
    }

    @GetMapping("/footer/newblog")
    public String newblogs(Model model) {
        model.addAttribute("newblogs", blogService.listRecommendBlogTop(3));
        return "_fragments :: newblogList";
    }
}
