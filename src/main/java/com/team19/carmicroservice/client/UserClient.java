package com.team19.carmicroservice.client;

import com.team19.carmicroservice.dto.CommentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.ArrayList;

@FeignClient(name = "user-service")
public interface UserClient {

    @PostMapping(value="user/getCommentCreator", produces = "application/json")
    ArrayList<CommentDTO> getCommentCreator(@RequestBody ArrayList<CommentDTO> comments);
}
