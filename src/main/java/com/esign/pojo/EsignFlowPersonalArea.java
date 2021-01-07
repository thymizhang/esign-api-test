package com.esign.pojo;

import lombok.Data;

/**
 * 流程盖章个人签署区
 *
 *         {
 *             "signerAccountId":"faea8237c61a4fdea864ee8d7621e14f",
 *             "authorizedAccountId":"2c7de24aff3340f5b944ebac49545b8e",
 *             "actorIndentityType":2,
 *             "fileId":"fe7df2f477d649c18ebcfdfffeba253d",
 *             "order":1,
 *             "assignedPosbean":true,
 *             "posBean":{
 *                 "posPage":"1",
 *                 "posX":158.72531,
 *                 "posY":431.05658
 *             },
 *             "sealType":"0",
 *             "sealId":"0123",
 *             "signType":1
 *         }
 *
 * @Author thymi
 * @Date 2020/12/30
 */
@Data
public class EsignFlowPersonalArea {


    /**
     * 签署操作人个人账号标识，即操作本次签署的个人，如需e签宝通知用户签署，则系统向该账号下绑定的手机、邮箱发送签署链接
     */
    String signerAccountId;

    /**
     * 签约主体账号标识，即本次签署对应任务的归属方，如传入机构id，则签署完成后，本任务可在企业账号下进行管理，默认是签署操作人个人
     */
    String authorizedAccountId;

    /**
     * 机构签约类别，当签约主体为机构时必传：2-机构盖章；
     */
    int actorIndentityType;

    /**
     * 文件file id
     */
    String fileId;

    /**
     * 签署区顺序，默认1,且不小于1，顺序越小越先处理
     */
    int order;

    /**
     * 是否指定位置，如指定位置则posBean不可为空；一旦设置为TRUE，表示用户签署时不允许更新位置
     */
    boolean assignedPosbean;

    /**
     * 签署区位置信息 。signType为0时，本参数无效； signType为1时, 页码和XY坐标不能为空； signType为2时, 页码和Y坐标不能为空
     */
    EsignPosBean posBean;

    /**
     * 签署方式，
     *
     * 0-手绘签名（企业主体签署不支持手绘），
     *
     * 1-模板印章签名，
     *
     * 多种类型时逗号分割，为空不限制
     */
    String sealType;

    /**
     * 印章id
     *
     * (1)开通了实名签署，该字段只支持个人签署场景指定一个印章
     *
     * (2)没有开通实名签署，同时支持企业主体签署场景以及个人签署场景指定一个印章
     */
    String sealId;

    /**
     * 签署类型，0-不限，1-单页签署，2-骑缝签署，默认1
     */
    int signType;
}
