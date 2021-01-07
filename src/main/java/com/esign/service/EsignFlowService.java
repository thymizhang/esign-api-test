package com.esign.service;

import java.util.Map;

/**
 * e签宝签署流程服务
 *
 * @Author thymi
 * @Date 2020/12/30
 */
public interface EsignFlowService {

    /**
     * 上传文件
     *
     * @param fileId    文件id
     * @param uploadUrl 文件直传地址, 可以重复使用，但是只能传一样的文件，有效期一小时
     */
    void uploadFile(String fileId, String uploadUrl);

    /**
     * 获取上传文件地址
     *
     * @param contentMd5  先计算文件md5值，在对该md5值进行base64编码
     * @param contentType 目标文件的MIME类型，支持：application/octet-stream，application/pdf。注意，后面文件流上传的Content-Type参数要和这里一致，不然就会有403的报错
     * @param fileName 文件名称（必须带上文件扩展名，不然会导致后续发起流程校验过不去 示例：合同.pdf ）
     * @param fileSize 文件大小，单位byte
     * @return 上传文件id和地址
     */
    Map getUploadUrl(String contentMd5, boolean contentType, String fileName, long fileSize);
}
