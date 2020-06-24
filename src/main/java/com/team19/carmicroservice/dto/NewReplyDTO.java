package com.team19.carmicroservice.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class NewReplyDTO {

    @NotEmpty(message="Content must not be null or empty.")
    @Size(min=2, message = "Content length must be 2 characters minimum")
    @Pattern(regexp="^[a-zA-Z0-9?'!,:;. ]*$", message="Content must not include special characters.")
    private String replyContent;

    public NewReplyDTO()
    {

    }

    public NewReplyDTO(String replyContent)
    {
        this.replyContent = replyContent;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }
}
