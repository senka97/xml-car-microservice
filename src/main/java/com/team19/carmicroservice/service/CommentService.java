package com.team19.carmicroservice.service;

import com.team19.carmicroservice.dto.CommentDTO;
import com.team19.carmicroservice.dto.NewCommentDTO;
import com.team19.carmicroservice.dto.NewReplyDTO;
import com.team19.carmicroservice.model.Comment;

import java.util.ArrayList;
import java.util.List;

public interface CommentService {

    ArrayList<CommentDTO> getCommentsForCar(Long id);
    Boolean createComment(NewCommentDTO comment);
    Boolean replyComment(Long id, NewReplyDTO reply);
    ArrayList<CommentDTO> getAllPostedComments();
    List<Comment> getAllPostedReplies();
    boolean approveComment(Long id);
    boolean rejectComment(Long id);
    boolean approveReply(Long id);
    boolean rejectReply(Long id);
    void hideCommentRequestsForBlockedAndRemovedClient(Long id);
}
