package com.team19.carmicroservice.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="from_comment")
    private String fromComment;

    @Column(name="content")
    private String content;

    @Column(name="date_time")
    private LocalDateTime dateTime;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Car car;

    public Comment() {
    }

    public Comment(Long id, String fromComment, String content, LocalDateTime dateTime, Car car) {
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

    public String getFromComment() {
        return fromComment;
    }

    public void setFromComment(String fromComment) {
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
}
