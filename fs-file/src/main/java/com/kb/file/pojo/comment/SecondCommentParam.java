package com.kb.file.pojo.comment;

import com.kb.common.base.BaseQuery;

/**
 * @author syg
 * @version 1.0
 */
public class SecondCommentParam extends BaseQuery {

    private Long videoId;

    private Long parentId;

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
