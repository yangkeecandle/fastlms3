package com.zerobase.fastlms.admin.model;


import lombok.Data;

@Data
public class BannerInput {

    String idList;

    long id;
    String subject;
    long sortValue;
    boolean usingYn;
    String openSelect;
    String filename;
    String urlFilename;
    String link;

}