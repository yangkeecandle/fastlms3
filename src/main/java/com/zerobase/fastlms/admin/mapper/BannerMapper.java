package com.zerobase.fastlms.admin.mapper;

import com.zerobase.fastlms.admin.dto.BannerDto;
import com.zerobase.fastlms.admin.dto.CategoryDto;
import com.zerobase.fastlms.admin.model.CommonParam;
import com.zerobase.fastlms.admin.model.MemberParam;
import com.zerobase.fastlms.course.model.BannerParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BannerMapper {
    long selectListCount(BannerParam parameter);
    List<BannerDto> selectList(BannerParam parameter);
    List<BannerDto> selectUsingBanner();
}
