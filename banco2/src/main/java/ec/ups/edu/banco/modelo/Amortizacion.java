package ec.ups.edu.banco.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Amortizacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAmortizacion;
	@Column(nullable = false)
	private int periodoAmortizacion;
	@Column(nullable = false)
	private String fechaCuota;
	@Column(nullable = false)
	private double cuotaAmortizacion;
	@Column(nullable = false)
	private double capitalPendienteAmortizacion;
	@Column(nullable = false)
	private String estadoAmortizacion;
	@ManyToOne
    @JoinColumn(name = "idCredito")
	/*
    private Credito credito;
	public int getIdAmortizacion() {
		return idAmortizacion;
	}
	*/
	public void setIdAmortizacion(int idAmortizacion) {
		this.idAmortizacion = idAmortizacion;
	}
	public int getPeriodoAmortizacion() {
		return periodoAmortizacion;
	}
	public void setPeriodoAmortizacion(int periodoAmortizacion) {
		this.periodoAmortizacion = periodoAmortizacion;
	}
	public String getFechaCuota() {
		return fechaCuota;
	}
	public void setFechaCuota(String fechaCuota) {
		this.fechaCuota = fechaCuota;
	}
	public double getCuotaAmortizacion() {
		return cuotaAmortizacion;
	}
	public void setCuotaAmortizacion(double cuotaAmortizacion) {
		this.cuotaAmortizacion = cuotaAmortizacion;
	}
	public double getCapitalPendienteAmortizacion() {
		return capitalPendienteAmortizacion;
	}
	public void setCapitalPendienteAmortizacion(double capitalPendienteAmortizacion) {
		this.capitalPendienteAmortizacion = capitalPendienteAmortizacion;
	}

	public String getEstadoAmortizacion() {
		return estadoAmortizacion;
	}
	public void setEstadoAmortizacion(String estadoAmortizacion) {
		this.estadoAmortizacion = estadoAmortizacion;
	}
	/*
	public Credito getCredito() {
		return credito;
	}
	public void setCredito(Credito credito) {
		this.credito = credito;
	}
	@Override
	public String toString() {
		return "Amortizacion [idAmortizacion=" + idAmortizacion + ", periodoAmortizacion=" + periodoAmortizacion
				+ ", fechaCuota=" + fechaCuota + ", cuotaAmortizacion=" + cuotaAmortizacion
				+ ", capitalPendienteAmortizacion=" + capitalPendienteAmortizacion + ", estadoAmortizacion="
				+ estadoAmortizacion + ", credito=" + credito + "]";
	}
	*/
	

}
