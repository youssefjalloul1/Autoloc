package com.example.autoloc.service;

import com.example.autoloc.web.dto.PaiementDTO;
import java.util.List;

public interface IPaiementService {
    PaiementDTO creer(PaiementDTO dto);
    PaiementDTO findById(Long id);
    List<PaiementDTO> findAll();
    PaiementDTO update(Long id, PaiementDTO dto);
    void deleteById(Long id);
}
