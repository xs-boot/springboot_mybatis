package com.xs.myblog.service.impl;


import com.xs.myblog.NotFindException;
import com.xs.myblog.dao.BlogDao;
import com.xs.myblog.pojo.Blog;
import com.xs.myblog.service.BlogService;
import com.xs.myblog.util.MarkdownUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.time.YearMonth;
import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogDao blogDao;

    @Override
    public Blog getBlogById(Long id) {
        return blogDao.getBlogById(id);
    }

    public Blog findById(Long id) {
        return blogDao.findById(id);
    }

    @Transactional
    @Override
    public Blog getAndConvert(Long id) {
        List<Blog> allByUser = blogDao.findAllByUser(1);
        Blog blog = null;
        for (int i = 0; i < allByUser.size(); i++) {
            if (allByUser.get(i).getId().equals(id))
                blog = allByUser.get(i);
        }
//        blog.setViews(blog.getViews() + 1);

        if (blog.getId() == null) {
            throw new NotFindException("博客不存在");
        }
        blogDao.updateViews(id);
        Blog b = new Blog();
        BeanUtils.copyProperties(blog, b);

        b.setContent(MarkdownUtils.markdownToHtmlExtensions(b.getContent()));
        return b;
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, Blog blog) {

        return blogDao.findAll(null);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogDao.findAll(pageable);
    }

    @Override
    public Page<Blog> listBlog(String query, Pageable pageable) {
        return null;
    }

    @Override
    public Page<Blog> listBlog(Long tagID, Pageable pageable) {
        return blogDao.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                Join join = root.join("tags");
                return cb.equal(join.get("id"), tagID);
            }
        },pageable);
    }

    @Override
    public List<Blog> listRecommendBlogTop(Integer size) {
        return blogDao.findTopByRecommend(size);
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogDao.findGroupYear();
        TreeMap<String,List<Blog>> map = new TreeMap<>((o1, o2)->Integer.parseInt(o2)-Integer.parseInt(o1));

        for (String year : years) {
            map.put(year, blogDao.findByYear(year));
        }
        return map;
    }

    @Override
    public Long countBlog() {
        return blogDao.count();
    }

    @Transactional
    @Override
    public int saveBlog(Blog blog) {
        Date now = new Date();

        blog.setCreateTime(now);
        blog.setUpdateTime(now);
        blog.setViews(0);
        return blogDao.insertBlog(blog);
    }

    @Transactional
    @Override
    public int updateBlog(Long id, Blog blog) {
        Blog b = blogDao.getBlogById(id);
        if (b == null) {
            throw new NotFindException("该博客不存在");
        }
        blog.setUpdateTime(new Date());
        return blogDao.updateBlog(blog);
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogDao.deleteById(id);
    }

    @Override
    public List<Blog> getAllBlog() {
        return blogDao.findAll();
    }

    @Override
    public List<Blog> getAllBlogAndUser(Integer userId) {
        return blogDao.findAllByUser(userId);
    }

    @Override
    public Blog getAllBlogAndUserById(Integer userId, Long blogId) {
        return blogDao.getAllBlogAndUserById(userId, blogId);
    }

    @Override
    public List<Blog> searchBlog(Blog blog) {
        return blogDao.searchBlog(blog);
    }

    @Override
    public List<Blog> searchBlogByQuery(String query) {
        return blogDao.searchBlogByQuery(query);
    }

    @Override
    public List<Blog> getBlogByTypeId(Long typeId) {
        return blogDao.findByTypeId(typeId);
    }

    @Override
    public List<Blog> getBlogByTagId(Long tagId) {
        return blogDao.findByTagId(tagId);
    }
}
