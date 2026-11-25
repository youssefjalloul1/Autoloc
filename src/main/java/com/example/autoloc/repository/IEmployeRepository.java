package com.example.autoloc.repository;

import com.example.autoloc.domain.Employe;
import com.example.autoloc.domain.enums.RoleEmploye;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IEmployeRepository extends JpaRepository<Employe, Long> {

    // Ex 4 — Query Methods
    List<Employe> findByAgenceNom(String nomAgence);

    List<Employe> findByAgence_IdAgenceAndRole(Long agenceId, RoleEmploye role);

    List<Employe> findByNomContaining(String nom);
}