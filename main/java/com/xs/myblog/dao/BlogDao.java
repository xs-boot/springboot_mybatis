package com.xs.myblog.dao;


import com.xs.myblog.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BlogDao{

    List<Blog> findTopByRecommend(Integer size);



    @Modifying
    @Transactional
    int updateViews(Long id);

    List<String> findGroupYear();

    List<Blog> findByYear(String year);

    Blog findById(Long id);

    List<Blog> findByTypeId(Long typeId);
    List<Blog> findByTagId(Long tagId);

    Page<Blog> findAll(Specification<Blog> blogSpecification, Pageable pageable);

    Page<Blog> findAll(Pageable pageable);

    List<Blog> findAll();

    Blog getBlogById(Long id);

    Long count();

    int insertBlog(Blog blog);

    void deleteById(Long id);

    int updateBlog(Blog blog);

    List<Blog> searchBlog(Blog blog);
    List<Blog> searchBlogByQuery(String query);

    List<Blog> findAllByUser(Integer userId);

    Blog getAllBlogAndUserById(Integer userId, Long blogId);
}
