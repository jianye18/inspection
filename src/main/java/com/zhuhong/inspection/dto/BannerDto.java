package com.zhuhong.inspection.dto;

import com.zhuhong.inspection.model.Banner;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author qq892
 */
@Data
public class BannerDto extends Banner {

    @ApiModelProperty(name = "name", value = "修改前的轮播图名称", example = "123.png")
    private String oldBannerName;

}
