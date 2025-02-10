package org.company.note.service;


//import com.example.entity.dto.Content;
//import com.example.entity.dto.Tag;
import com.github.pagehelper.PageInfo;
import org.company.note.entity.Content;
import org.company.note.entity.Tag;

import java.util.List;

public interface ContentService {

    int insertContent(String username, String title, String content,String image);

    int updateContent(int contentId ,String username, String title, String content);
    int deleteContent(Integer contentId,String username);

    List<Content> queryContent(Integer userId);
    PageInfo<Content> getContentByUserName(String username,Integer pageNum,Integer pageSize);

    Content selectContentByIdAndUsername(int id, String username);

    /**
     * 根据tags查询当前用户的笔记内容
     * @param id
     * @param username
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Content> selectContentByUserIdAndTagId(int id, String username,Integer pageNum,Integer pageSize);

    /**
     * 根据标签和用户添加笔记内容
     * @param userId
     * @param title
     * @param content
     * @param image
     * @param tagName
     */
    void addContentAndTag(String userId, String title, String content, String image, String tagName,String username);



    /**
     * 根据标签和用户名更新笔记内容和标签
     * @param content
     * @param tag
     */
    void updateContentAndTag(Content content, Tag tag);

    /**
     * 根据用户的名字查询对应的id
     * @param username
     * @return
     */
    int getIdByUsername(String username);

    /**
     * 根据username和updated_at查询t_note当中的内容
     * @param username
     * @param updatedAt
     * @return
     */
    PageInfo<Content> getContentByUserAndUpdatedAt(String username,String updatedAt,Integer pageNum,Integer pageSize);


    List<Content> getNotesByUserIdWithFuzzySearch(int userId, String searchTerm);

}
