package com.zhuhong.inspection.vo;

import com.zhuhong.inspection.model.Banner;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 叶剑
 */
@Data
public class BannerVo extends Banner {

    @ApiModelProperty(name = "viewUrl", value = "轮播图展示地址")
    private String viewUrl;

}
