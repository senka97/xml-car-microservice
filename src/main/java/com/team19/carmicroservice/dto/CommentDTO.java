package com.team19.carmicroservice.dto;

import com.team19.carmicroservice.model.Car;
import java.time.LocalDateTime;

public class CommentDTO {

    private Long id;

    private Long fromComment;

    private String userName="Petar";

    private String userLastname="Petrovic";

    private String content;

    private LocalDateTime dateTime;

    private String replayContent;

    private boolean isReplayed = false;

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

    public String getReplayContent() {
        return replayContent;
    }

    public void setReplayContent(String replayContent) {
        this.replayContent = replayContent;
    }

    public boolean getIsReplayed() {
        return isReplayed;
    }

    public void setIsReplayed(boolean replayed) {
        isReplayed = replayed;
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
}
