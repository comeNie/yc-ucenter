package com.ai.yc.ucenter.api.cache.interfaces;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.yc.ucenter.api.cache.param.SysParam;
import com.ai.yc.ucenter.api.cache.param.SysParamMultiCond;
import com.ai.yc.ucenter.api.cache.param.SysParamSingleCond;

/**
 * 公共中心缓存读取服务<br>
 * Date: 2016年5月30日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author gucl
 */
@Path("/cacheservice")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface ICacheSV {

    /**
     * 获取字典参数列表
     * @param param
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author gucl
     * @ApiDocMethod
     * @ApiCode GN_0205
     * @RestRelativeURL cacheservice/getSysParamList
	 */
	@POST
	@Path("/getSysParamList")
    public List<SysParam> getSysParamList(SysParamMultiCond param)
    		throws BusinessException,SystemException;

    
    /**
     * 获取单条字典参数
     * @param param
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author gucl
     * @ApiDocMethod
     * @ApiCode GN_0206
     * @RestRelativeURL cacheservice/getSysParamSingle
	 */
	@POST
	@Path("/getSysParamSingle")
    public SysParam getSysParamSingle(SysParamSingleCond param) throws BusinessException,SystemException;

    
    
}
