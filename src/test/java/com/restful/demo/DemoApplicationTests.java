package com.restful.demo;

import com.alibaba.fastjson.JSON;
import com.restful.demo.entity.TBook;
import com.restful.demo.entity.User;
import com.restful.demo.mapper.TBookMapper;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.lucene.util.QueryBuilder;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;

import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private TBookMapper bookMapper;

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;


    //创建索引
    @Test
    void contextLoads() throws IOException {
        CreateIndexRequest request=new CreateIndexRequest("test9");
        CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(createIndexResponse);
    }
//判断索引是否存在
    @Test
    void contextLoads2() throws IOException {
        GetIndexRequest request= new GetIndexRequest("test9");
        boolean exist = client.indices().exists(request,RequestOptions.DEFAULT);
        System.out.println(exist);
    }
//删除索引
    @Test
    void contextLoads3() throws IOException {
//        GetIndexRequest request= new GetIndexRequest("test9");
        DeleteIndexRequest request= new DeleteIndexRequest("test9");
        AcknowledgedResponse delete = client.indices().delete(request,RequestOptions.DEFAULT);
        System.out.println(delete.isAcknowledged());
    }


    //创建文档
    @Test
    void contextLoads4() throws IOException {
        User user = new User("zyf", 22);
        IndexRequest request = new IndexRequest("user_index");
        request.id("1");
        request.timeout(TimeValue.timeValueSeconds(1));
//        request.timeout("1s");
        request.source(JSON.toJSONString(user), XContentType.JSON);
        IndexResponse indexResponse=client.index(request,RequestOptions.DEFAULT);
        System.out.println(indexResponse.toString());
        System.out.println(indexResponse.status());

    }

    @Test
    void contextLoads5() throws IOException {
        GetRequest getRequest=new GetRequest("user_index","1");
        //不获取返回的source上下文
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        getRequest.storedFields("_none_");
        boolean exist= client.exists(getRequest,RequestOptions.DEFAULT);
        System.out.println(exist);

    }

    //获得文档的信息
    @Test
    void contextLoads6() throws IOException{
        GetRequest getRequest = new GetRequest("user_index","1");
        GetResponse getResponse=client.get(getRequest,RequestOptions.DEFAULT);
        System.out.println(getResponse.getSourceAsString());//打印文档的内容
        System.out.println(getResponse);
    }

    //更新文档的信息
    @Test
    void contextLoads7() throws IOException{
        UpdateRequest updateRequest = new UpdateRequest("user_index","1");
        updateRequest.timeout("1s");

        User user = new User("hhb",250);
        updateRequest.doc(JSON.toJSONString(user),XContentType.JSON);
        UpdateResponse update = client.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println(update.status());
    }

    //删除文档信息
    @Test
    void contextLoads8() throws IOException{
        DeleteRequest deleteRequest=new DeleteRequest("user_index","3");
        deleteRequest.timeout("1s");

        DeleteResponse deleteResponse= client.delete(deleteRequest,RequestOptions.DEFAULT);
        System.out.println(deleteResponse.status());
    }

    //批量插入数据
    @Test
    void contextLoads9() throws IOException{
        BulkRequest bulkRequest=new BulkRequest();
        bulkRequest.timeout("1s");
        List<User> list= new ArrayList<>();
        list.add(new User("zhangsan",2));
        list.add(new User("wangwu",3));
        list.add(new User("liliu",4));
        list.add(new User("sunqi",5));
        list.add(new User("wangba",6));

        for (int i = 0; i < list.size(); i++) {
            bulkRequest.add(
                    new IndexRequest("user_index")
                            .id(""+(i+1))
                            .source(JSON.toJSONString(list.get(i)),XContentType.JSON)
            );
        }

        BulkResponse bulkResponses= client.bulk(bulkRequest,RequestOptions.DEFAULT);
        System.out.println(bulkResponses.hasFailures());//是否失败


    }

    //查询
    @Test
    void contextLoads10() throws IOException{
        SearchRequest request= new SearchRequest("user_index");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //termQuery 精确查询；matchAllQuery 匹配所有
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name","zhangsan");
        searchSourceBuilder.query(termQueryBuilder);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        request.source(searchSourceBuilder);
        SearchResponse search = client.search(request, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(search.getHits()));
        System.out.println("=======================");
        for (SearchHit hit : search.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
            
        }

    }

}
