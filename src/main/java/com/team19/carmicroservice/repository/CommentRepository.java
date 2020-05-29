package com.team19.carmicroservice.repository;

import com.team19.carmicroservice.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT * FROM comment c WHERE c.car_id= ?1 "  , nativeQuery = true)
    ArrayList<Comment> findCommentsForCar(Long id);
}