package com.lhy.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2019/8/20 15:54
 */
@Mapper
public interface PersonMapper {

    List<Person> findAll();

    Long add(Person person);

}
