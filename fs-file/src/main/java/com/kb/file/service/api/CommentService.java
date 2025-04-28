package com.kb.file.service.api;

import com.kb.common.base.BaseResponse;
import com.kb.file.pojo.comment.Comment;
import com.kb.file.pojo.comment.SecondCommentParam;

/**
 * @author syg
 * @version 1.0
 */
public interface CommentService {
    /**
     * 获取一级评论
     * @param videoId
     * @return
     */
    BaseResponse getFirstComment(Long videoId);

    /**
     * 获取二级评论
     * @param secondCommentParam
     * @return
     */
    BaseResponse getSecondComment(SecondCommentParam secondCommentParam);

    /**
     * 回复
     * @param comment
     * @return
     */
    BaseResponse reply(Comment comment);

    /**
     * 更新评论
     * @param comment
     * @return
     */
    BaseResponse update(Comment comment);

    /**
     * 删除评论
     * @param id
     * @return
     */
    BaseResponse delete(Long id);
}
