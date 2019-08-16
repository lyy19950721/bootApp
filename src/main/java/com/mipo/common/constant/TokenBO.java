package com.mipo.common.constant;

import lombok.Data;

@Data
public class TokenBO {

    private Long id;

    private Long partnerId;

    private String managerName;

    private String loginEmail;

    private Integer roleId;
}
