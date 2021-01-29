package com.xs.myblog.service.impl;



import com.xs.myblog.NotFindException;
import com.xs.myblog.dao.BlogDao;
import com.xs.myblog.dao.TagDao;
import com.xs.myblog.pojo.Tag;
import com.xs.myblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by limi on 2017/10/16.
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao;

    @Autowired
    private BlogDao blogDao;



    @Transactional
    @Override
    public int saveTag(Tag tag) {
        return tagDao.save(tag);
    }

    @Transactional
    @Override
    public Tag getTag(Long id) {
        return tagDao.findById(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagDao.findByName(name);
    }

    @Transactional
    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagDao.findAll(pageable);
    }

    @Override
    public List<Tag> listTag() {
        return tagDao.findAll();
    }

    @Override
    public List<Tag> listTagTop(Integer size) {
        List<Tag> allTags = tagDao.findAll();
        for (int i = 0; i < allTags.size(); i++) {
            allTags.get(i).setBlogs(blogDao.findByTagId(allTags.get(i).getId()));
        }
        Collections.sort(allTags, (o1, o2) -> o2.getBlogs().size() - o1.getBlogs().size());
        List<Tag> tagList = new ArrayList<>();
        for (int i = 0; i < size && i < allTags.size(); i++) {
            if (allTags.get(i).getBlogs().size() > 0)
                tagList.add(allTags.get(i));
        }
        return tagList;
    }


    @Override
    public List<Tag> listTag(String ids) { //1,2,3
        System.out.println(convertToList(ids));
        return tagDao.findAllById(convertToList(ids));
    }

    @Override
    public int insertBlogTag(Long blogId, String tagsId) {
        String[] split = tagsId.split(",");

        for (int i = 0; i < split.length; i++) {
            tagDao.insertBlogTag(blogId, Long.parseLong(split[i]));
        }

        return split.length;
    }

    private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();

        if (!StringUtils.isEmpty(ids)) {
            String[] array = ids.split(",");
            for (int i = 0; i < array.length; i++) {
                list.add(Long.valueOf(array[i]));
            }
        }
        return list;
    }



    @Transactional
    @Override
    public int updateTag(Long id, Tag tag) {
        Tag t = tagDao.findById(id);

        if (t.getId() == null) {
            throw new NotFindException("不存在该标签");
        }

        return tagDao.updateTag(tag);
    }



    @Transactional
    @Override
    public int deleteTag(Long id) {
        //删除前要先检查有没有博客已经使用了该标签，如果已经有博客使用了则不能删除
        int count = tagDao.getCountBlogUserTag(id);
        if (count > 0)
            return -1;
        tagDao.deleteBlogsAndTagById(id);
        tagDao.deleteById(id);
        return 0;
    }
}
