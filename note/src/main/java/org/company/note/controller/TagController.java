package org.company.note.controller;


import com.auth0.jwt.interfaces.DecodedJWT;
//import com.example.entity.RestBean;
//import com.example.entity.dto.Content;
//import com.example.service.ContentService;
//import com.example.service.TagService;
//import com.example.utils.JwtUtils;
import org.company.note.entity.Content;
import org.company.note.utils.JWTUtils;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.company.note.entity.RestBean;
import org.company.note.service.ContentService;
import org.company.note.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 用于验证相关Controller包含用户笔记的增删改查等操作
 */
@Validated
@RestController
@RequestMapping("/api/tag")
@Tag(name = "标签内容相关", description = "包括用户标签的增删改查")
public class TagController {

    @Resource
    JWTUtils utils;

    @Autowired
    TagService tagService;
    @Autowired
    ContentService contentService;

    /**
     * 列出标签列表
     * @param request
     * @return
     */
    @PostMapping("/tag-list")
    @Operation(summary = "请求标签的内容列表")
    public RestBean<List<Map<String,Object>>> tagList(HttpServletRequest request){
        String token = request.getHeader("token");
        if(token != null) {
            DecodedJWT verify = JWTUtils.verify(token);
            int idByUsername = contentService.getIdByUsername(verify.getClaim("username").asString());
            List<Map<String,Object>> tags = tagService.getTagsByUserId(idByUsername);
            return RestBean.success(tags);
        }
        return RestBean.failure(404,"false");
    }
    /**
     * 删除标签
     * @param request
     * @param tagId 标签id
     * @return
     */
    @PostMapping("/delete-tag")
    @Operation(summary = "删除标签")
    public RestBean<PageInfo<Content>> deleteTag(HttpServletRequest request, int tagId){
        String token = request.getHeader("token");
        if(token != null) {
            DecodedJWT verify = JWTUtils.verify(token);
            int idByUsername = contentService.getIdByUsername(verify.getClaim("username").asString());
            tagService.deleteTagAndRelationsForUser(tagId,idByUsername);
            return RestBean.success();
        }
        return RestBean.failure(404,"false");
    }

//    @PostMapping("/getTagid")
//    @Operation(summary = "删除标签")
//    public RestBean<PageInfo<Content>> getTagId(HttpServletRequest request, String tagname){
//        String token = request.getHeader("token");
//        if(token != null) {
//            DecodedJWT verify = JWTUtils.verify(token);
//            int idByUsername = contentService.getIdByUsername(verify.getClaim("username").asString());
////            tagService.getTagIdByNameAndUserName(tagname,verify.getClaim("username").asString());
//            return RestBean.success();
//        }
//        return RestBean.failure(404,"false");
//    }

    /**
     * 根据tagId查询笔记内容
     * @param request
     * @param tagId
     * @return
     */
    @PostMapping("/get_content_id")
    @Operation(summary = "根据tagId查询笔记内容")
    public RestBean<List<Content>> getNoteByTagId(HttpServletRequest request, Integer tagId){
        String token = request.getHeader("token");
        if(token != null) {
            DecodedJWT verify = JWTUtils.verify(token);
            int idByUsername = contentService.getIdByUsername(verify.getClaim("username").asString());
            List<Content> contentList = tagService.findNotesByUserIdAndTagId(idByUsername,tagId);
            return RestBean.success(contentList);
        }
        return RestBean.failure(404,"false");
    }
}
