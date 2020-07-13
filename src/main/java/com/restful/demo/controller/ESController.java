package com.restful.demo.controller;

import com.alibaba.fastjson.JSON;
import com.restful.demo.entity.User;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.event.ObjectChangeListener;
import java.io.IOException;


@RestController
@RequestMapping("/api/v2")
public class ESController {
    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;


    //根据id查询用户
    @RequestMapping(value = "/users/{userId}",method = RequestMethod.GET)
    public Object getUser(@PathVariable Integer userId) throws IOException {
        GetRequest getRequest = new GetRequest("user_index",userId.toString());
        GetResponse getResponse=client.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(getResponse.getSourceAsString());//打印文档的内容
        System.out.println(getResponse);
        return getResponse.getSourceAsString();
    }

    //根据id删除用户
    @RequestMapping(value = "/users/{userId}",method = RequestMethod.DELETE)
    public Object delUser(@PathVariable Integer userId) throws IOException {
        DeleteRequest deleteRequest=new DeleteRequest("user_index",userId.toString());
        deleteRequest.timeout("1s");

        DeleteResponse deleteResponse= client.delete(deleteRequest,RequestOptions.DEFAULT);
        System.out.println(deleteResponse.status());
        return deleteResponse.status();

    }

    //添加用户(随机id)
    @RequestMapping(value = "/users",method = RequestMethod.PUT)
    public Object addUser(User user) throws IOException {
        IndexRequest request = new IndexRequest("user_index");
//        request.id("1");
        request.timeout(TimeValue.timeValueSeconds(1));
//        request.timeout("1s");
        request.source(JSON.toJSONString(user), XContentType.JSON);
        IndexResponse indexResponse=client.index(request,RequestOptions.DEFAULT);
        System.out.println(indexResponse.toString());
        System.out.println(indexResponse.status());
        return indexResponse.status();


    }

    //根据id更新用户
    @RequestMapping(value = "/users/{userId}",method = RequestMethod.POST)
    public Object updateUser(User user,@PathVariable Integer userId) throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("user_index",userId.toString());
        updateRequest.timeout("1s");

//        User user = new User("hhb",250);
        updateRequest.doc(JSON.toJSONString(user),XContentType.JSON);
        UpdateResponse update = client.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println(update.status());
        return update.status();
    }
}
