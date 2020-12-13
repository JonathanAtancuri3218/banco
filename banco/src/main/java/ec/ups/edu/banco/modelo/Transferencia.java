package ec.ups.edu.banco.modelo;

import java.io.Serializable;

public class Transferencia implements Serializable {
	
	private String cuentaOrigen;
	private String cuentaDestino;
	private double monto;
	public String getCuentaOrigen() {
		return cuentaOrigen;
	}
	public void setCuentaOrigen(String cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
	}
	public String getCuentaDestino() {
		return cuentaDestino;
	}
	public void setCuentaDestino(String cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	
	@Override
	public String toString() {
		return "Transferencia [cuentaOrigen=" + cuentaOrigen + ", cuentaDestino=" + cuentaDestino + ", monto=" + monto
				+ "]";
	}
	
	
}
