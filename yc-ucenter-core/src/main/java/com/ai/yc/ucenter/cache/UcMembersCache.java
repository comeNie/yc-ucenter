package com.ai.yc.ucenter.cache;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.sdk.cache.base.AbstractCache;
import com.ai.yc.ucenter.service.atom.members.IUcMembersAtomService;

@Component
public class UcMembersCache extends AbstractCache {
    private static final Logger logger = LoggerFactory.getLogger(UcMembersCache.class);

    @Autowired
    private IUcMembersAtomService iUcMembersAtomService;
    


    @Override
    public void write() throws Exception {
    	
    }

}
