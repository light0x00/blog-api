package com.light.blog.core.test.tmp.join;

import com.light.blog.common.utils.CheckUtils;
import com.light.blog.common.utils.JsonUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2019/6/1
 */
public class JoinTest {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class User{
        int id;
        String name;
        int deptId;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Dept{
        int id;
        String name;
    }

    Map<String,Object> buildMap(List<String>keys,List<Object>vals){
        Map<String,Object> map = new HashMap<>();

        for (int i = 0; i < keys.size(); i++) {
            map.put(keys.get(i),vals.get(i));
        }
        return map;
    }

    @Test
    public void f(){

        List<Map> empTable = Arrays.asList(
                JsonUtils.parseJson("{\"id\":\"1\",\"name\":\"诸葛亮\",\"deptId\":1}",HashMap.class),
                JsonUtils.parseJson("{\"id\":\"2\",\"name\":\"诸葛瑾\",\"deptId\":2}",HashMap.class),
                JsonUtils.parseJson("{\"id\":\"3\",\"name\":\"陆逊\",\"deptId\":2}",HashMap.class),
                JsonUtils.parseJson("{\"id\":\"4\",\"name\":\"吕布\",\"deptId\":null}",HashMap.class)
        );

        List<Map> deptTable = Arrays.asList(
                JsonUtils.parseJson("{\"id\":1,\"name\":\"蜀汉\"}",HashMap.class),
                JsonUtils.parseJson("{\"id\":2,\"name\":\"东吴\"}",HashMap.class),
                JsonUtils.parseJson("{\"id\":3,\"name\":\"曹魏\"}",HashMap.class)
        );

        empLeftJoinDept(empTable,deptTable);
        empInnerJoinDept(empTable,deptTable);
        empRightJoinDept(empTable,deptTable);


    }

    //emp a left join dept b on a.dept_id=b.ud
    void empLeftJoinDept(List<Map> empTable,List<Map> deptTable){
        List<Map<Object,Object>> resultSet1 = new LinkedList<>();

        //模拟deptId列的二级索引
        Map<Object,List<Map>> indexes = deptTable.stream().collect(Collectors.groupingBy(d->d.get("id")));

        for (Map empRow : empTable) {
            //找出相关联的dept记录
            List<Map> matchedDeptRows =  indexes.get(empRow.get("deptId"));
            //没有关联的dept记录 则项结果集插入一条空记录
            if(CheckUtils.isEmpty(matchedDeptRows)){
                Map resultRow  = new HashMap<>();
                empRow.forEach((k,v)->resultRow.put("emp."+k,v)); //把userRow的所有列放到resultRow中
                resultSet1.add(resultRow);
            }
            //有则 分别将匹配行与userRow合并,然后插入结果集
            else {
                for (Map matchedDeptRow : matchedDeptRows) {
                    Map resultRow  = new HashMap<>();
                    empRow.forEach((k,v)->resultRow.put("emp."+k,v)); //把userRow的所有列放到resultRow中
                    matchedDeptRow .forEach((k,v)->resultRow.put("dept."+k,v)); //把userRow的所有列放到resultRow中
                    resultSet1.add(resultRow);
                }
            }
        }
        print(resultSet1);

    }

    void empInnerJoinDept(List<Map> empTable,List<Map> deptTable){
        List<Map<Object,Object>> resultSet1 = new LinkedList<>();

        Map<Object,List<Map>> indexes = deptTable.stream().collect(Collectors.groupingBy(d->d.get("id")));

        for (Map empRow : empTable) {

            List<Map> matchedDeptRows =  indexes.get(empRow.get("deptId"));
            //没有关联的dept记录 则不添加到结果集
            if(CheckUtils.isEmpty(matchedDeptRows)){
//
            }
            //有则 分别将匹配行与userRow合并,然后插入结果集
            else {
                for (Map matchedDeptRow : matchedDeptRows) {
                    Map resultRow  = new HashMap<>();
                    empRow.forEach((k,v)->resultRow.put("emp."+k,v)); //把userRow的所有列放到resultRow中
                    matchedDeptRow .forEach((k,v)->resultRow.put("dept."+k,v)); //把userRow的所有列放到resultRow中
                    resultSet1.add(resultRow);
                }
            }
        }
        print(resultSet1);

    }

    void empRightJoinDept(List<Map> empTable, List<Map> deptTable){
        List<Map<Object,Object>> resultSet1 = new LinkedList<>();

        Map<Object,List<Map>> indexes = empTable.stream().collect(Collectors.groupingBy(e->e.get("deptId")==null?"null":e.get("deptId")));

        for (Map deptRow : deptTable) {

            List<Map> matchedEmpRows =  indexes.get(deptRow.get("id"));
            //没有关联的emp记录 则项结果集插入一条空记录
            if(CheckUtils.isEmpty(matchedEmpRows)){
                Map resultRow  = new HashMap<>();
                deptRow.forEach((k,v)->resultRow.put("dept."+k,v)); //把userRow的所有列放到resultRow中
                resultSet1.add(resultRow);
            }
            //有则 分别将匹配行与userRow合并,然后插入结果集
            else {
                for (Map matchedEmpRow : matchedEmpRows) {
                    Map resultRow  = new HashMap<>();
                    deptRow.forEach((k,v)->resultRow.put("dept."+k,v)); //把userRow的所有列放到resultRow中
                    matchedEmpRow .forEach((k,v)->resultRow.put("emp."+k,v)); //把userRow的所有列放到resultRow中
                    resultSet1.add(resultRow);
                }
            }
        }
        print(resultSet1);

    }

    void print(List<Map<Object,Object>> table){
        System.out.println("-------------------------");
        for (Map<Object, Object> row : table) {
            System.out.println(row);
        }
        System.out.println("-------------------------");
    }


}
