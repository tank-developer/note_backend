package org.company.note.controller;


import com.auth0.jwt.interfaces.DecodedJWT;
//import com.example.entity.RestBean;
//import com.example.entity.dto.Content;
//import com.example.service.ContentService;
//import com.example.utils.JwtUtils;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.company.note.entity.Content;
import org.company.note.entity.RestBean;
import org.company.note.service.ContentService;
import org.company.note.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用于验证相关Controller包含用户笔记的增删改查等操作
 */
@Validated
@RestController
@RequestMapping("/api/content")
@Tag(name = "笔记内容相关", description = "包括用户笔记的增删改查")
public class ContentController {
    @Resource
    JWTUtils utils;

    @Autowired
    ContentService contentService;

    /**
     * 请求笔记列表
     * @param request 请求
     * @param pageNum 初始的页号
     * @param pageSize 分页的一页的长度
     * @return 是否请求成功
     */
    @PostMapping("/content-list")
    @Operation(summary = "请求笔记内容列表")
    public RestBean<PageInfo<Content>> contentList(HttpServletRequest request, Integer pageNum, Integer pageSize){
        String token = request.getHeader("token");
        if(token != null) {
            DecodedJWT verify = JWTUtils.verify(token);
            PageInfo<Content> contents = contentService.getContentByUserName(verify.getClaim("username").asString(),pageNum,pageSize);
            return RestBean.success(contents);
        }
        return RestBean.failure(500,"请登录");
    }

    /**
     * 根据日期请求笔记内容列表
     * @param request
     * @param updatedAt
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PostMapping("/content-list-updateat")
    @Operation(summary = "根据日期请求笔记内容列表")
    public RestBean<PageInfo<Content>> contentListByUpdatedAt(HttpServletRequest request,String updatedAt, Integer pageNum, Integer pageSize){
        String token = request.getHeader("token");
        if(token != null) {
            DecodedJWT verify = JWTUtils.verify(token);
            PageInfo<Content> contents = contentService.getContentByUserAndUpdatedAt(verify.getClaim("username").asString(),updatedAt,pageNum,pageSize);
            return RestBean.success(contents);
        }
        return RestBean.failure(500,"请登录");
    }



    /**
     * 请求插入笔记内容
     * @param request 请求
     * @param content 标题
     * @param title 内容
     * @param imageurl 图片的地址
     * @return 是否请求成功
     */
    @PostMapping("/insert-content")
    @Operation(summary = "增加笔记内容")
    public RestBean<String> insertContent(HttpServletRequest request,String content,String title,String imageurl){
        String[] imageUrlList = imageurl.split(",");
        if(imageUrlList.length > 5){
            return RestBean.success("图片不能大于五张");
        }
        String token = request.getHeader("token");
        if(token != null) {
            DecodedJWT verify = JWTUtils.verify(token);
            int result = contentService.insertContent(verify.getClaim("username").asString(),title,content,imageurl);
            if (result > 0){
                return RestBean.success("添加成功");
            }else {
                return RestBean.failure(500,"重新插入");
            }
        }
        return RestBean.failure(500,"添加失败");
    }

    /**
     * 修改笔记内容
     * @param request
     * @param content 笔记内容
     * @param title 笔记的标题
     * @return
     */
    @PostMapping("/update-content")
    @Operation(summary = "修改笔记内容")
    public RestBean<String> updateContent(HttpServletRequest request,int contentId, String content,String title){
        String token = request.getHeader("token");
        if(token != null) {
            DecodedJWT verify = JWTUtils.verify(token);
            int result = contentService.updateContent(contentId,verify.getClaim("username").asString(),title,content);
            if (result > 0){
                return RestBean.success("修改成功");
            }else {
                return RestBean.failure(500,"重新插入");
            }
        }
        return RestBean.failure(500,"添加失败");
    }

    /**
     * 删除笔记内容
     * @param request
     * @return
     */
    @PostMapping("/delete-content")
    @Operation(summary = "删除笔记内容")
    public RestBean<Void> deleteContent(HttpServletRequest request,Integer contentId){
        if (contentId != null){
            String token = request.getHeader("token");
            if(token != null) {
                DecodedJWT verify = JWTUtils.verify(token);
                String username = verify.getClaim("username").asString();
                int result = contentService.deleteContent(contentId,username);
                if (result > 0){
                    return RestBean.success();
                }else {
                    return RestBean.failure(500,"删除失败");
                }
            }
            return RestBean.failure(500,"删除失败");
        }
        return RestBean.failure(500,"请输入序号");
    }


