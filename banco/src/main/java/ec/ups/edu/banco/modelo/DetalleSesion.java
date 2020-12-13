package ec.ups.edu.banco.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import ec.ups.edu.banco.modelo.Usuario;

@Entity
public class DetalleSesion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSesion;
	@Column(nullable = false)
	private String fechaSesion;
	@Column(nullable = false)
	private String detalleSesion;
	@Column(nullable = false)
	private String direccionIPSesion;
	@ManyToOne
    @JoinColumn(name = "cedulaUsuario")
    private Usuario detalleS;
	public int getIdSesion() {
		return idSesion;
	}
	public void setIdSesion(int idSesion) {
		this.idSesion = idSesion;
	}
	public String getFechaSesion() {
		return fechaSesion;
	}
	public void setFechaSesion(String fechaSesion) {
		this.fechaSesion = fechaSesion;
	}
	public String getDetalleSesion() {
		return detalleSesion;
	}
	public void setDetalleSesion(String detalleSesion) {
		this.detalleSesion = detalleSesion;
	}
	public String getDireccionIPSesion() {
		return direccionIPSesion;
	}
	public void setDireccionIPSesion(String direccionIPSesion) {
		this.direccionIPSesion = direccionIPSesion;
	}
	public Usuario getDetalleS() {
		return detalleS;
	}
	public void setDetalleS(Usuario detalleS) {
		this.detalleS = detalleS;
	}
	@Override
	public String toString() {
		return "DetalleSesion [idSesion=" + idSesion + ", fechaSesion=" + fechaSesion + ", detalleSesion="
				+ detalleSesion + ", direccionIPSesion=" + direccionIPSesion + ", detalleS=" + detalleS + "]";
	}

	
	
}
