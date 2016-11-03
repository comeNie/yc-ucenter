package com.ai.platform.common.test.cache;


import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;


import com.ai.yc.ucenter.constants.CacheNSMapper;

import com.ai.yc.ucenter.util.CacheFactoryUtil;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class CacheTest {


	
    @Test
    public void testWrite() throws Exception {
        CacheServiceStart.main(new String[] {});
    }

  


    @Test
    public void getUUID() {
        System.out.println(UUID.randomUUID().toString());
    }
  
    @Test
    public void testGnSubject() throws Exception {
        //测试证件号码转换
        ICacheClient cacheClient = CacheFactoryUtil.getCacheClient(CacheNSMapper.CACHE_GN_SUBJECT);
        String data = cacheClient.hget(CacheNSMapper.CACHE_GN_SUBJECT,
                "2.SLP.100000");
        System.out.println(data);
//        SysParam singleParam=iCacheSV.getSysParam("SLP", "ORD_OD_FEE_TOTAL", "PAY_STYLE", "1");
//        List<SysParam> paramList=iCacheSV.getSysParams("BIS-ST", "CHL_CHANNEL", "STATE");
//                
//        System.out.println("singleParam="+JSON.toJSONString(singleParam));
//        System.out.println("paramList="+JSON.toJSONString(paramList));
    }
    /*@Test
	public void testCacheSysParam(){
		
		ICacheSV sv=new CacheSVImpl();
		SysParam singleParam=sv.getSysParam("all1", "CM_CUST", "STATE", "1");
		List<SysParam> paramList=sv.getSysParams("all1", "CM_CUST", "STATE");
        		
		System.out.println("singleParam="+JSON.toJSONString(singleParam));
		System.out.println("paramList="+JSON.toJSONString(paramList));
	}*/
    
    /*@Test
    public void testReadGnSysParam2() throws Exception {
    	//测试证件号码转换
        ICacheSV sv = new CacheSVImpl();
        SysParam singleParam=sv.getSysParam("BIS-TEST", "ORD_ORDER", "STATE", "11");
		System.out.println("singleParam="+JSON.toJSONString(singleParam));
    }*/
    
 

    
    
    
}