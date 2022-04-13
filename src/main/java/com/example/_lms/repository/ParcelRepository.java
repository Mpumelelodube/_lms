package com.example._lms.repository;

import com.example._lms.domain.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParcelRepository extends JpaRepository<Parcel,Long> {
}
