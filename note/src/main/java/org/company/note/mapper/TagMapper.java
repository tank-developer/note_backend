package org.company.note.mapper;

//import com.example.entity.dto.Tag;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.company.note.entity.Content;
import org.company.note.entity.Tag;

import java.util.List;

public interface TagMapper {
//    void deleteNoteTagRelations(int tagId);
//    void deleteTag(int tagId);


    void deleteNoteTagRelationsByTagIdAndUserId(int tagId, int userId);
    void deleteTagIfNotUsedByUser(int tagId, int userId);

    List<Tag> selectTagsByUserId(int userId);


//    Integer findTagIdByNameAndUserName(String tagName, String userName);


//    @Select({
//            "<script>",
//            "SELECT tt.note_id",
//            "FROM t_tags t",
//            "JOIN t_note_tags tt ON t.id = tt.tag_id",
//            "JOIN t_user u ON tt.note_id IN (SELECT n.id FROM t_note n WHERE n.user_id = #{userId})",
//            "WHERE t.name = #{tagName}",
//            "</script>"
//    })
//    Integer findNoteIdByTagNameAndUserId(@Param("tagName") String tagName, @Param("userId") int userId);



    Integer findTagIdByName(String tagName);

    @Select({
            "<script>",
            "SELECT n.*",
            "FROM t_note n",
            "JOIN t_note_tags nt ON n.id = nt.note_id",
            "WHERE n.user_id = #{userId}",
            "<if test=\"tagId != null\">",
            "AND nt.tag_id = #{tagId}",
            "</if>",
            "</script>"
    })
    List<Content> findNotesByUserIdAndTagId(@Param("userId") int userId, @Param("tagId") Integer tagId);



}
