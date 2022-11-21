package com.zerobase.fastlms.admin.dto;

import com.zerobase.fastlms.admin.entity.Banner;
import com.zerobase.fastlms.admin.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BannerDto {

    long totalCount;

    Long id;
    String subject;
    String filename;
    String urlFilename;
    String link;
    LocalDateTime reqDt;
    String openSelect;
    long sortValue;
    boolean usingYn;

    //ADD COLUMNS
    int courseCount;


    public static List<BannerDto> of (List<Banner> banners) {
        if (banners != null) {
            List<BannerDto> bannerList = new ArrayList<>();
            for(Banner x : banners) {
                bannerList.add(of(x));
            }
            return bannerList;
        }

        return null;
    }

    public static BannerDto of(Banner banner) {
        return BannerDto.builder()
                .id(banner.getId())
                .subject(banner.getSubject())
                .filename(banner.getFilename())
                .reqDt(banner.getReqDt())
                .urlFilename(banner.getUrlFilename())
                .sortValue(banner.getSortValue())
                .openSelect(banner.getOpenSelect())
                .usingYn(banner.isUsingYn())
                .link(banner.getLink())
                .build();
    }


}

