package com.team19.carmicroservice.controller;

import com.team19.carmicroservice.dto.CommentDTO;
import com.team19.carmicroservice.dto.NewCommentDTO;
import com.team19.carmicroservice.dto.NewReplyDTO;
import com.team19.carmicroservice.model.Comment;
import com.team19.carmicroservice.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    @GetMapping(value = "/comments/{id}")
    //@PreAuthorize("hasAuthority('comment_read')")   treba svi da mogu da citaju komentare
    public ResponseEntity<?> getCommentsForCar(@PathVariable("id") Long adId)
    {
        return new ResponseEntity<>(commentService.getCommentsForCar(adId), HttpStatus.OK);
    }

    @PostMapping(value="/comments")
    @PreAuthorize("hasAuthority('comment_create')")
    public ResponseEntity<?> createComment(@RequestBody NewCommentDTO comment)
    {
        if(commentService.createComment(comment))
        {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @PutMapping(value="/comments/{id}")
    @PreAuthorize("hasAuthority('comment_update')")
    public ResponseEntity<?> replyComment(@PathVariable("id") Long comId, @RequestBody NewReplyDTO reply)
    {
        if(commentService.replyComment(comId,reply))
        {
            return new ResponseEntity<>(HttpStatus.OK);
        }else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @GetMapping(value = "/comments")
    @PreAuthorize("hasAuthority('allComments')")
    public ResponseEntity<?> getAllPostedComments() {
        ArrayList<CommentDTO> comments = commentService.getAllPostedComments();
        if (!comments.isEmpty()) {
            return new ResponseEntity<>(comments, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/replies")
    @PreAuthorize("hasAuthority('allReplies')")
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

    @PutMapping(value = "/comments/approve/{id}")
    @PreAuthorize("hasAuthority('comment_update')")
    public ResponseEntity<?> approveComment(@PathVariable Long id) {

        if(commentService.approveComment(id)) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/comments/reject/{id}")
    @PreAuthorize("hasAuthority('comment_update')")
    public ResponseEntity<?>  rejectComment(@PathVariable Long id) {

        if(commentService.rejectComment(id)) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/replies/approve/{id}")
    @PreAuthorize("hasAuthority('reply_update')")
    public ResponseEntity<?> approveReply(@PathVariable Long id) {

        if(commentService.approveReply(id)) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/replies/reject/{id}")
    @PreAuthorize("hasAuthority('reply_update')")
    public ResponseEntity<?> rejectReply(@PathVariable Long id) {

        if(commentService.rejectReply(id)) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
