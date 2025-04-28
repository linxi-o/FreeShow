package com.kb.file.dao;

import com.kb.file.pojo.comment.Comment;
import com.kb.file.pojo.comment.SecondCommentParam;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author syg
 * @version 1.0
 */
@Repository
public interface CommentMapper {
    /**
     * 一级评论
     * @param videoId
     * @return
     */
    List<Comment> getFirstComment(Long videoId);

    /**
     * 二级评论
     * @param secondCommentParam
     * @return
     */
    List<Comment> getSecondComment(SecondCommentParam secondCommentParam);

    /**
     * 添加
     * @param comment
     * @return
     */
    Integer add(Comment comment);

    /**
     * 更新
     * @param comment
     * @return
     */
    Integer update(Comment comment);

    /**
     * 删除
     * @param id
     * @return
     */
    Integer delete(Long id);
}
