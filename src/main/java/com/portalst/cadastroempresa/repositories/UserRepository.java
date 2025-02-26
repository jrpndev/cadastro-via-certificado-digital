package com.portalst.cadastroempresa.repositories;

import com.portalst.cadastroempresa.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);
}
