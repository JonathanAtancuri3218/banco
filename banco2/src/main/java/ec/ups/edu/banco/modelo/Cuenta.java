package ec.ups.edu.banco.modelo;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cuenta {

	@Id
	private String numCuenta;
	@Column(nullable = false)
	private String tipo;
	@Column(nullable = false)
	private double saldoCuenta;
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "cuenta")
	private Set<Transaccion> listaTransacciones;

	public String getNumCuenta() {
		return numCuenta;
	}
	public void setNumCuenta(String numCuenta) {
		this.numCuenta = numCuenta;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public double getSaldoCuenta() {
		return saldoCuenta;
	}

	public void setSaldoCuenta(double saldoCuenta) {
		this.saldoCuenta = saldoCuenta;
	}

	public Set<Transaccion> getListaTransacciones() {
		return listaTransacciones;
	}
	public void setListaTransacciones(Set<Transaccion> listaTransacciones) {
		this.listaTransacciones = listaTransacciones;
	}
	@Override
	public String toString() {
		return "Cuenta [numCuenta=" + numCuenta + ", tipo=" + tipo + ", saldoCuenta=" + saldoCuenta
				+ ", listaTransacciones=" + listaTransacciones + "]";
	}

}
