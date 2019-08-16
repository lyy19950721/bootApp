package com.mipo.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class BaseDTO {

    @ApiModelProperty(name = "pageNum", value = "分页显示的当前页码, 值大于0", required = true)
    @Min(value = 1, message = "ERROR_CODE_001")
    private int pageNum;

    @ApiModelProperty(name = "pageSize", value = "每页显示条数, 值大于0", required = true)
    @Min(value = 1, message = "ERROR_CODE_001")
    private int pageSize;
}
