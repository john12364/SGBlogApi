package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.domin.entity.Comment;
import com.sangeng.domin.result.ResponseResult;
import com.sangeng.domin.vo.CommentReplyVo;


/**
 * 评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2022-05-14 23:36:50
 */
public interface CommentService extends IService<Comment> {

    //根据文章id查询评论信息
    ResponseResult commentList(String type,Long articleId, Integer pageNum, Integer pageSize);
    //对文章或者用户进行评论
    ResponseResult addComment(CommentReplyVo commentReplyVo);
}
