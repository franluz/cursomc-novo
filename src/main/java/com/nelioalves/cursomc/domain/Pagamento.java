package com.nelioalves.cursomc.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nelioalves.cursomc.domain.enums.EstadoPagamento;
@Entity
@Inheritance(strategy=InheritanceType.JOINED) 
public abstract  class Pagamento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer id;
	private Integer estado;
	@JoinColumn(name="pedido_id",referencedColumnName ="id")
	@OneToOne
	@MapsId
	@JsonIgnore
	private Pedido pedido;
	/**
	 * @param id
	 * @param estado
	 * @param pedido
	 */
	public Pagamento(Integer id, EstadoPagamento estado, Pedido pedido) {
		super();
		this.id = id;
		this.estado = estado.getId();
		this.pedido = pedido;
	}
	/**
	 * 
	 */
	public Pagamento() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public EstadoPagamento getEstado() {
		return EstadoPagamento.toEnum(estado);
	}
	public void setEstado(EstadoPagamento estado) {
		this.estado = estado.getId();
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pagamento other = (Pagamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}