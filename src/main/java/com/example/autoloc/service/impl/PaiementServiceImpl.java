package com.example.autoloc.service.impl;

import com.example.autoloc.domain.Paiement;
import com.example.autoloc.exception.ResourceNotFoundException;
import com.example.autoloc.repository.IPaiementRepository;
import com.example.autoloc.service.IPaiementService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaiementServiceImpl implements IPaiementService {

    private final IPaiementRepository paiementRepository;

    public PaiementServiceImpl(IPaiementRepository paiementRepository) {
        this.paiementRepository = paiementRepository;
    }

    @Override
    public Paiement create(Paiement paiement) {
        return paiementRepository.save(paiement);
    }

    @Override
    public Paiement findById(Long id) {
        return paiementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paiement", id));
    }

    @Override
    public List<Paiement> findAll() {
        return paiementRepository.findAll();
    }

    @Override
    public Paiement update(Long id, Paiement paiement) {
        Paiement existing = findById(id);
        existing.setMontant(paiement.getMontant());
        existing.setDatePaiement(paiement.getDatePaiement());
        existing.setModePaiement(paiement.getModePaiement());
        existing.setContrat(paiement.getContrat());
        return paiementRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        paiementRepository.deleteById(id);
    }

    @Override
    public long count() {
        return paiementRepository.count();
    }

    @Override
    public boolean existsById(Long id) {
        return paiementRepository.existsById(id);
    }
}
