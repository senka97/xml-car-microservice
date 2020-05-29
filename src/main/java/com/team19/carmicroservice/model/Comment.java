package com.team19.carmicroservice.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="from_comment")
    private Long fromComment;

    @Column(name="content")
    private String content;

    @Column(name="date_time")
    private LocalDateTime dateTime;

    @Column(name="reply_content")
    private String replyContent;

    @Column(name="is_replied")
    private boolean isReplied = false;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Car car;

    public Comment() {
    }

    public Comment(Long id, Long fromComment, String content, LocalDateTime dateTime, Car car) {
        this.id = id;
        this.fromComment = fromComment;
        this.content = content;
        this.dateTime = dateTime;
        this.car = car;
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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
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
}
