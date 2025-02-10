package org.company.note.service.impl;

//import com.example.entity.dto.Content;
//import com.example.entity.dto.Tag;
//import com.example.mapper.ContentMapper;
//import com.example.service.ContentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.transaction.Transactional;
import org.company.note.entity.Content;
import org.company.note.entity.Tag;
import org.company.note.mapper.ContentMapper;
import org.company.note.mapper.TagMapper;
import org.company.note.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 笔记内容处理相关服务
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Autowired
    private TagMapper tagMapper;

    @Override
    public int insertContent(String username, String title, String content,String image) {
        Map<String, Object> params = new HashMap<>();
        params.put("username", username);
        params.put("title", title);
        params.put("content", content);
        params.put("image", image);
        return contentMapper.insertContentByUsername(params);
    }

    @Override
    public int updateContent(int contentId ,String username, String title, String content) {
        return contentMapper.updateContentByIdAndUsername(contentId,username,title,content);
    }

    @Override
    public int deleteContent(Integer contentId, String username) {
        return contentMapper.deleteContentByIdAndUsername(contentId, username);
    }

//    @Override
//    public int deleteContent(String username) {
//        return contentMapper.deleteContentByUsername(username);
//    }


    @Override
    public List<Content> queryContent(Integer userId) {
        List<Content> contents = contentMapper.selectNotesByUserId(userId);
        return contents;
    }

    @Override
    public PageInfo<Content> getContentByUserName(String username,Integer pageNum,Integer pageSize) {
        //开启分页功能，pageNum是当前页，pageSize是每页显示的数据量，这两个值都可以选择让前端传或者自己调整
        PageHelper.startPage(pageNum,pageSize);
        List<Content> contentList = contentMapper.selectContentByUserName(username);
        PageInfo<Content> pageInfo = new PageInfo<>(contentList);
        return pageInfo;
    }

    @Override
    public Content selectContentByIdAndUsername(int id, String username) {
        Content contents = contentMapper.selectContentByIdAndUsername(id,username);
        return contents;
    }

    @Override
    public PageInfo<Content> selectContentByUserIdAndTagId(int id, String username,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Content> contentList = contentMapper.selectContentByUserIdAndTagId(username, id);
        PageInfo<Content> pageInfo = new PageInfo<>(contentList);
        return pageInfo;
    }

    @Override
    @Transactional//事物
    public void addContentAndTag(String userId, String title, String content, String image, String tagName,String username) {
        int userIdInt = Integer.parseInt(userId);
        //根据用户id和标签名字找当前用是否存在某一个标签。
//        Integer noteIdByTagNameAndUserId = tagMapper.findNoteIdByTagNameAndUserId(tagName, userIdInt);
        Integer noteIdByTagNameAndUserId = tagMapper.findTagIdByName(tagName);

        Content contentEntity = new Content();
        contentEntity.setUserId(userId);
        contentEntity.setTitle(title);
        contentEntity.setContent(content);
        contentEntity.setImage(image);
        contentMapper.insertContent(contentEntity);
        int contentId = contentEntity.getId(); // 获取插入后生成的contentId
        //当前用户存在某一个标签，那就直接绑定已经存在的标签和内容，
        if (noteIdByTagNameAndUserId != null){
            Tag tagEntity = new Tag();
            tagEntity.setName(tagName);
//            contentMapper.insertTag(tagEntity);
//            int tagId = tagEntity.getId(); // 获取插入后生成的tagId

            contentMapper.insertNoteTagRelation(contentId, noteIdByTagNameAndUserId);
        }else {
            //这里查询的标签是标签表里所有的标签，而不是某一个用户的标签。
            Optional<Integer> tagIdByName = Optional.ofNullable(tagMapper.findTagIdByName(tagName));
            Integer newtagId = tagIdByName.orElse(0);
            //根据标签的名字找到对应的标签的id,如果存在就直接绑定数据，不做插入标签；如果不存在就插入标签，绑定关系
            /*
            当前用户不存在某一个标签，那就首先看下这个标签在标签表中是否存在，如果不存在就插入标签，再用新的标签的id绑定内容id
            如果存在标签表中存在这个标签，那就用这个表中的标签和内容绑定。
            */

            //如果不存在就插入标签，再用新的标签的id绑定内容id
            if (newtagId == 0){
                 Tag tagEntity = new Tag();
                tagEntity.setName(tagName);
                contentMapper.insertTag(tagEntity);
                int tagId = tagEntity.getId(); // 获取插入后生成的tagId
                contentMapper.insertNoteTagRelation(contentId, tagId);
            }else {//如果存在标签表中存在这个标签，那就用这个表中的标签和内容绑定。
//            Tag tagEntity = new Tag();
//            tagEntity.setName(tagName);
//            contentMapper.insertTag(tagEntity);
//            int tagId = tagEntity.getId(); // 获取插入后生成的tagId
                contentMapper.insertNoteTagRelation(contentId, newtagId);
            }

        }

//        Tag tagEntity = new Tag();
//        tagEntity.setName(tagName);
//        contentMapper.insertTag(tagEntity);
//        int tagId = tagEntity.getId(); // 获取插入后生成的tagId
//
//        contentMapper.insertNoteTagRelation(contentId, tagId);
    }

    @Override
    public void updateContentAndTag(Content content, Tag tag) {
        contentMapper.updateContent(content);
        contentMapper.updateTag(tag);
    }


    @Override
    public int getIdByUsername(String username) {
        return contentMapper.selectIdByUsername(username);
    }

    @Override
    public PageInfo<Content> getContentByUserAndUpdatedAt(String username,String updatedAt,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Content> contentList = contentMapper.findNotesByUserNameAndUpdatedAt(username,updatedAt);
        PageInfo<Content> pageInfo = new PageInfo<>(contentList);
        return pageInfo;
    }

    @Override
    public List<Content> getNotesByUserIdWithFuzzySearch(int userId, String searchTerm) {
        return contentMapper.selectNotesByUserIdWithFuzzySearch(userId, searchTerm);
    }

}
