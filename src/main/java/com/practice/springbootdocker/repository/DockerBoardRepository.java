package com.practice.springbootdocker.repository;

import com.practice.springbootdocker.domain.entity.DockerBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DockerBoardRepository extends JpaRepository<DockerBoard, Long> {
}
