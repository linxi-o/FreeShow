package com.kb.search.dao.repository;

import com.kb.search.pojo.search.Video;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author syg
 * @version 1.0
 */
public interface VideoRepository extends ElasticsearchRepository<Video,Long> {
    /**
     * find by title(name) like
     * @param keyword
     * @return
     */
    Video findByNameLike(String keyword);
}
