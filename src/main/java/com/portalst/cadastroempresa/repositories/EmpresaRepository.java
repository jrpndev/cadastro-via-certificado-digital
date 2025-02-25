package com.portalst.cadastroempresa.repositories;

import com.portalst.cadastroempresa.models.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
