package com.portalst.cadastroempresa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.portalst.cadastroempresa.models.Company;
import com.portalst.cadastroempresa.services.CompanyService;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping
    public Company store(@RequestBody Company body) {
        return companyService.save(body);
    }

    @GetMapping
    public List<Company> list() {
        return companyService.list();
    }
}
