package ec.ups.edu.banco.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cuenta {

	@Id
	private int numCuental;
	private String tipoCuenta;
	private double saldoCuenta;
	
	
	public int getNumCuental() {
		return numCuental;
	}
	public void setNumCuental(int numCuental) {
		this.numCuental = numCuental;
	}
	public String getTipoCuenta() {
		return tipoCuenta;
	}
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	public double getSaldoCuenta() {
		return saldoCuenta;
	}
	public void setSaldoCuenta(double saldoCuenta) {
		this.saldoCuenta = saldoCuenta;
	}
	
}
