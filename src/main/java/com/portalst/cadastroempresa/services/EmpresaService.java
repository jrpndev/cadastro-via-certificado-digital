package com.portalst.cadastroempresa.services;

import com.portalst.cadastroempresa.models.Empresa;
import com.portalst.cadastroempresa.models.StatusCadastro;
import com.portalst.cadastroempresa.repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    public Empresa salvarEmpresa(Empresa empresa) {
        if (empresa.isUsuarioInterno()) {
            empresa.setStatus(StatusCadastro.APROVADO);
        } else {
            empresa.setStatus(StatusCadastro.PENDENTE);
        }
        return empresaRepository.save(empresa);
    }

    public List<Empresa> listarEmpresas() {
        return empresaRepository.findAll();
    }
}
