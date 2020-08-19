package com.markben.rest.common.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @author 乌草坡
 * @since 1.0
 */
public class LabelValueVO {

    @ApiModelProperty(value = "显示文本")
    private String label;

    @ApiModelProperty(value = "文本对应的业务值")
    private String value;

    public LabelValueVO() {
    }

    public LabelValueVO(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
