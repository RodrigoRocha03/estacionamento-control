package com.api.estacionamentocontrol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.estacionamentocontrol.models.VagaEstacionamentoModel;

import java.util.UUID;

@Repository
public interface VagaEstacionamentoRepository extends JpaRepository<VagaEstacionamentoModel, UUID> {

	boolean existsByPlacaCarro(String placaCarro);

	boolean existsByVagaEstacionamentoNumero(String vagaEstacionamentoNumero);

	boolean existsByApartamentoAndBloco(String apartamento, String bloco);
}

