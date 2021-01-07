package com.esign.pojo;

import lombok.Data;

/**
 * 流程盖章企业签署区（或平台签署区）
 *
 *         {
 *             "fileId":"fe7df2f477d649c18ebcfdfffeba253d",
 *             "order":1,
 *             "posBean":{
 *                 "posPage":"1",
 *                 "posX":158.72531,
 *                 "posY":431.05658
 *             },
 *             "sealId":"bcd7ffd9-5caf-4342-bd1c-02257229ccd5",
 *             "signType":1
 *         }
 *
 * @Author thymi
 * @Date 2020/12/30
 */
@Data
public class EsignFlowCompanyArea {

    /**
     * 文件file id
     */
    String fileId;

    /**
     * 签署顺序，默认1,且不小于1，顺序越小越先处理
     */
    int order;

    /**
     * 签署区位置信息, （signType为1时, 页码和XY坐标不能为空, signType为2时, 页码和Y坐标不能为空）
     */
    EsignPosBean posBean;

    /**
     * 印章id，仅限企业公章，暂不支持指定企业法定代表人印章，如不传，则采用账号下的默认印章
     *
     * 注意：正式环境，沙箱环境在管理后台：用户中心-印章管理，进行配置；
     */
    String sealId;

    /**
     * 签署类型， 1-单页签署，2-骑缝签署，默认1
     */
    int signType;

}
