package com.team19.carmicroservice.controller;

import com.team19.carmicroservice.dto.NewCommentDTO;
import com.team19.carmicroservice.dto.NewReplyDTO;
import com.team19.carmicroservice.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
}
