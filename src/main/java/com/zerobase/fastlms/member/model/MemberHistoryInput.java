package com.zerobase.fastlms.member.model;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ToString
@Data
public class MemberHistoryInput {
    private long id;
    private String userId;
    private LocalDateTime loginDt;
    private String loginIp;
    private String UserAgent;

    public String getLastLoginDtText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        return loginDt != null ? loginDt.format(formatter) : "";
    }
}
