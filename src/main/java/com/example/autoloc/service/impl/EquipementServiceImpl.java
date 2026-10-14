package com.example.autoloc.service.impl;

import com.example.autoloc.domain.Equipement;
import com.example.autoloc.exception.ResourceNotFoundException;
import com.example.autoloc.repository.IEquipementRepository;
import com.example.autoloc.service.IEquipementService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipementServiceImpl implements IEquipementService {

    private final IEquipementRepository equipementRepository;

    public EquipementServiceImpl(IEquipementRepository equipementRepository) {
        this.equipementRepository = equipementRepository;
    }

    @Override
    public Equipement create(Equipement equipement) {
        return equipementRepository.save(equipement);
    }

    @Override
    public Equipement findById(Long id) {
        return equipementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipement", id));
    }

    @Override
    public List<Equipement> findAll() {
        return equipementRepository.findAll();
    }

    @Override
    public Equipement update(Long id, Equipement equipement) {
        Equipement existing = findById(id);
        existing.setLibelle(equipement.getLibelle());
        return equipementRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        equipementRepository.deleteById(id);
    }

    @Override
    public long count() {
        return equipementRepository.count();
    }

    @Override
    public boolean existsById(Long id) {
        return equipementRepository.existsById(id);
    }
}
