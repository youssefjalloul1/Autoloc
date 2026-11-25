package com.example.autoloc.service;

import com.example.autoloc.web.dto.ContratDTO;
import java.util.List;

public interface IContratService {
    ContratDTO creer(ContratDTO dto);
    ContratDTO findById(Long id);
    List<ContratDTO> findAll();
    ContratDTO update(Long id, ContratDTO dto);
    void deleteById(Long id);
}