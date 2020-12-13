package ec.ups.edu.banco.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import ec.ups.edu.banco.modelo.Cuenta;

@Entity
public class Transaccion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTransaccion;
	@Column(nullable = false)
	private String fechaTransaccion;
	@Column(nullable = false)
	private String localidadTransaccion;
	@Column(nullable = false)
	private String tipoTransaccion;
	@Column(nullable = false)
	private Double valorTransaccion;
	@ManyToOne
    @JoinColumn(name = "numCuenta")
    private Cuenta cuenta;
	public int getIdTransaccion() {
		return idTransaccion;
	}
	public void setIdTransaccion(int idTransaccion) {
		this.idTransaccion = idTransaccion;
	}
	public String getFechaTransaccion() {
		return fechaTransaccion;
	}
	public void setFechaTransaccion(String fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}
	public String getLocalidadTransaccion() {
		return localidadTransaccion;
	}
	public void setLocalidadTransaccion(String localidadTransaccion) {
		this.localidadTransaccion = localidadTransaccion;
	}
	public String getTipoTransaccion() {
		return tipoTransaccion;
	}
	public void setTipoTransaccion(String tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}
	public Double getValorTransaccion() {
		return valorTransaccion;
	}
	public void setValorTransaccion(Double valorTransaccion) {
		this.valorTransaccion = valorTransaccion;
	}
	public Cuenta getCuenta() {
		return cuenta;
	}
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	@Override
	public String toString() {
		return "Transaccion [idTransaccion=" + idTransaccion + ", fechaTransaccion=" + fechaTransaccion
				+ ", localidadTransaccion=" + localidadTransaccion + ", tipoTransaccion=" + tipoTransaccion
				+ ", valorTransaccion=" + valorTransaccion + ", cuenta=" + cuenta + "]";
	}
	
	
}
