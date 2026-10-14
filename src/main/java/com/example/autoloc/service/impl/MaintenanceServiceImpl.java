package com.example.autoloc.service.impl;

import com.example.autoloc.domain.Maintenance;
import com.example.autoloc.exception.ResourceNotFoundException;
import com.example.autoloc.repository.IMaintenanceRepository;
import com.example.autoloc.service.IMaintenanceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaintenanceServiceImpl implements IMaintenanceService {

    private final IMaintenanceRepository maintenanceRepository;

    public MaintenanceServiceImpl(IMaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }

    @Override
    public Maintenance create(Maintenance maintenance) {
        return maintenanceRepository.save(maintenance);
    }

    @Override
    public Maintenance findById(Long id) {
        return maintenanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Maintenance", id));
    }

    @Override
    public List<Maintenance> findAll() {
        return maintenanceRepository.findAll();
    }

    @Override
    public Maintenance update(Long id, Maintenance maintenance) {
        Maintenance existing = findById(id);
        existing.setDateDebut(maintenance.getDateDebut());
        existing.setDateFin(maintenance.getDateFin());
        existing.setDescription(maintenance.getDescription());
        existing.setVehicule(maintenance.getVehicule());
        return maintenanceRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        maintenanceRepository.deleteById(id);
    }

    @Override
    public long count() {
        return maintenanceRepository.count();
    }

    @Override
    public boolean existsById(Long id) {
        return maintenanceRepository.existsById(id);
    }
}
