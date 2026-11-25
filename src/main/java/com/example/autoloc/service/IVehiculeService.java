package com.example.autoloc.service;

import com.example.autoloc.web.dto.VehiculeDTO;
import java.util.List;

public interface IVehiculeService {
    VehiculeDTO creer(VehiculeDTO dto);
    VehiculeDTO findById(Long id);
    List<VehiculeDTO> findAll();
    VehiculeDTO update(Long id, VehiculeDTO dto);
    void deleteById(Long id);
    void affecterVehiculeAAgence(Long vehiculeId, Long agenceId);
    void desaffecterVehiculeDeAgence(Long vehiculeId);
}
