package com.team19.carmicroservice.dto;

import javax.validation.constraints.*;

public class NewCommentDTO {

    @NotNull(message = "Id of user leaving comment must not be null")
    @Positive(message="Id of user leaving comment must be positive value.")
    private Long fromComment;

    @NotEmpty(message="Content must not be null or empty.")
    @Size(min=2, message = "Content length must be 2 characters minimum")
    @Pattern(regexp="^[a-zA-Z0-9?'!,:;. ]*$", message="Content must not include special characters.")
    private String content;

    @NotNull(message = "Id of ad must not be null")
    @Positive(message="Id of ad must be positive value")
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
