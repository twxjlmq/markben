package com.markben.common.enums;

import com.markben.common.utils.StringUtils;

/**
 * 是否类型
 * @author 乌草坡
 * @since 0.0.1
 */
public enum YesNoType {
    
    /**
     * 0 -- 否
     */
    NO(0, false, "0", "否"),
    /**
     * 1 -- 是
     */
    YES(1, true, "1", "是");
    
    private int index;
    private Boolean value;
    private String strValue;
    private String text;
    
    YesNoType(int index, boolean value, String strValue, String text) {
        this.index = index;
        this.value = value;
        this.strValue = strValue;
        this.text = text;
    }
    
    /**
     * 通过INDEX值获取是否类型对象
     * @param index 值
     * @return 返回index对应的类型对象
     */
    public static YesNoType getObj(int index) {
        YesNoType yesOrNo = null;
        for (YesNoType yesNo : YesNoType.values()) {
            if(yesNo.getIndex() == index) {
                yesOrNo = yesNo;
                break;
            }
        }
        return yesOrNo;
    }
    
    /**
     * 通过value值获取是否类型对象；如果没有获取对象，则返回默认对象；
     * 默认值为： {@link #NO}
     * @param index 值
     * @return 返回value对应的类型对象
     */
    public static YesNoType getSupportDefaultObj(int index) {
        YesNoType yesOrNo = getObj(index);
        if(null == yesOrNo) {
            yesOrNo = NO;
        }
        return yesOrNo;
    }
    
    /**
     * 通过value值获取是否类型对象
     * @param value 值
     * @return 返回value对应的类型对象
     */
    public static YesNoType getObj(boolean value) {
        YesNoType yesOrNo = null;
        for (YesNoType yesNo : YesNoType.values()) {
            if(yesNo.getValue() == value) {
                yesOrNo = yesNo;
                break;
            }
        }
        return yesOrNo;
    }
    
    /**
     * 通过value值获取是否类型对象；如果没有获取对象，则返回默认对象；
     * 默认值为： {@link #NO}
     * @param value 值
     * @return 返回value对应的类型对象
     */
    public static YesNoType getSupportDefaultObj(boolean value) {
        YesNoType yesOrNo = getObj(value);
        if(null == yesOrNo) {
            yesOrNo = NO;
        }
        return yesOrNo;
    }
    
    /**
     * 通过value值获取是否类型对象
     * @param strValue 字符串值
     * @return 返回value对应的类型对象
     */
    public static YesNoType getObjByStrValue(String strValue) {
        YesNoType yesOrNo = null;
        if(StringUtils.isEmpty(strValue)) {
            return yesOrNo;
        }
        for (YesNoType yesNo : YesNoType.values()) {
            if(yesNo.getStrValue().equals(strValue)) {
                yesOrNo = yesNo;
                break;
            }
        }
        return yesOrNo;
    }
    
    /**
     * 通过 <code>value</code> 值获取是否类型对象；如果没有获取对象，则返回默认对象；
     * 默认值为： {@link #NO}
     * @param strValue 字符串值
     * @return 返回是否类型对象
     */
    public static YesNoType getSupportDefaultObj(String strValue) {
        YesNoType yesOrNo = getObjByStrValue(strValue);
        if(null == yesOrNo) {
            yesOrNo = NO;
        }
        return yesOrNo;
    }
    
    /**
     * 通过text值获取是否类型对象
     * @param text 显示内容
     * @return 返回是否类型对象
     */
    public static YesNoType getObj(String text) {
        YesNoType yesOrNo = null;
        if(StringUtils.isEmpty(text)) {
            return yesOrNo;
        }
        for (YesNoType yesNo : YesNoType.values()) {
            if(yesNo.getText().equals(text)) {
                yesOrNo = yesNo;
                break;
            }
        }
        return yesOrNo;
    }
    
    public int getIndex() {
        return index;
    }
    
    public void setIndex(int index) {
        this.index = index;
    }
    
    public boolean getValue() {
        return value;
    }
    
    public void setValue(boolean value) {
        this.value = value;
    }
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }

    public String getStrValue() {
        return strValue;
    }
    
}
