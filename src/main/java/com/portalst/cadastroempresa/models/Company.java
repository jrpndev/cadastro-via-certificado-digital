package com.portalst.cadastroempresa.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String razaoSocial;
    private String cnpj;
    private String nomeFantasia;
    private String perfil;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;
    private boolean isExternalUser;
}
