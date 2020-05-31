package com.team19.carmicroservice.dto;

public class NewCommentDTO {

    private Long fromComment;

    private String content;

    private Long adId;

    public NewCommentDTO()
    {

    }

    public Long getFromComment() {
        return fromComment;
    }

    public void setFromComment(Long fromComment) {
        this.fromComment = fromComment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getAdId() {
        return adId;
    }

    public void setAdId(Long adId) {
        this.adId = adId;
    }
}
