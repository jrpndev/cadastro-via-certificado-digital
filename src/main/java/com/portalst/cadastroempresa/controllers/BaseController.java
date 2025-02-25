package com.portalst.cadastroempresa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portalst.cadastroempresa.models.Company;
import com.portalst.cadastroempresa.services.BaseService;

@RestController
@RequestMapping("/api/companies")
public class BaseController {

    @Autowired
    private BaseService baseService;

    @PostMapping
    public Company store(@RequestBody Company body) {
        return baseService.save(body);
    }

    @GetMapping
    public List<Company> list() {
        return baseService.list();
    }
}
