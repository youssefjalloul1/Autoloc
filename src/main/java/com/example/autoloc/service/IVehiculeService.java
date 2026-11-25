package com.example.autoloc.service;

import com.example.autoloc.domain.enums.CategorieVehicule;
import com.example.autoloc.domain.enums.StatutVehicule;
import com.example.autoloc.web.dto.VehiculeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface IVehiculeService {
    VehiculeDTO creer(VehiculeDTO dto);
    VehiculeDTO findById(Long id);
    List<VehiculeDTO> findAll();
    VehiculeDTO update(Long id, VehiculeDTO dto);
    void deleteById(Long id);
    void affecterVehiculeAAgence(Long vehiculeId, Long agenceId);
    void desaffecterVehiculeDeAgence(Long vehiculeId);
    // Query methods
    List<VehiculeDTO> findByAgenceVille(String ville);
    Page<VehiculeDTO> findByStatut(StatutVehicule statut, Pageable pageable);
    List<VehiculeDTO> rechercherVehiculesDisponibles(String ville, CategorieVehicule categorie,
                                                     LocalDate dateDebut, LocalDate dateFin);
}
