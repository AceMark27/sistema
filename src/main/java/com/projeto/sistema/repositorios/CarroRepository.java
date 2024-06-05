package com.projeto.sistema.repositorios;

import com.projeto.sistema.modelos.Carro;
import org.springframework.data.repository.CrudRepository;

public interface CarroRepository extends CrudRepository<Carro, Long> {
}
