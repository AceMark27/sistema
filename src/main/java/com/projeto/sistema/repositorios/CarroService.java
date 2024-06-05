package com.projeto.sistema.repositorios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.sistema.modelos.Carro;

@Service
public class CarroService {
    @Autowired
    private CarroRepository carroRepository;

    public Iterable<Carro> getAllCarros() {
        return carroRepository.findAll();
    }
    public Optional<Carro> getCarroById(Long id) {
        return carroRepository.findById(id);
    }
}
