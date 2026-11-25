package com.example.autoloc.service.impl;

import com.example.autoloc.domain.Employe;
import com.example.autoloc.exception.ResourceNotFoundException;
import com.example.autoloc.repository.IAgenceRepository;
import com.example.autoloc.repository.IEmployeRepository;
import com.example.autoloc.service.IEmployeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeServiceImpl implements IEmployeService {

    private final IEmployeRepository employeRepository;
    private final IAgenceRepository agenceRepository;

    public EmployeServiceImpl(IEmployeRepository employeRepository,
                               IAgenceRepository agenceRepository) {
        this.employeRepository = employeRepository;
        this.agenceRepository = agenceRepository;
    }

    @Override
    public Employe create(Employe employe) {
        return employeRepository.save(employe);
    }

    @Override
    @Transactional(readOnly = true)
    public Employe findById(Long id) {
        return employeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employe", id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employe> findAll() {
        return employeRepository.findAll();
    }

    @Override
    public Employe update(Long id, Employe employe) {
        var existing = findById(id);
        existing.setNom(employe.getNom());
        existing.setPrenom(employe.getPrenom());
        existing.setRole(employe.getRole());
        return employeRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        employeRepository.deleteById(id);
    }

    @Override
    public void affecterEmployeAAgence(Long employeId, Long agenceId) {
        var employe = employeRepository.findById(employeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employe", employeId));
        var agence = agenceRepository.findById(agenceId)
                .orElseThrow(() -> new ResourceNotFoundException("Agence", agenceId));
        employe.setAgence(agence);
        employeRepository.save(employe);
    }

    @Override
    public void desaffecterEmployeDeAgence(Long employeId) {
        var employe = employeRepository.findById(employeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employe", employeId));
        employe.setAgence(null);
        employeRepository.save(employe);
    }
}
