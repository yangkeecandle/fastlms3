package com.zerobase.fastlms.admin.service;


import com.zerobase.fastlms.admin.dto.BannerDto;
import com.zerobase.fastlms.admin.dto.CategoryDto;
import com.zerobase.fastlms.admin.model.BannerInput;
import com.zerobase.fastlms.admin.model.CategoryInput;
import com.zerobase.fastlms.course.dto.CourseDto;
import com.zerobase.fastlms.course.model.BannerParam;
import com.zerobase.fastlms.course.model.CourseInput;

import java.util.List;

public interface BannerService {

    List<BannerDto> list();

    List<BannerDto> list(BannerParam parameter);

    BannerDto getById(long id);

    boolean set(BannerInput parameter);

    boolean add(BannerInput parameter);


    /**
     * 카테고리 삭제
     */
    boolean del(String id);

    /**
     * 사용중인 배너 정보
     */
    List<BannerDto> usingBanner();
}
