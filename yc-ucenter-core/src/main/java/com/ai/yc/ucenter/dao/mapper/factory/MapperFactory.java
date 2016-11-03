package com.ai.yc.ucenter.dao.mapper.factory;


import javax.annotation.PostConstruct;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.yc.ucenter.dao.mapper.interfaces.UcMembersMapper;
import com.ai.yc.ucenter.dao.mapper.interfaces.UcMembersOperationMapper;




@Component
public class MapperFactory {

    private static SqlSessionTemplate sqlSessionTemplate;
    
    @Autowired
    private SqlSessionTemplate st;

    @PostConstruct
    void init() {
        setSqlSessionTemplate(st);
    }
    
    public static SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    public static void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        MapperFactory.sqlSessionTemplate = sqlSessionTemplate;
    }

  

    public static UcMembersMapper getUcMembersMapper() {
        return sqlSessionTemplate.getMapper(UcMembersMapper.class);
    }
    public static UcMembersOperationMapper getUcMembersOperationMapper() {
        return sqlSessionTemplate.getMapper(UcMembersOperationMapper.class);
    }
   
    
    
}
