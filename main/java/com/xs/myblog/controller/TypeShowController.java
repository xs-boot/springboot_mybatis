package com.xs.myblog.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xs.myblog.pojo.Blog;
import com.xs.myblog.pojo.Type;
import com.xs.myblog.service.BlogService;
import com.xs.myblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping
public class TypeShowController {

    @Autowired
    private TypeService typeService;
    @Autowired
    private BlogService blogService;
    @GetMapping("/types/{id}")
    public String types(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,
                        @PathVariable Long id, Model model) {
        //种类一般不会超过1000，用1000模拟所有种类数

        List<Type> types = typeService.listTypeTop(1000);
        if (id == -1) {
            id = types.get(0).getId();
        }
        PageHelper.startPage(pageNum, 5);
        List<Blog> blogByTypeId = blogService.getBlogByTypeId(id);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogByTypeId);
        model.addAttribute("types", types);
        model.addAttribute("page", pageInfo);
        model.addAttribute("activeTypeId", id);
        return "types";
    }
}
