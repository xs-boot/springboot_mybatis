package com.xs.myblog.controller;



import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xs.myblog.pojo.Blog;
import com.xs.myblog.pojo.Tag;
import com.xs.myblog.service.BlogService;
import com.xs.myblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class TagShowController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String tags(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,
                       @PathVariable Long id, Model model) {
        List<Tag> tags = tagService.listTagTop(10000);
        if (id == -1) {
           id = tags.get(0).getId();
        }
        System.out.println("id:" + id);
        PageHelper.startPage(pageNum, 5);
        List<Blog> blogByTagId = blogService.getBlogByTagId(id);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogByTagId);
        model.addAttribute("tags", tags);
        model.addAttribute("page", pageInfo);
        model.addAttribute("activeTagId", id);
        return "tags";
    }
}
