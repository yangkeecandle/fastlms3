package com.zerobase.fastlms.admin.controller;

import com.zerobase.fastlms.admin.dto.BannerDto;
import com.zerobase.fastlms.admin.model.BannerInput;
import com.zerobase.fastlms.admin.service.BannerService;
import com.zerobase.fastlms.admin.service.CategoryService;
import com.zerobase.fastlms.course.controller.BaseController;
import com.zerobase.fastlms.course.dto.CourseDto;
import com.zerobase.fastlms.course.model.BannerParam;
import com.zerobase.fastlms.course.model.CourseInput;
import com.zerobase.fastlms.course.model.CourseParam;
import com.zerobase.fastlms.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Controller
public class AdminBannerController extends BaseController {

    private final BannerService bannerService;

    @GetMapping("/admin/banner/list.do")
    public String list(Model model, BannerParam parameter) {
        parameter.init();

        List<BannerDto> bannerList = bannerService.list(parameter);

        long totalCount = 0;
        if (!CollectionUtils.isEmpty(bannerList)) {
            totalCount = bannerList.get(0).getTotalCount();
        }
        String queryString = parameter.getQueryString();
        String pagerHtml = getPaperHtml(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);

        model.addAttribute("list", bannerList);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagerHtml);
        return "admin/banner/list";
    }

    @GetMapping(value = {"/admin/banner/add.do", "/admin/banner/edit.do"})
    public String add(Model model, HttpServletRequest request
            , BannerInput parameter) {

        boolean editMode = request.getRequestURI().contains("/edit.do");
        BannerDto detail = new BannerDto();

        if (editMode) {
            long id = parameter.getId();
            BannerDto existBanner = bannerService.getById(id);
            if (existBanner == null) {
                // error 처리
                model.addAttribute("message", "배너정보가 존재하지 않습니다.");
                return "common/error";
            }
            detail = existBanner;
            System.out.println(detail.getSubject());
            System.out.println(detail.getSortValue());
        }

        model.addAttribute("editMode", editMode);
        model.addAttribute("detail", detail);

        return "admin/banner/add";
    }


    @PostMapping(value = {"/admin/banner/add.do", "/admin/banner/edit.do"})
    public String addSubmit(Model model, HttpServletRequest request
            , MultipartFile file
            , BannerInput parameter) {

        String uploadPath = "C:/project/project3/fastlms3/src/main/resources/static/images/";

        if (file != null) {
            String originalFilename = file.getOriginalFilename();
            parameter.setFilename(file.getOriginalFilename());
            parameter.setUrlFilename("/images/"+ file.getOriginalFilename());

            try {
                file.transferTo(new File(uploadPath+originalFilename));
            } catch (IOException e) {
                log.info("############################ - 1");
                log.info(e.getMessage());
            }
        }


        boolean editMode = request.getRequestURI().contains("/edit.do");

        if (editMode) {
            long id = parameter.getId();
            BannerDto existBanner = bannerService.getById(id);
            if (existBanner == null) {
                // error 처리
                model.addAttribute("message", "배너정보가 존재하지 않습니다.");
                return "common/error";
            }

            boolean result = bannerService.set(parameter);

        } else {
            boolean result = bannerService.add(parameter);
        }

        return "redirect:/admin/banner/list.do";
    }

    @PostMapping("/admin/banner/delete.do")
    public String del(Model model, HttpServletRequest request
            , BannerInput parameter) {

        boolean result = bannerService.del(parameter.getIdList());

        return "redirect:/admin/banner/list.do";
    }
}
