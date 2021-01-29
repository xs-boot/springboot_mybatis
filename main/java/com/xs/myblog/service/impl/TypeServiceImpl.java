package com.xs.myblog.service.impl;


import com.github.pagehelper.PageHelper;
import com.xs.myblog.dao.BlogDao;
import com.xs.myblog.dao.TypeDao;
import com.xs.myblog.pojo.Type;
import com.xs.myblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    TypeDao typeDao;

    @Autowired
    BlogDao blogDao;

    @Transactional
    @Override
    public int saveType(Type type) {
        return typeDao.save(type);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        return typeDao.findById(id);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeDao.findByName(name);
    }

    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeDao.findAll(pageable);
    }

    @Override
    public List<Type> listType() {
        return typeDao.findAll();
    }

    @Override
    public List<Type> listTypeTop(Integer size) {
        List<Type> allType = typeDao.findAll();
        for (int i = 0; i < allType.size(); i++) {
            allType.get(i).setBlogs(blogDao.findByTypeId(allType.get(i).getId()));
        }
        Collections.sort(allType, (o1, o2) -> o2.getBlogs().size() - o1.getBlogs().size());

        List<Type> typeList = new ArrayList<>();
        for (int i = 0; i < size && i < allType.size(); i++) {
            if (allType.get(i).getBlogs().size() > 0)
                typeList.add(allType.get(i));
        }
        return typeList;
    }

    @Transactional
    @Override
    public int updateType(Long id, Type type) {
        return typeDao.update(type);
    }

    @Transactional
    @Override
    public int deleteType(Long id) {
        int countBlogUserType = typeDao.getCountBlogUserType(id);
        if (countBlogUserType > 0) {
            return -1;
        }
        typeDao.deleteById(id);
        return 0;
    }

    @Override
    public List<Type> getAllType() {
        return typeDao.findAll();
    }
}
