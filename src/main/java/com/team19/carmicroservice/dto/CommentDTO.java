package com.team19.carmicroservice.dto;

import java.time.LocalDateTime;

public class CommentDTO {

    private Long id;

    private Long fromComment;

    private String userName;

    private String userLastname;

    private String content;

    private LocalDateTime dateTime;

    private String replyContent;

    private boolean isReplied= false;

    private String commentStatus;

    private String replyStatus;

    private Long carId;

    public CommentDTO()
    {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public boolean getIsReplied() {
        return isReplied;
    }

    public void setIsReplied(boolean replied) {
        isReplied = replied;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLastname() {
        return userLastname;
    }

    public void setUserLastname(String userLastname) {
        this.userLastname = userLastname;
    }

    public String getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
    }

    public String getReplyStatus() {
        return replyStatus;
    }

    public void setReplyStatus(String replyStatus) {
        this.replyStatus = replyStatus;
    }
}
