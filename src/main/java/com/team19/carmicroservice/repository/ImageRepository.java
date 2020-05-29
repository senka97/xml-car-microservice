package com.team19.carmicroservice.repository;

import com.team19.carmicroservice.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
