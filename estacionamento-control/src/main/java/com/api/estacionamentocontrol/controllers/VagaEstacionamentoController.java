package com.api.estacionamentocontrol.controllers;

import com.api.estacionamentocontrol.dtos.VagaEstacionamentoDto;
import com.api.estacionamentocontrol.models.VagaEstacionamentoModel;
import com.api.estacionamentocontrol.services.VagaEstacionamentoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/parking-spot")
@Api(value = "Controle de Estacionamento API REST")
@CrossOrigin(origins = "*", maxAge = 3600)
public class VagaEstacionamentoController {

	final VagaEstacionamentoService vagaEstacionamentoService;

	public VagaEstacionamentoController(VagaEstacionamentoService vagaEstacionamentoService) {
		this.vagaEstacionamentoService = vagaEstacionamentoService;
	}

	@PostMapping
	@ApiOperation(value = "Cadastra uma vaga")
	public ResponseEntity<Object> saveVagaEstacionamento(
			@RequestBody @Valid VagaEstacionamentoDto vagaEstacionamentoDto) {
		if (vagaEstacionamentoService.existsByPlacaCarro(vagaEstacionamentoDto.getPlacaCarro())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Placa do carro ja esta sendo usada!");
		}
		if (vagaEstacionamentoService
				.existsByVagaEstacionamentoNumero(vagaEstacionamentoDto.getVagaEstacionamentoNumero())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Essa vaga ja esta sendo usada!");
		}
		if (vagaEstacionamentoService.existsByApartamentoAndBloco(vagaEstacionamentoDto.getApartamento(),
				vagaEstacionamentoDto.getBloco())) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("Conflict: Vaga de estacionamento já registrada para este apartamento/bloco!");
		}
		var vagaEstacionamentoModel = new VagaEstacionamentoModel();
		BeanUtils.copyProperties(vagaEstacionamentoDto, vagaEstacionamentoModel);
		vagaEstacionamentoModel.setDataRegistro(LocalDateTime.now(ZoneId.of("UTC")));
		return ResponseEntity.status(HttpStatus.CREATED).body(vagaEstacionamentoService.save(vagaEstacionamentoModel));
	}

	@GetMapping()
	@ApiOperation(value = "Retorna a lista do cadastro de vagas")
	public ResponseEntity<List<VagaEstacionamentoModel>> getAllVagaEstacionamento() {
		return ResponseEntity.status(HttpStatus.OK).body(vagaEstacionamentoService.findAll());
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna um cadastro unico")
	public ResponseEntity<Object> getOneVagaEstacionamento(@PathVariable(value = "id") UUID id) {
		Optional<VagaEstacionamentoModel> vagaEstacionamentoModelOptional = vagaEstacionamentoService.findById(id);
		if (!vagaEstacionamentoModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga de Estacionamento não encontrada.");
		}
		return ResponseEntity.status(HttpStatus.OK).body(vagaEstacionamentoModelOptional.get());
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Exclui um cadastro de vaga")
	public ResponseEntity<Object> deleteVagaEstacionamento(@PathVariable(value = "id") UUID id) {
		Optional<VagaEstacionamentoModel> vagaEstacionamentoModelOptional = vagaEstacionamentoService.findById(id);
		if (!vagaEstacionamentoModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga de Estacionamento não encontrada.");
		}
		vagaEstacionamentoService.delete(vagaEstacionamentoModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Parking Spot deleted successfully.");
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Atualiza um cadastro de vaga")
	public ResponseEntity<Object> updateVagaEstacionamento(@PathVariable(value = "id") UUID id,
			@RequestBody @Valid VagaEstacionamentoDto vagaEstacionamentoDto) {
		Optional<VagaEstacionamentoModel> vagaEstacionamentoModelOptional = vagaEstacionamentoService.findById(id);
		if (!vagaEstacionamentoModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga de Estacionamento não encontrada.");
		}
		var vagaEstacionamentoModel = new VagaEstacionamentoModel();
		BeanUtils.copyProperties(vagaEstacionamentoDto, vagaEstacionamentoModel);
		vagaEstacionamentoModel.setId(vagaEstacionamentoModelOptional.get().getId());
		vagaEstacionamentoModel.setDataRegistro(vagaEstacionamentoModelOptional.get().getDataRegistro());
		return ResponseEntity.status(HttpStatus.OK).body(vagaEstacionamentoService.save(vagaEstacionamentoModel));
	}
}
