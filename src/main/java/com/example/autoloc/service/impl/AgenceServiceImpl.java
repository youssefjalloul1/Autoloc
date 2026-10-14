package com.example.autoloc.service.impl;

import com.example.autoloc.domain.Agence;
import com.example.autoloc.exception.ResourceNotFoundException;
import com.example.autoloc.repository.IAgenceRepository;
import com.example.autoloc.service.IAgenceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgenceServiceImpl implements IAgenceService {

    private final IAgenceRepository agenceRepository;

    public AgenceServiceImpl(IAgenceRepository agenceRepository) {
        this.agenceRepository = agenceRepository;
    }

    @Override
    public Agence create(Agence agence) {
        return agenceRepository.save(agence);
    }

    @Override
    public Agence findById(Long id) {
        return agenceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agence", id));
    }

    @Override
    public List<Agence> findAll() {
        return agenceRepository.findAll();
    }

    @Override
    public Agence update(Long id, Agence agence) {
        Agence existing = findById(id);
        existing.setNom(agence.getNom());
        existing.setVille(agence.getVille());
        existing.setAdresse(agence.getAdresse());
        existing.setTelephone(agence.getTelephone());
        return agenceRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        agenceRepository.deleteById(id);
    }

    @Override
    public long count() {
        return agenceRepository.count();
    }

    @Override
    public boolean existsById(Long id) {
        return agenceRepository.existsById(id);
    }
}
