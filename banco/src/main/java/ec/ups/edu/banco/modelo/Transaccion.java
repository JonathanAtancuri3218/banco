package ec.ups.edu.banco.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Transaccion {

	private String tipoTransaccion;
	private Double valorTrasaccion;
	private Date fechaTransaccion;
	@Id
	private int idTransaccion;
	
	
	public String getTipoTransaccion() {
		return tipoTransaccion;
	}
	public void setTipoTransaccion(String tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}
	public Double getValorTrasaccion() {
		return valorTrasaccion;
	}
	public void setValorTrasaccion(Double valorTrasaccion) {
		this.valorTrasaccion = valorTrasaccion;
	}
	public Date getFechaTransaccion() {
		return fechaTransaccion;
	}
	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}
	public int getIdTransaccion() {
		return idTransaccion;
	}
	public void setIdTransaccion(int idTransaccion) {
		this.idTransaccion = idTransaccion;
	}
	
	
}
