package com.team19.carmicroservice.model;

import com.team19.carmicroservice.enums.CommentStatus;
import com.team19.carmicroservice.enums.ReplyStatus;

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

    @Enumerated(EnumType.STRING)
    private CommentStatus commentStatus = CommentStatus.POSTED;

    @Enumerated(EnumType.STRING)
    private ReplyStatus replyStatus = ReplyStatus.NOT_POSTED;

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

    public CommentStatus getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(CommentStatus commentStatus) {
        this.commentStatus = commentStatus;
    }

    public ReplyStatus getReplyStatus() {
        return replyStatus;
    }

    public void setReplyStatus(ReplyStatus replyStatus) {
        this.replyStatus = replyStatus;
    }
}
