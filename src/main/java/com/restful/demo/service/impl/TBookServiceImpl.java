package com.restful.demo.service.impl;

import com.restful.demo.entity.TBook;
import com.restful.demo.mapper.TBookMapper;
import com.restful.demo.service.ITBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jimmy zZ
 * @since 2020-07-06
 */
@Service
public class TBookServiceImpl extends ServiceImpl<TBookMapper, TBook> implements ITBookService {

}
