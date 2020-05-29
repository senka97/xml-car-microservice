package com.team19.carmicroservice.controller;

import com.team19.carmicroservice.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
