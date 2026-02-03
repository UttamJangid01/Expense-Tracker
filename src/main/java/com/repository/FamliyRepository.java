package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Famliy;

@Repository
public interface FamliyRepository extends JpaRepository<Famliy, Long> {

  Famliy findTopByOrderByIdDesc();

  Famliy findByFamliyName(String familyName);
}
