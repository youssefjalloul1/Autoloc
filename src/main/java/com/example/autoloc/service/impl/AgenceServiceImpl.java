package com.example.autoloc.service.impl;

import com.example.autoloc.exception.ResourceNotFoundException;
import com.example.autoloc.repository.IAgenceRepository;
import com.example.autoloc.service.IAgenceService;
import com.example.autoloc.web.dto.AgenceDTO;
import com.example.autoloc.web.mapper.AgenceMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AgenceServiceImpl implements IAgenceService {

    private final IAgenceRepository agenceRepository;
    private final AgenceMapper agenceMapper;

    public AgenceServiceImpl(IAgenceRepository agenceRepository, AgenceMapper agenceMapper) {
        this.agenceRepository = agenceRepository;
        this.agenceMapper = agenceMapper;
    }

    @Override
    public AgenceDTO creer(AgenceDTO dto) {
        return agenceMapper.toDto(agenceRepository.save(agenceMapper.toEntity(dto)));
    }

    @Override
    @Transactional(readOnly = true)
    public AgenceDTO findById(Long id) {
        return agenceMapper.toDto(agenceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agence", id)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AgenceDTO> findAll() {
        return agenceRepository.findAll().stream().map(agenceMapper::toDto).toList();
    }

    @Override
    public AgenceDTO update(Long id, AgenceDTO dto) {
        var existing = agenceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agence", id));
        existing.setNom(dto.getNom());
        existing.setVille(dto.getVille());
        existing.setAdresse(dto.getAdresse());
        existing.setTelephone(dto.getTelephone());
        return agenceMapper.toDto(agenceRepository.save(existing));
    }

    @Override
    public void deleteById(Long id) {
        agenceRepository.deleteById(id);
    }
}
