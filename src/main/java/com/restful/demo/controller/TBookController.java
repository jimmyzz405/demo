package com.restful.demo.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.restful.demo.entity.TBook;
import com.restful.demo.mapper.TBookMapper;
import com.restful.demo.service.impl.TBookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jimmy zZ
 * @since 2020-07-06
 */
@RestController
public class TBookController {

    @Autowired
    private TBookMapper bookMapper;
    @Autowired
    private TBookServiceImpl bookService;

    @RequestMapping("/getBook/{bookId}")
    public Object getBook(@PathVariable Integer bookId){
        TBook book = bookMapper.selectById(bookId);
        return book;
    }

    @RequestMapping(value = "/delBook/{bookId}",method = RequestMethod.DELETE)
    public Object delBook(@PathVariable Integer bookId){

       return bookMapper.deleteById(bookId);
    }

//    @RequestMapping("/editBook/")

    @RequestMapping(value = "/addBook",method = RequestMethod.POST)
    public Object addBook( TBook book){
        return bookMapper.insert(book);
    }

    @RequestMapping(value = "/editBook",method = RequestMethod.POST)
    public Object editBook(TBook book){
        return bookMapper.updateById(book);
    }





}
