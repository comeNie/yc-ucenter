package com.ai.yc.ucenter.dao.mapper.interfaces;

import com.ai.yc.ucenter.dao.mapper.bo.UcMembersOperation;
import com.ai.yc.ucenter.dao.mapper.bo.UcMembersOperationCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UcMembersOperationMapper {
    int countByExample(UcMembersOperationCriteria example);

    int deleteByExample(UcMembersOperationCriteria example);

    int deleteByPrimaryKey(Integer oid);

    int insert(UcMembersOperation record);

    int insertSelective(UcMembersOperation record);

    List<UcMembersOperation> selectByExample(UcMembersOperationCriteria example);

    UcMembersOperation selectByPrimaryKey(Integer oid);

    int updateByExampleSelective(@Param("record") UcMembersOperation record, @Param("example") UcMembersOperationCriteria example);

    int updateByExample(@Param("record") UcMembersOperation record, @Param("example") UcMembersOperationCriteria example);

    int updateByPrimaryKeySelective(UcMembersOperation record);

    int updateByPrimaryKey(UcMembersOperation record);
}