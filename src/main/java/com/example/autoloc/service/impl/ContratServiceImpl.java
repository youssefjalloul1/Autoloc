package com.example.autoloc.service.impl;

import com.example.autoloc.domain.Contrat;
import com.example.autoloc.exception.ResourceNotFoundException;
import com.example.autoloc.repository.IContratRepository;
import com.example.autoloc.service.IContratService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContratServiceImpl implements IContratService {

    private final IContratRepository contratRepository;

    public ContratServiceImpl(IContratRepository contratRepository) {
        this.contratRepository = contratRepository;
    }

    @Override
    public Contrat create(Contrat contrat) {
        return contratRepository.save(contrat);
    }

    @Override
    public Contrat findById(Long id) {
        return contratRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat", id));
    }

    @Override
    public List<Contrat> findAll() {
        return contratRepository.findAll();
    }

    @Override
    public Contrat update(Long id, Contrat contrat) {
        Contrat existing = findById(id);
        existing.setDateSignature(contrat.getDateSignature());
        existing.setMontantTotal(contrat.getMontantTotal());
        existing.setValide(contrat.isValide());
        existing.setReservation(contrat.getReservation());
        return contratRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        contratRepository.deleteById(id);
    }

    @Override
    public long count() {
        return contratRepository.count();
    }

    @Override
    public boolean existsById(Long id) {
        return contratRepository.existsById(id);
    }
}
