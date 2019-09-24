package com.tyrael.sell.VO;

import lombok.Data;

/**
 * Http请求返回的最外层对象
 */

@Data
public class ResultVO<T> {

    /** 错误码 */
    private Integer code;

    /** 提示内容 */
    private String msg;

    /** 具体内容 */
    private T data;
}
