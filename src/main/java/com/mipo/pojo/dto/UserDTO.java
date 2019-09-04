package com.mipo.pojo.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author ：lyy
 * @date ：Created in 2019-08-26 11:24
 * @description：
 * @modified By：
 * @version: $
 */
@Data
@ToString
public class UserDTO {

    @NotEmpty(message = "BASE_ERROR_001")
    @NotNull(message = "BASE_ERROR_001")
    private String userName;

    @NotEmpty(message = "BASE_ERROR_001")
    @NotNull(message = "BASE_ERROR_001")
    private String passWord;
}
