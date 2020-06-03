package com.team19.carmicroservice.repository;

import com.team19.carmicroservice.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT * FROM comment c WHERE c.car_id= ?1 "  , nativeQuery = true)
    ArrayList<Comment> findCommentsForCar(Long id);

    @Query(value = "SELECT * FROM comment  WHERE comment_status = 'POSTED'", nativeQuery = true)
    ArrayList<Comment> findAllPosetdComments();

    @Query(value = "SELECT * FROM comment  WHERE reply_status = 'POSTED'", nativeQuery = true)
    List<Comment> findAllPostedReplies();

    ArrayList<Comment> findAllByFromComment(Long id);
    ArrayList<Comment> findAllByCar_OwnerId(Long id);
}
