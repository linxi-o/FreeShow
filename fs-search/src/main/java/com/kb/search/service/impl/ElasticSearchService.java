package com.kb.search.service.impl;

import com.kb.common.exception.InfoException;
import com.kb.search.dao.repository.UserInfoRepository;
import com.kb.search.dao.repository.VideoRepository;
import com.kb.search.pojo.search.UserInfo;
import com.kb.search.pojo.search.Video;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author syg
 * @version 1.0
 */
@Service
@Slf4j
public class ElasticSearchService {
    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Resource
    private RestHighLevelClient restHighLevelClient;

    public List<Map<String,Object>> getContents(String keyword,
                                                Integer pageNo,
                                                Integer pageSize){
        String[] indexes={"videos","user-infos"};
        SearchRequest searchRequest=new SearchRequest(indexes);
        SearchSourceBuilder sourceBuilder=new SearchSourceBuilder();
        // 分页
        sourceBuilder.from(pageNo-1);
        sourceBuilder.size(pageSize);
        //构建多字段查询，定义查找规则
        MultiMatchQueryBuilder matchQueryBuilder= QueryBuilders.multiMatchQuery(keyword,"nickname","name","Introduction");
        //把查找条件添加到查询体
        sourceBuilder.query(matchQueryBuilder);
        searchRequest.source(sourceBuilder);
        //设置超时时间
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        // 高亮显示
        String[] array={"nickname","name","Introduction"};
        HighlightBuilder highlightBuilder=new HighlightBuilder();
        //遍历要高亮的字段，加入高亮器
        for(String key:array){
            highlightBuilder.fields().add(new HighlightBuilder.Field(key));
        }
        // 多个字段进行高亮,要置为false
        highlightBuilder.requireFieldMatch(false);
        highlightBuilder.preTags("<span style=\"color:red\">");
        highlightBuilder.postTags("</span>");
        sourceBuilder.highlighter(highlightBuilder);
        //执行搜索
        SearchResponse searchResponse;
        try {
            //执行查找
            searchResponse= restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("es 搜索出错",e);
            throw new InfoException("es 搜索出错",e);
        }
        List<Map<String,Object>> mapList=new ArrayList<>();
        //处理搜索结果
        for (SearchHit hit: searchResponse.getHits()){
            Map<String, HighlightField> highlightFieldMap = hit.getHighlightFields();
            Map<String, Object> sourceMap = hit.getSourceAsMap();
            for(String key:array){
                HighlightField field=highlightFieldMap.get(key);
                if(field!=null){
                    //多条内容可能是同一条对话
                    Text[] fragments=field.fragments();
                    String str = Arrays.toString(fragments);
                    str=str.substring(1,str.length()-1);
                    sourceMap.put(key,str);
                }
            }
            mapList.add(sourceMap);
        }
        return mapList;
    }
    public  void addUserInfo(UserInfo userInfo){
        userInfoRepository.save(userInfo);
    }

    public  void addVideo(Video video){
        videoRepository.save(video);
    }

    public  Video getVideo(String keyword){
        return videoRepository.findByNameLike(keyword);
    }

    public  void deleteAllVideos(){
        videoRepository.deleteAll();
    }

}
