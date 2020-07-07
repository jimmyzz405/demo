package com.restful.demo;

import com.restful.demo.entity.TBook;
import com.restful.demo.mapper.TBookMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private TBookMapper bookMapper;

    @Test
    void contextLoads() {
        TBook book = new TBook();
        book.setBookId(9);
        book.setBookName("好书");
//        book.setBookPrice(6.99);
        bookMapper.insert(book);
    }

}
