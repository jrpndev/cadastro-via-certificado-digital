package com.portalst.cadastroempresa.repositories;

import com.portalst.cadastroempresa.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository extends JpaRepository<Company, Long> {
}
