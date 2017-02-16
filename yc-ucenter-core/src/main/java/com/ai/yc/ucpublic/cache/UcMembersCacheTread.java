package com.ai.yc.ucpublic.cache;

import java.util.List;

import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import com.ai.yc.ucenter.constants.CacheNSMapper;
import com.ai.yc.ucenter.dao.mapper.bo.UcMembers;
import com.ai.yc.ucenter.util.CacheFactoryUtil;
import com.alibaba.fastjson.JSON;

public class UcMembersCacheTread extends Thread{
    private List<UcMembers> resultList;
    ICacheClient cacheClient = CacheFactoryUtil.getCacheClient(CacheNSMapper.CACHE_GN_AREA);
    
    public UcMembersCacheTread(List<UcMembers> resultList){
        this.resultList=resultList;
    }
    @Override
    public void run() {
        for (UcMembers ucMembers : resultList) {          
            String key=ucMembers.getUid().toString();
            cacheClient.hset(CacheNSMapper.CACHE_GN_AREA, key, JSON.toJSONString(ucMembers));
        } 
        //System.out.println("【"+Thread.currentThread().getName()+"】");
    } 
}
