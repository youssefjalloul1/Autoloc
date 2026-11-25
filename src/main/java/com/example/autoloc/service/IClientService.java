package com.example.autoloc.service;

import com.example.autoloc.web.dto.ClientDTO;
import java.util.List;

public interface IClientService {
    ClientDTO creer(ClientDTO dto);
    ClientDTO findById(Long id);
    List<ClientDTO> findAll();
    ClientDTO update(Long id, ClientDTO dto);
    void deleteById(Long id);
}
