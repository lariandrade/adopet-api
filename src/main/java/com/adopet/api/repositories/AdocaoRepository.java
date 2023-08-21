package com.adopet.api.repositories;

import com.adopet.api.models.Adocao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdocaoRepository extends JpaRepository<Adocao, Integer> {
}
