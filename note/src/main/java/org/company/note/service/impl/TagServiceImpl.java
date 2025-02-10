package org.company.note.service.impl;

//import com.example.entity.dto.Tag;
//import com.example.mapper.TagMapper;
//import com.example.service.TagService;
import org.company.note.entity.Content;
import org.company.note.entity.Tag;
import org.company.note.mapper.TagMapper;
import org.company.note.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

//    @Override
//    public void deleteTagAndRelations(int tagId) {
////        tagMapper.deleteNoteTagRelations(tagId);
////        tagMapper.deleteTag(tagId);
//    }

    @Override
    public void deleteTagAndRelationsForUser(int tagId, int userId) {
        tagMapper.deleteNoteTagRelationsByTagIdAndUserId(tagId, userId);
        tagMapper.deleteTagIfNotUsedByUser(tagId, userId);

    }

    public static List<Map<String, Object>> removeDuplicates(List<Map<String, Object>> list, String keyToCompare) {
        Set<Object> seen = new HashSet<>();
        List<Map<String, Object>> result = new ArrayList<>();
        List<Map<String, Object>> result2 = new ArrayList<>();

        for (Map<String, Object> map : list) {
            Object value = map.get(keyToCompare);
            if (!seen.contains(value)) {
                seen.add(value);
                result.add(map);
            }else {
                result2.add(map);
            }
        }

        return result;
    }

    @Override
    public List<Map<String,Object>> getTagsByUserId(int userId) {
        List<Tag> tags = tagMapper.selectTagsByUserId(userId);
        List<Map<String,Object>> tagsList = new ArrayList<>();
        for (int i = 0; i < tags.toArray().length; i++) {
            org.company.note.entity.Tag tag = tags.get(i);
            Map tagMap = new HashMap();
            tagMap.put("id", tag.getId());
            tagMap.put("name", tag.getName());
            tagsList.add(tagMap);
        }
        List<Map<String, Object>> deDuplicatedList = removeDuplicates(tagsList, "name");
        return deDuplicatedList;
    }

//    @Override
//    public Integer findNoteIdByTagNameAndUserId(String tagName, int userId) {
////        return tagMapper.findNoteIdByTagNameAndUserId(tagName,userId);
//    }

    @Override
    public int getTagIdByName(String tagName) {
        return tagMapper.findTagIdByName(tagName);
    }

    @Override
    public List<Content> findNotesByUserIdAndTagId(Integer userId, Integer tagId) {
        return tagMapper.findNotesByUserIdAndTagId(userId, tagId);
    }


}
