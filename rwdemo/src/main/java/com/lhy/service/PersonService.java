package com.lhy.service;

import com.lhy.mapper.Person;
import com.lhy.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2019/8/20 16:05
 */
@Service
public class PersonService {

    @Autowired
    private PersonMapper mapper;

    //    @Transactional(propagation = Propagation.NESTED)
    public List<Person> findAll() {
        return mapper.findAll();
    }

    @Transactional
    public Long add(Person person) {
        return mapper.add(person);
    }


    @Transactional
    public void complex(Person person) {
        add(person);
        List<Person> all = findAll();
        if (!CollectionUtils.isEmpty(all)) {
            for (Person p : all) {
                System.out.println(p.toString());
            }
        }
    }
}
