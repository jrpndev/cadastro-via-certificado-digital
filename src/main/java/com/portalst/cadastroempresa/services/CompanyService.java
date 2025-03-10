package com.portalst.cadastroempresa.services;

import com.portalst.cadastroempresa.models.Company;
import com.portalst.cadastroempresa.models.StatusEnum;
import com.portalst.cadastroempresa.repositories.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private BaseRepository baseRepository;

    public Company save(Company company) {
        if (company.isExternalUser == true) {
            company.setStatus(StatusEnum.PENDING);
        } else {
            company.setStatus(StatusEnum.APPROVED);
        }
        return baseRepository.save(company);
    }
    

    public List<Company> list() {
        return baseRepository.findAll();
    }
}
