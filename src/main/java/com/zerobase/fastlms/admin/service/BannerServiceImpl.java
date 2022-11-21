package com.zerobase.fastlms.admin.service;

import com.zerobase.fastlms.admin.dto.BannerDto;
import com.zerobase.fastlms.admin.dto.CategoryDto;
import com.zerobase.fastlms.admin.entity.Banner;
import com.zerobase.fastlms.admin.entity.Category;
import com.zerobase.fastlms.admin.mapper.BannerMapper;
import com.zerobase.fastlms.admin.mapper.CategoryMapper;
import com.zerobase.fastlms.admin.model.BannerInput;
import com.zerobase.fastlms.admin.model.CategoryInput;
import com.zerobase.fastlms.admin.repository.BannerRepository;
import com.zerobase.fastlms.admin.repository.CategoryRepository;
import com.zerobase.fastlms.course.dto.CourseDto;
import com.zerobase.fastlms.course.entity.Course;
import com.zerobase.fastlms.course.mapper.CourseMapper;
import com.zerobase.fastlms.course.model.BannerParam;
import com.zerobase.fastlms.course.model.CourseInput;
import com.zerobase.fastlms.course.model.CourseParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BannerServiceImpl implements BannerService {

    private final BannerRepository bannerRepository;
    private final BannerMapper bannerMapper;

    private Sort getSortBySortValueDesc() {
        return Sort.by(Sort.Direction.DESC, "sortValue");
    }

    @Override
    public List<BannerDto> list() {
        return null;
    }

    @Override
    public List<BannerDto> list(BannerParam parameter) {

        long totalCount = bannerMapper.selectListCount(parameter);

        List<BannerDto> list = bannerMapper.selectList(parameter);
        if (!CollectionUtils.isEmpty(list)) {
            int i = 0;
            for (BannerDto x : list) {
                x.setTotalCount(totalCount);
                i++;
            }
        }

        return list;
    }

    @Override
    public BannerDto getById(long id) {
        return bannerRepository.findById(id).map(BannerDto::of).orElse(null);
    }

    @Override
    public boolean set(BannerInput parameter) {

        Optional<Banner> optionalBanner = bannerRepository.findById(parameter.getId());
        if (!optionalBanner.isPresent()) {
            //수정할 데이터가 없음
            return false;
        }

        System.out.println("=========="+parameter.getSubject());
        System.out.println(parameter.getOpenSelect());
        System.out.println(parameter.getSortValue());
        System.out.println(parameter.getUrlFilename()+"=====");

        Banner banner = optionalBanner.get();
        banner.setSubject(parameter.getSubject());
        banner.setOpenSelect(parameter.getOpenSelect());
        banner.setSortValue(parameter.getSortValue());
        banner.setUsingYn(parameter.isUsingYn());
        banner.setLink(parameter.getLink());

        System.out.println(banner.getUrlFilename());

        if (parameter.getUrlFilename() != null && parameter.getUrlFilename().length() > 8){
            banner.setFilename(parameter.getFilename());
            banner.setUrlFilename(parameter.getUrlFilename());
        }

        bannerRepository.save(banner);

        return true;
    }

    @Override
    public boolean add(BannerInput parameter) {

        Banner banner = Banner.builder()
                .subject(parameter.getSubject())
                .openSelect(parameter.getOpenSelect())
                .sortValue(parameter.getSortValue())
                .filename(parameter.getFilename())
                .urlFilename(parameter.getUrlFilename())
                .reqDt(LocalDateTime.now())
                .link(parameter.getLink())
                .usingYn(parameter.isUsingYn())
                .build();

        bannerRepository.save(banner);

        return true;
    }

    @Override
    public boolean del(String idList) {

        if (idList != null && idList.length() > 0) {
            String[] ids = idList.split(",");
            for (String x : ids) {
                long id = 0L;
                try {
                    id = Long.parseLong(x);
                } catch (Exception e) {
                }

                if (id > 0) {
                    bannerRepository.deleteById(id);
                }
            }
        }

        return true;
    }

    @Override
    public List<BannerDto> usingBanner() {
        return bannerMapper.selectUsingBanner();
    }
}
