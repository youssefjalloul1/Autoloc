package com.example.autoloc.service;

import com.example.autoloc.web.dto.AgenceDTO;
import java.util.List;

public interface IAgenceService {
    AgenceDTO creer(AgenceDTO dto);
    AgenceDTO findById(Long id);
    List<AgenceDTO> findAll();
    AgenceDTO update(Long id, AgenceDTO dto);
    void deleteById(Long id);
}
