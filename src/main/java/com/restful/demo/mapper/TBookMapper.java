package com.restful.demo.mapper;

import com.restful.demo.entity.TBook;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jimmy zZ
 * @since 2020-07-06
 */

@Mapper
@Repository
public interface TBookMapper extends BaseMapper<TBook> {

}
