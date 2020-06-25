package com.team19.carmicroservice.controller;

import com.team19.carmicroservice.dto.CommentDTO;
import com.team19.carmicroservice.dto.NewCommentDTO;
import com.team19.carmicroservice.dto.NewReplyDTO;
import com.team19.carmicroservice.model.Comment;
import com.team19.carmicroservice.security.CustomPrincipal;
import com.team19.carmicroservice.service.impl.CommentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.text.MessageFormat;
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
            logger.info("Creating comment-For adID: " + comment.getAdId() + " created");
            return new ResponseEntity<>("Comment created",HttpStatus.CREATED);
        }
        else{
            logger.info("Creating comment-For adID: " + comment.getAdId() + " couldn't be created");
            return new ResponseEntity<>("Error creating comment",HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping(value="/comments/{id}")
    @PreAuthorize("hasAuthority('comment_update')")
    public ResponseEntity<?> replyComment(@PathVariable("id") Long comId, @Valid @RequestBody NewReplyDTO reply)
    {
        if(commentService.replyComment(comId,reply))
        {
            logger.info("Posting reply-For commentID: " + comId + " created");
            return new ResponseEntity<>("Reply created", HttpStatus.OK);
        }
        else
        {
            logger.info("Posting reply-For commentID: " + comId + " couldn't be created");
            return new ResponseEntity<>("Error creating reply", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(value = "/comments")
    @PreAuthorize("hasAuthority('comment_read')")
    public ResponseEntity<?> getAllPostedComments() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal cp = (CustomPrincipal) auth.getPrincipal();

        ArrayList<CommentDTO> comments = commentService.getAllPostedComments();
        if (!comments.isEmpty()) {
            logger.info(MessageFormat.format("CO-read posted comments:UserID:{0}", cp.getUserID())); //CO-comment
            return new ResponseEntity<>(comments, HttpStatus.OK);
        }
        logger.info(MessageFormat.format("CO-no posted comments:UserID:{0}", cp.getUserID()));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/replies")
    @PreAuthorize("hasAuthority('comment_read')")
    public ResponseEntity<?> getAllPostedReplies() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal cp = (CustomPrincipal) auth.getPrincipal();

        List<Comment> comments = commentService.getAllPostedReplies();
        List<CommentDTO> commentDTOS = new ArrayList<>();

        if (!comments.isEmpty()) {
            for (Comment c : comments) {
                commentDTOS.add(new CommentDTO(c));
            }
            logger.info(MessageFormat.format("PRP-read posted replies:UserID:{0}", cp.getUserID())); //PRP- posted replies
            return new ResponseEntity<>(commentDTOS, HttpStatus.OK);
        }
        logger.info(MessageFormat.format("PRP-no posted replies:UserID:{0}", cp.getUserID())); //PRP- posted replies
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/comments/{id}/approve")
    @PreAuthorize("hasAuthority('comment_update')")
    public ResponseEntity<?> approveComment(@PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal cp = (CustomPrincipal) auth.getPrincipal();

        if(commentService.approveComment(id)) {
            logger.info(MessageFormat.format("CO-ID:{0}-approved;UserID:{1}", id, cp.getUserID()));
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        logger.info(MessageFormat.format("CO-ID:{0}-not found;UserID:{1}", id, cp.getUserID()));
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/comments/{id}/reject")
    @PreAuthorize("hasAuthority('comment_update')")
    public ResponseEntity<?>  rejectComment(@PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal cp = (CustomPrincipal) auth.getPrincipal();

        if(commentService.rejectComment(id)) {
            logger.info(MessageFormat.format("CO-ID:{0}-rejected;UserID:{1}", id, cp.getUserID()));
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        logger.info(MessageFormat.format("CO-ID:{0}-not rejected;UserID:{1}", id, cp.getUserID()));
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/replies/{id}/approve")
    @PreAuthorize("hasAuthority('comment_update')")
    public ResponseEntity<?> approveReply(@PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal cp = (CustomPrincipal) auth.getPrincipal();

        if(commentService.approveReply(id)) {
            logger.info(MessageFormat.format("RP-ID:{0}-approved;UserID:{1}", id, cp.getUserID()));
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        logger.info(MessageFormat.format("RP-ID:{0}-not approved;UserID:{1}", id, cp.getUserID()));
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/replies/{id}/reject")
    @PreAuthorize("hasAuthority('comment_update')")
    public ResponseEntity<?> rejectReply(@PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal cp = (CustomPrincipal) auth.getPrincipal();

        if(commentService.rejectReply(id)) {
            logger.info(MessageFormat.format("RP-ID:{0}-rejected;UserID:{1}", id, cp.getUserID()));
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        logger.info(MessageFormat.format("RP-ID:{0}-not rejected;UserID:{1}", id, cp.getUserID()));
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/comments/client/{id}")
    @PreAuthorize("hasAuthority('comment_update')")
    public ResponseEntity<?> hideCommentRequestForBLockedAndRemovedClient(@PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal cp = (CustomPrincipal) auth.getPrincipal();

        commentService.hideCommentRequestsForBlockedAndRemovedClient(id);
        logger.info(MessageFormat.format("CO-hide for B/R C;ClientID:{0};UserID:{1}", id, cp.getUserID())); //hide for blocked/removed client
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping(value = "/comments/client/{id}")
    public List<CommentDTO> findAllRejectedComments(@PathVariable Long id) {
        logger.info(MessageFormat.format("CO-read-rejected;ClientID:{0}", id));
        return commentService.getAllRejectedComments(id);
    }
}