    @PostMapping("/query-content-id")
    @Operation(summary = "查询某一条笔记内容")
    public RestBean<Content> queryContentById(HttpServletRequest request,int contentId){
        String token = request.getHeader("token");
        if(token != null) {
            DecodedJWT verify = JWTUtils.verify(token);
            String username = verify.getClaim("username").asString();
            Content content = contentService.selectContentByIdAndUsername(contentId,username);
            return RestBean.success(content);
        }
        return RestBean.failure(500,"查询失败");
    }

    /**
     * 根据tags查询当前用户的笔记内容
     * @param request
     * @param tagsId
     * @return
     */
    @PostMapping("/query-content-tagsId")
    @Operation(summary = "根据某一用户和他的标签tags查询某一条笔记内容")
    public RestBean<PageInfo<Content>> queryContentBytagsId(HttpServletRequest request,
                                                            @RequestParam(value = "tagsId", required = false) Integer tagsId,
                                                            Integer pageNum,
                                                            Integer pageSize) {
        String token = request.getHeader("token");
        if(token != null) {
            DecodedJWT verify = JWTUtils.verify(token);
            String username = verify.getClaim("username").asString();
            if (tagsId != null){
                PageInfo<Content> contentPageInfo = contentService.selectContentByUserIdAndTagId(tagsId, username, pageNum, pageSize);
                return RestBean.success(contentPageInfo);
            }else {
                PageInfo<Content> contents = contentService.getContentByUserName(username,pageNum,pageSize);
                return RestBean.success(contents);
            }
        }
        return RestBean.failure(500,"查询失败");
    }

    /**
     * 根据某一用户和他的标签tags添加笔记和标签
     * @param request
     * @param tagName 标签名字
     * @param title 笔记标题
     * @param content 笔记内容
     * @param imageurl 笔记的配图
     * @return
     */
    @PostMapping("/insert-content-tagsId")
    @Operation(summary = "根据某一用户和他的标签tags添加笔记和标签")//,String content,String title,String imageurl
    public RestBean<Void> insertContentBytagsId(HttpServletRequest request,
                                                             @RequestParam(value = "tagName", required = false) String tagName,
                                                             String title,
                                                             String content,
                                                             String imageurl
                                                             ) {
        String token = request.getHeader("token");
        if(token != null) {
            DecodedJWT verify = JWTUtils.verify(token);
            String username = verify.getClaim("username").asString();
            int idByUsername = contentService.getIdByUsername(username);
            String idStr = String.valueOf(idByUsername);
            if (tagName != null){
                contentService.addContentAndTag(idStr,title,content,imageurl,tagName,username);
                return RestBean.success();
            }else {
                int result = contentService.insertContent(username,title,content,imageurl);
                return RestBean.success();
            }
        }
        return RestBean.failure(500,"查询失败");
    }

    /**
     * 根据某一用户和他的标签tags修改笔记和标签
     * @param request
     * @param tagName
     * @param title
     * @param content
     * @param image
     * @param contentId
     * @return
     */
    @PostMapping("/update-content-tagsId")
    @Operation(summary = "根据某一用户和他的标签tags修改笔记和标签")
    public RestBean<String> updateContentBytagsId(HttpServletRequest request,
                                                String tagName,
                                                String title,
                                                String content,
                                                String image,
                                                int contentId,
                                                int tagId

    ) {
//        String authorization = request.getHeader("Authorization");
        String token = request.getHeader("token");
        if(token != null) {
            DecodedJWT verify = JWTUtils.verify(token);
            String username = verify.getClaim("username").asString();
            int idByUsername = contentService.getIdByUsername(username);
            String idStr = String.valueOf(idByUsername);
            if (tagName != null){
                //Content content, Tag tag);
                Content contentEntity = new Content();
                contentEntity.setId(contentId);
                contentEntity.setTitle(title);
                contentEntity.setContent(content);
                contentEntity.setImage(image);

                org.company.note.entity.Tag  tag = new org.company.note.entity.Tag();
                tag.setId(tagId);
                tag.setName(tagName);
                contentService.updateContentAndTag(contentEntity,tag);
                return RestBean.success("更新成功");
            }else {
                int result = contentService.updateContent(contentId,username,title,content);
                return RestBean.success("更新成功");
            }
        }
        return RestBean.failure(500,"更新失败");
    }
    

    @PostMapping("/content_search")
    @Operation(summary = "请求笔记内容列表")
    public RestBean<List<Content>> contentListLike(HttpServletRequest request, String searchTerm){
        String token = request.getHeader("token");
        if(token != null) {
            DecodedJWT verify = JWTUtils.verify(token);
            String username = verify.getClaim("username").asString();
            int idByUsername = contentService.getIdByUsername(username);
            List<Content> result = contentService.getNotesByUserIdWithFuzzySearch(idByUsername, searchTerm);
            return RestBean.success(result);
        }
        return RestBean.failure(500,"搜索失败");
    }
}
