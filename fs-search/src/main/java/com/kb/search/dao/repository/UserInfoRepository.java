package com.kb.search.dao.repository;

import com.kb.search.pojo.search.UserInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author syg
 * @version 1.0
 */
public interface UserInfoRepository extends ElasticsearchRepository<UserInfo,Long> {

}
