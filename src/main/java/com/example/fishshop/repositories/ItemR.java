package com.example.fishshop.repositories;

import com.example.fishshop.models.ItemM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemR extends JpaRepository<ItemM, Long> {
}
