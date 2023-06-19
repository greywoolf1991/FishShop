package com.example.fishshop.repositories;

import com.example.fishshop.models.ClientM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientR extends JpaRepository<ClientM, Long> {
    List<ClientM> findClientMSByActual (boolean actual);
}
