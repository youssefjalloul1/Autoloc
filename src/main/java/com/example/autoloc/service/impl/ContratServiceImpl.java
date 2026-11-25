package com.example.autoloc.service.impl;

import com.example.autoloc.exception.ResourceNotFoundException;
import com.example.autoloc.repository.IContratRepository;
import com.example.autoloc.repository.IReservationRepository;
import com.example.autoloc.service.IContratService;
import com.example.autoloc.web.dto.ChiffreAffaireAgenceDTO;
import com.example.autoloc.web.dto.ContratDTO;
import com.example.autoloc.web.mapper.ContratMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ContratServiceImpl implements IContratService {

    private final IContratRepository contratRepository;
    private final IReservationRepository reservationRepository;
    private final ContratMapper contratMapper;

    public ContratServiceImpl(IContratRepository contratRepository,
                               IReservationRepository reservationRepository,
                               ContratMapper contratMapper) {
        this.contratRepository = contratRepository;
        this.reservationRepository = reservationRepository;
        this.contratMapper = contratMapper;
    }

    @Override
    public ContratDTO creer(ContratDTO dto) {
        var contrat = contratMapper.toEntity(dto);
        if (dto.getReservationId() != null) {
            contrat.setReservation(reservationRepository.findById(dto.getReservationId())
                    .orElseThrow(() -> new ResourceNotFoundException("Reservation", dto.getReservationId())));
        }
        return contratMapper.toDto(contratRepository.save(contrat));
    }

    @Override
    @Transactional(readOnly = true)
    public ContratDTO findById(Long id) {
        return contratMapper.toDto(contratRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat", id)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContratDTO> findAll() {
        return contratRepository.findAll().stream().map(contratMapper::toDto).toList();
    }

    @Override
    public ContratDTO update(Long id, ContratDTO dto) {
        var existing = contratRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat", id));
        existing.setDateSignature(dto.getDateSignature());
        existing.setMontantTotal(dto.getMontantTotal());
        existing.setValide(dto.isValide());
        return contratMapper.toDto(contratRepository.save(existing));
    }

    @Override
    public void deleteById(Long id) {
        contratRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChiffreAffaireAgenceDTO> chiffreAffaireParAgence() {
        return contratRepository.chiffreAffaireParAgence();
    }
}
