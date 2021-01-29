package com.xs.myblog.service;


import com.xs.myblog.pojo.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BlogService {

    Blog getBlogById(Long id);

    Blog findById(Long id);

    Blog getAndConvert(Long id);

    Page<Blog> listBlog(Pageable pageable, Blog blog);

    Page<Blog> listBlog(Pageable pageable);

    Page<Blog> listBlog(String query, Pageable pageable);

    Page<Blog> listBlog(Long tagID, Pageable pageable);

    List<Blog> listRecommendBlogTop(Integer size);

    Map<String, List<Blog>> archiveBlog();

    Long countBlog();

    int saveBlog(Blog blog);

    int updateBlog(Long id, Blog blog);

    void deleteBlog(Long id);

    List<Blog> getAllBlog();

    List<Blog> getAllBlogAndUser(Integer userId);
    Blog getAllBlogAndUserById(Integer userId,Long blogId);

    List<Blog> searchBlog(Blog blog);
    List<Blog> searchBlogByQuery(String query);

    List<Blog> getBlogByTypeId(Long typeId);
    List<Blog> getBlogByTagId(Long tagId);
}
