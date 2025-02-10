package org.company.note.service;

//import com.example.entity.dto.Tag;

import org.apache.ibatis.annotations.Param;
import org.company.note.entity.Content;
import org.company.note.entity.Tag;

import java.util.List;
import java.util.Map;

public interface TagService {

//    void deleteTagAndRelations(int tagId);

    void deleteTagAndRelationsForUser(int tagId, int userId);

    List<Map<String,Object>> getTagsByUserId(int userId);


//    Integer getTagIdByNameAndUserName(String tagName, String userName);


//    Integer findNoteIdByTagNameAndUserId(@Param("tagName") String tagName, @Param("userId") int userId);

    int getTagIdByName(String tagName);

    List<Content> findNotesByUserIdAndTagId(Integer userId, Integer tagId);

}

