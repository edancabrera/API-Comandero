package com.crov.comandero.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.crov.comandero.dto.MenuDTO;
import com.crov.comandero.model.CategoriaPlatillo;
import com.crov.comandero.model.Menu;
import com.crov.comandero.repository.CategoriaPlatilloRepository;

@Service
public class CategoriaPlatilloService {
    private final CategoriaPlatilloRepository categoriaPlatilloRepository;

    public CategoriaPlatilloService (CategoriaPlatilloRepository categoriaPlatilloRepository){
        this.categoriaPlatilloRepository = categoriaPlatilloRepository;
    }

    public List<MenuDTO> listarMenusExistentes(){
        List<Menu> menus = categoriaPlatilloRepository.findMenusUnicos();
        return menus.stream().map(menu -> new MenuDTO(menu.name())).collect(Collectors.toList());
    }

    public List<CategoriaPlatillo> listarCategoriasPorMesa(String mesa){
        Menu menu = Menu.valueOf(mesa.toUpperCase());
        return categoriaPlatilloRepository.findByMenu(menu);
    }
}