package com.xs.myblog.dao;


import com.xs.myblog.pojo.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface TagDao {

    Tag findByName(String name);

    List<Tag> findTop(Integer size);

    int updateTag(Tag tag);

    int insertBlogTag(Long blogId, Long tagId);

    Tag findById(Long id);

    Page<Tag> findAll(Pageable pageable);

    List<Tag> findAll();

    List<Tag> findAllById(List<Long> convertToList);

    void deleteById(Long id);
    void deleteBlogsAndTagById(Long id);
    int getCountBlogUserTag(Long id);

    int save(Tag tag);
}
