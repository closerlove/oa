package com.qf.oa.entity;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author cx
 * @Time 2018/11/7 16:09
 * @Version 1.0
 */
public class Mail {

    //标题
    private String title;

    //收件人
    private String to;

    //内容
    private String content;

    //附件
    private MultipartFile file;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "title='" + title + '\'' +
                ", to='" + to + '\'' +
                ", content='" + content + '\'' +
                ", file=" + file +
                '}';
    }
}
