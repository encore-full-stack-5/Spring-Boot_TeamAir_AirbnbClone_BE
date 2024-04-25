package com.air.comment.domain.repository;

import com.air.comment.domain.entity.HostComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HostcommentRepository
extends JpaRepository<HostComment, Integer> {
}
