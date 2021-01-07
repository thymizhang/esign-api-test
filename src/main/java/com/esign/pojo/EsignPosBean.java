package com.esign.pojo;

import lombok.Data;

/**
 * 签署区位置信息
 *
 *             "posBean":{
 *                 "posPage":"1",
 *                 "posX":158.72531,
 *                 "posY":431.05658
 *             },
 *
 * @Author thymi
 * @Date 2020/12/30
 */
@Data
public class EsignPosBean {

    /**
     * 页码信息，当签署区signType为2时, 页码可以'-'分割, 其他情况只能是数字
     */
    String posPage;

    /**
     * x坐标
     */
    String posX;

    /**
     * y坐标
     */
    String posY;

    /**
     * 签署区宽，默认印章宽度
     */
    String width;
}
