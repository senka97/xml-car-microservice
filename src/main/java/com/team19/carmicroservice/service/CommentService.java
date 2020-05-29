package com.team19.carmicroservice.service;

import com.team19.carmicroservice.dto.CommentDTO;

import java.util.ArrayList;

public interface CommentService {

    ArrayList<CommentDTO> getCommentsForCar(Long id);
}
