package com.example.autoloc.service.impl;

import com.example.autoloc.exception.ResourceNotFoundException;
import com.example.autoloc.repository.IClientRepository;
import com.example.autoloc.service.IClientService;
import com.example.autoloc.web.dto.ClientDTO;
import com.example.autoloc.web.mapper.ClientMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClientServiceImpl implements IClientService {

    private final IClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientServiceImpl(IClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public ClientDTO creer(ClientDTO dto) {
        return clientMapper.toDto(clientRepository.save(clientMapper.toEntity(dto)));
    }

    @Override
    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        return clientMapper.toDto(clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client", id)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClientDTO> findAll() {
        return clientRepository.findAll().stream().map(clientMapper::toDto).toList();
    }

    @Override
    public ClientDTO update(Long id, ClientDTO dto) {
        var existing = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client", id));
        existing.setNom(dto.getNom());
        existing.setPrenom(dto.getPrenom());
        existing.setEmail(dto.getEmail());
        existing.setTelephone(dto.getTelephone());
        existing.setNumPermis(dto.getNumPermis());
        existing.setDateInscription(dto.getDateInscription());
        return clientMapper.toDto(clientRepository.save(existing));
    }

    @Override
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }
}
