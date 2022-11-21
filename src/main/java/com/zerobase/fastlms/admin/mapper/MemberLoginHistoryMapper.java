package com.zerobase.fastlms.admin.mapper;


import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.admin.model.MemberHistoryParam;
import com.zerobase.fastlms.admin.model.MemberParam;
import com.zerobase.fastlms.member.model.MemberHistoryInput;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberLoginHistoryMapper {

    List<MemberHistoryInput> selectListMyLogin(MemberHistoryParam parameter);

}