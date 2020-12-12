package ec.ups.edu.banco.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetalleSesion {

	private String detalleSesion;
	private String DireccionIPSesion;
	private Date fechaSesion;
	@Id
	private int idSesion;
	
	
	public String getDetalleSesion() {
		return detalleSesion;
	}
	public void setDetalleSesion(String detalleSesion) {
		this.detalleSesion = detalleSesion;
	}
	public String getDireccionIPSesion() {
		return DireccionIPSesion;
	}
	public void setDireccionIPSesion(String direccionIPSesion) {
		DireccionIPSesion = direccionIPSesion;
	}
	public Date getFechaSesion() {
		return fechaSesion;
	}
	public void setFechaSesion(Date fechaSesion) {
		this.fechaSesion = fechaSesion;
	}
	public int getIdSesion() {
		return idSesion;
	}
	public void setIdSesion(int idSesion) {
		this.idSesion = idSesion;
	}
	
	
}
