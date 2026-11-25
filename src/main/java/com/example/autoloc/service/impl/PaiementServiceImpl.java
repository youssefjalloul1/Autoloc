package com.example.autoloc.service.impl;

import com.example.autoloc.exception.ResourceNotFoundException;
import com.example.autoloc.repository.IContratRepository;
import com.example.autoloc.repository.IPaiementRepository;
import com.example.autoloc.service.IPaiementService;
import com.example.autoloc.web.dto.PaiementDTO;
import com.example.autoloc.web.mapper.PaiementMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PaiementServiceImpl implements IPaiementService {

    private final IPaiementRepository paiementRepository;
    private final IContratRepository contratRepository;
    private final PaiementMapper paiementMapper;

    public PaiementServiceImpl(IPaiementRepository paiementRepository,
                                IContratRepository contratRepository,
                                PaiementMapper paiementMapper) {
        this.paiementRepository = paiementRepository;
        this.contratRepository = contratRepository;
        this.paiementMapper = paiementMapper;
    }

    @Override
    public PaiementDTO creer(PaiementDTO dto) {
        var paiement = paiementMapper.toEntity(dto);
        if (dto.getContratId() != null) {
            paiement.setContrat(contratRepository.findById(dto.getContratId())
                    .orElseThrow(() -> new ResourceNotFoundException("Contrat", dto.getContratId())));
        }
        return paiementMapper.toDto(paiementRepository.save(paiement));
    }

    @Override
    @Transactional(readOnly = true)
    public PaiementDTO findById(Long id) {
        return paiementMapper.toDto(paiementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paiement", id)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaiementDTO> findAll() {
        return paiementRepository.findAll().stream().map(paiementMapper::toDto).toList();
    }

    @Override
    public PaiementDTO update(Long id, PaiementDTO dto) {
        var existing = paiementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paiement", id));
        existing.setMontant(dto.getMontant());
        existing.setDatePaiement(dto.getDatePaiement());
        existing.setModePaiement(dto.getModePaiement());
        return paiementMapper.toDto(paiementRepository.save(existing));
    }

    @Override
    public void deleteById(Long id) {
        paiementRepository.deleteById(id);
    }
}
