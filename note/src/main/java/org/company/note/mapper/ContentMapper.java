package org.company.note.mapper;


//import com.example.entity.dto.Content;
//import com.example.entity.dto.Tag;
import org.apache.ibatis.annotations.Param;
import org.company.note.entity.Content;
import org.company.note.entity.Tag;

import java.util.List;
import java.util.Map;

//@Mapper
public interface ContentMapper {
    List<Content> selectNotesByUserId(Integer userId);

    List<Content> selectContentByUserName(@Param("username") String username);

    int insertContentByUsername(Map<String, Object> params);

    int updateContentByIdAndUsername(@Param("id") int id,
                                     @Param("username") String username,
                                     @Param("title") String title,
                                     @Param("content") String content);

//    int deleteContentByUsername(@Param("username") String username);

    int deleteContentByIdAndUsername(@Param("id") Integer id, @Param("username") String username);


    Content selectContentByIdAndUsername(@Param("id") int id, @Param("username") String username);


    List<Content> selectContentByUserIdAndTagId(@Param("username") String username, @Param("tagId") Integer tagId);

    //根据用户名查询用户对应的id
    int selectIdByUsername(@Param("username") String username);



    int insertContent(Content content);
    int insertTag(Tag tag);
    void insertNoteTagRelation(int contentId, Integer tagId);

    int updateContent(Content content);
    int updateTag(Tag tag);

    /**
     * 根据username和updated_at查询t_note当中的内容
     * @param username
     * @param updatedAt
     * @return
     */
    List<Content> findNotesByUserNameAndUpdatedAt(String username, String updatedAt);


    List<Content> selectNotesByUserIdWithFuzzySearch(@Param("userId") int userId, @Param("searchTerm") String searchTerm);

}
