package com.zhuhong.inspection.base;

/**
 * 自定义异常
 *
 * @Author: jian.ye
 * @Date: 2019/10/16 10:47
 */
public class BussinessException extends RuntimeException {

    private Integer code;

    /**
     * 继承Exception，加入错误状态值
     *
     * @param exceptionEnum
     * @Author: jian.ye
     * @Date: 2019/10/16 10:45
     */
    public BussinessException (ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
        this.code = exceptionEnum.getCode();
    }

    /**
     * 自定义错误信息
     *
     * @param code
     * @param msg
     * @Author: jian.ye
     * @Date: 2019/10/16 10:47
     */
    public BussinessException (Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
