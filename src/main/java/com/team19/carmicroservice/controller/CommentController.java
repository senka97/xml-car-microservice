package com.team19.carmicroservice.controller;

import com.team19.carmicroservice.dto.CommentDTO;
import com.team19.carmicroservice.dto.NewCommentDTO;
import com.team19.carmicroservice.dto.NewReplyDTO;
import com.team19.carmicroservice.model.Comment;
import com.team19.carmicroservice.service.impl.CommentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    Logger logger = LoggerFactory.getLogger(CommentController.class);

    @GetMapping(value = "/comments/{id}")
    //@PreAuthorize("hasAuthority('comment_read')")   treba svi da mogu da citaju komentare
    public ResponseEntity<?> getCommentsForCar(@PathVariable("id") Long adId) {

        ArrayList<CommentDTO> comments = commentService.getCommentsForCar(adId);
        if (comments != null)
        {
            return new ResponseEntity<>(comments, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>("Not found.", HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(value="/comments")
    @PreAuthorize("hasAuthority('comment_create')")
    public ResponseEntity<?> createComment(@Valid @RequestBody NewCommentDTO comment)
    {
        if(commentService.createComment(comment))
        {
            logger.info("Creating comment - Comment for ad id: " + comment.getAdId() + " created");
            return new ResponseEntity<>("Comment created",HttpStatus.CREATED);
        }
        else{
            logger.info("Creating comment - Comment for ad id: " + comment.getAdId() + " couldn't be created");
            return new ResponseEntity<>("Error creating comment",HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping(value="/comments/{id}")
    @PreAuthorize("hasAuthority('comment_update')")
    public ResponseEntity<?> replyComment(@PathVariable("id") Long comId, @Valid @RequestBody NewReplyDTO reply)
    {
        if(commentService.replyComment(comId,reply))
        {
            logger.info("Posting reply - Reply for comment id: " + comId + " created");
            return new ResponseEntity<>("Reply created", HttpStatus.OK);
        }
        else
        {
            logger.info("Posting reply - Reply for comment id: " + comId + " couldn't be created");
            return new ResponseEntity<>("Error creating reply", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(value = "/comments")
    @PreAuthorize("hasAuthority('comment_read')")
    public ResponseEntity<?> getAllPostedComments() {
        ArrayList<CommentDTO> comments = commentService.getAllPostedComments();
        if (!comments.isEmpty()) {
            return new ResponseEntity<>(comments, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/replies")
    @PreAuthorize("hasAuthority('comment_read')")
    public ResponseEntity<?> getAllPostedReplies() {
        List<Comment> comments = commentService.getAllPostedReplies();
        List<CommentDTO> commentDTOS = new ArrayList<>();

        if (!comments.isEmpty()) {
            for (Comment c : comments) {
                commentDTOS.add(new CommentDTO(c));
            }
            return new ResponseEntity<>(commentDTOS, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/comments/{id}/approve")
    @PreAuthorize("hasAuthority('comment_update')")
    public ResponseEntity<?> approveComment(@PathVariable Long id) {

        if(commentService.approveComment(id)) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/comments/{id}/reject")
    @PreAuthorize("hasAuthority('comment_update')")
    public ResponseEntity<?>  rejectComment(@PathVariable Long id) {

        if(commentService.rejectComment(id)) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/replies/{id}/approve")
    @PreAuthorize("hasAuthority('comment_update')")
    public ResponseEntity<?> approveReply(@PathVariable Long id) {

        if(commentService.approveReply(id)) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/replies/{id}/reject")
    @PreAuthorize("hasAuthority('comment_update')")
    public ResponseEntity<?> rejectReply(@PathVariable Long id) {

        if(commentService.rejectReply(id)) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/comments/client/{id}")
    @PreAuthorize("hasAuthority('comment_update')")
    public ResponseEntity<?> hideCommentRequestForBLockedAndRemovedClient(@PathVariable Long id) {
        commentService.hideCommentRequestsForBlockedAndRemovedClient(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping(value = "/comments/client/{id}")
    public List<CommentDTO> findAllRejectedComments(@PathVariable Long id) {
        return commentService.getAllRejectedComments(id);
    }
}
