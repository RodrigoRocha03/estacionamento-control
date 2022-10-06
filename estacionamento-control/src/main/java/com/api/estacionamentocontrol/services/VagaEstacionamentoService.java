package com.api.estacionamentocontrol.services;


import com.api.estacionamentocontrol.models.VagaEstacionamentoModel;
import com.api.estacionamentocontrol.repositories.VagaEstacionamentoRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VagaEstacionamentoService {

	final VagaEstacionamentoRepository vagaEstacionamentoRepository;

	public VagaEstacionamentoService(VagaEstacionamentoRepository vagaEstacionamentoRepository) {
		this.vagaEstacionamentoRepository = vagaEstacionamentoRepository;
	}

	@Transactional
	public VagaEstacionamentoModel save(VagaEstacionamentoModel vagaEstacionamentoModel) {
		return vagaEstacionamentoRepository.save(vagaEstacionamentoModel);
	}

	public boolean existsByPlacaCarro(String placaCarro) {
		return vagaEstacionamentoRepository.existsByPlacaCarro(placaCarro);
	}

	public boolean existsByVagaEstacionamentoNumero(String vagaEstacionamentoNumero) {
		return vagaEstacionamentoRepository.existsByVagaEstacionamentoNumero(vagaEstacionamentoNumero);
	}

	public boolean existsByApartamentoAndBloco(String apartamento, String bloco) {
		return vagaEstacionamentoRepository.existsByApartamentoAndBloco(apartamento, bloco);
	}

	public Optional<VagaEstacionamentoModel> findById(UUID id) {
		return vagaEstacionamentoRepository.findById(id);
	}

	public List<VagaEstacionamentoModel> findAll() {
		return vagaEstacionamentoRepository.findAll();
	}

	@Transactional
	public void delete(VagaEstacionamentoModel vagaEstacionamentoModel) {
		vagaEstacionamentoRepository.delete(vagaEstacionamentoModel);
	}
}

