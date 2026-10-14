package com.example.autoloc.service.impl;

import com.example.autoloc.domain.Employe;
import com.example.autoloc.exception.ResourceNotFoundException;
import com.example.autoloc.repository.IEmployeRepository;
import com.example.autoloc.service.IEmployeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeServiceImpl implements IEmployeService {

    private final IEmployeRepository employeRepository;

    public EmployeServiceImpl(IEmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    @Override
    public Employe create(Employe employe) {
        return employeRepository.save(employe);
    }

    @Override
    public Employe findById(Long id) {
        return employeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employe", id));
    }

    @Override
    public List<Employe> findAll() {
        return employeRepository.findAll();
    }

    @Override
    public Employe update(Long id, Employe employe) {
        Employe existing = findById(id);
        existing.setNom(employe.getNom());
        existing.setPrenom(employe.getPrenom());
        existing.setRole(employe.getRole());
        existing.setAgence(employe.getAgence());
        return employeRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        employeRepository.deleteById(id);
    }

    @Override
    public long count() {
        return employeRepository.count();
    }

    @Override
    public boolean existsById(Long id) {
        return employeRepository.existsById(id);
    }
}
