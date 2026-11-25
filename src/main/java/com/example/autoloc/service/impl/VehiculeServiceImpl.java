package com.example.autoloc.service.impl;

import com.example.autoloc.exception.ResourceNotFoundException;
import com.example.autoloc.repository.IAgenceRepository;
import com.example.autoloc.repository.IVehiculeRepository;
import com.example.autoloc.service.IVehiculeService;
import com.example.autoloc.web.dto.VehiculeDTO;
import com.example.autoloc.web.mapper.VehiculeMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VehiculeServiceImpl implements IVehiculeService {

    private final IVehiculeRepository vehiculeRepository;
    private final IAgenceRepository agenceRepository;
    private final VehiculeMapper vehiculeMapper;

    public VehiculeServiceImpl(IVehiculeRepository vehiculeRepository,
                                IAgenceRepository agenceRepository,
                                VehiculeMapper vehiculeMapper) {
        this.vehiculeRepository = vehiculeRepository;
        this.agenceRepository = agenceRepository;
        this.vehiculeMapper = vehiculeMapper;
    }

    @Override
    public VehiculeDTO creer(VehiculeDTO dto) {
        var vehicule = vehiculeMapper.toEntity(dto);
        if (dto.getAgenceId() != null) {
            vehicule.setAgence(agenceRepository.findById(dto.getAgenceId())
                    .orElseThrow(() -> new ResourceNotFoundException("Agence", dto.getAgenceId())));
        }
        return vehiculeMapper.toDto(vehiculeRepository.save(vehicule));
    }

    @Override
    @Transactional(readOnly = true)
    public VehiculeDTO findById(Long id) {
        return vehiculeMapper.toDto(vehiculeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicule", id)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehiculeDTO> findAll() {
        return vehiculeRepository.findAll().stream().map(vehiculeMapper::toDto).toList();
    }

    @Override
    public VehiculeDTO update(Long id, VehiculeDTO dto) {
        var existing = vehiculeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicule", id));
        existing.setImmatriculation(dto.getImmatriculation());
        existing.setMarque(dto.getMarque());
        existing.setModele(dto.getModele());
        existing.setCategorie(dto.getCategorie());
        existing.setTarifJournalier(dto.getTarifJournalier());
        existing.setStatut(dto.getStatut());
        return vehiculeMapper.toDto(vehiculeRepository.save(existing));
    }

    @Override
    public void deleteById(Long id) {
        vehiculeRepository.deleteById(id);
    }

    @Override
    public void affecterVehiculeAAgence(Long vehiculeId, Long agenceId) {
        var vehicule = vehiculeRepository.findById(vehiculeId)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicule", vehiculeId));
        var agence = agenceRepository.findById(agenceId)
                .orElseThrow(() -> new ResourceNotFoundException("Agence", agenceId));
        vehicule.setAgence(agence);
        vehiculeRepository.save(vehicule);
    }

    @Override
    public void desaffecterVehiculeDeAgence(Long vehiculeId) {
        var vehicule = vehiculeRepository.findById(vehiculeId)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicule", vehiculeId));
        vehicule.setAgence(null);
        vehiculeRepository.save(vehicule);
    }
}
