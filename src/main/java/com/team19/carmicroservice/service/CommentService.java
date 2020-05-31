package com.team19.carmicroservice.service;

import com.team19.carmicroservice.dto.CommentDTO;
import com.team19.carmicroservice.dto.NewCommentDTO;
import com.team19.carmicroservice.dto.NewReplyDTO;

import java.util.ArrayList;

public interface CommentService {

    ArrayList<CommentDTO> getCommentsForCar(Long id);
    Boolean createComment(NewCommentDTO comment);
    Boolean replyComment(Long id, NewReplyDTO reply);
}
