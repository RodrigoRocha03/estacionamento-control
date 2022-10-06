package com.api.estacionamentocontrol.models;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.time.LocalDateTime; 
import java.util.UUID;

@Entity
@Table(name = "TB_PARKING_SPOT")
public class VagaEstacionamentoModel implements Serializable {
	private static final long serialVersionUID = 1L;

	// @Id
	// @GeneratedValue(strategy = GenerationType.AUTO)

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(columnDefinition = "VARCHAR(36)")
	@Type(type = "uuid-char")
	private UUID id;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	@Column(nullable = false, unique = true, length = 10)
	private String vagaEstacionamentoNumero;
	@Column(nullable = false, unique = true, length = 7)
	private String placaCarro;
	@Column(nullable = false, length = 70)
	private String marcaCarro;
	@Column(nullable = false, length = 70)
	private String modeloCarro;
	@Column(nullable = false, length = 70)
	private String corCarro;
	@Column(nullable = false)
	private LocalDateTime dataRegistro;
	@Column(nullable = false, length = 130)
	private String responsavelNome;
	@Column(nullable = false, length = 30)
	private String apartamento;
	@Column(nullable = false, length = 30)
	private String bloco;

	public String getVagaEstacionamentoNumero() {
		return vagaEstacionamentoNumero;
	}

	public void setVagaEstacionamentoNumero(String vagaEstacionamentoNumero) {
		this.vagaEstacionamentoNumero = vagaEstacionamentoNumero;
	}

	public String getPlacaCarro() {
		return placaCarro;
	}

	public void setPlacaCarro(String placaCarro) {
		this.placaCarro = placaCarro;
	}

	public String getMarcaCarro() {
		return marcaCarro;
	}

	public void setMarcaCarro(String marcaCarro) {
		this.marcaCarro = marcaCarro;
	}

	public String getModeloCarro() {
		return modeloCarro;
	}

	public void setModeloCarro(String modeloCarro) {
		this.modeloCarro = modeloCarro;
	}

	public String getCorCarro() {
		return corCarro;
	}

	public void setCorCarro(String corCarro) {
		this.corCarro = corCarro;
	}

	public LocalDateTime getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(LocalDateTime dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public String getResponsavelNome() {
		return responsavelNome;
	}

	public void setResponsavelNome(String responsavelNome) {
		this.responsavelNome = responsavelNome;
	}

	public String getApartamento() {
		return apartamento;
	}

	public void setApartamento(String apartamento) {
		this.apartamento = apartamento;
	}

	public String getBloco() {
		return bloco;
	}

	public void setBloco(String bloco) {
		this.bloco = bloco;
	}

}
