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

    @Column(name="replay_content")
    private String replayContent;

    @Column(name="is_replayed")
    private boolean isReplayed = false;

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
}
