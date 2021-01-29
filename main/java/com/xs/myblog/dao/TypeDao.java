package com.xs.myblog.dao;


import com.xs.myblog.pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface TypeDao {

    Type findByName(String name);

    List<Type> findTop(Integer size);

    int update(Type type);

    Type findById(Long id);

    Page<Type> findAll(Pageable pageable);

    List<Type> findAll();

    int deleteById(Long id);
    int getCountBlogUserType(Long id);

    int save(Type type);

}
