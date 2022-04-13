package com.example._lms.repository;

import com.example._lms.domain.Sender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SenderRepository extends JpaRepository<Sender,Long> {
}
