package com.test;

import com.lhy.MyApplication;
import com.lhy.mapper.Person;
import com.lhy.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2019/8/20 16:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyApplication.class)
public class AppTest {

    @Autowired
    private PersonService personService;

    @Test
    public void addPerson() {
        Person person = new Person();
        person.setName(UUID.randomUUID().toString());
        person.setAge((int)(Math.random()*100 + 10));
        personService.add(person);
    }

    @Test
    public void listPerson() {
        List<Person> all = personService.findAll();
        if(!CollectionUtils.isEmpty(all)){
            for (Person person : all) {
                System.out.println(person.toString());
            }
        }
    }

    @Test
    public void complexOperator(){
        Person person = new Person();
        person.setName(UUID.randomUUID().toString());
        person.setAge((int)(Math.random()*100 + 10));
        personService.complex(person);
    }


}
