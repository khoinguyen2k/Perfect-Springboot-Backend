package com.backend.repository;

import com.backend.model.PackageBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageBoxRepository extends JpaRepository<PackageBox, Integer> {
}
