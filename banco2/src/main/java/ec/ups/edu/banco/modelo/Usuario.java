package ec.ups.edu.banco.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity
public class Usuario {

	@Id
	private String cedulaUsuario;
	@Column(nullable = false)
	private String nombreUsuario;
	@Column(nullable = false)
	private String apellidoUsuario;
	@Column(nullable = false)
	private String emailUsuario;
	@Column(nullable = false)
	private String telefonoUsuario;
	@Column(nullable = false)
	private String direccionUsuario;
	@Column(nullable = false)
	private String usuarioNombreU;
	@Column(nullable = false)
	private String contraseniaUsuario;
	@Column(nullable = true)
	private String tipoUsuario;
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "detalleS")
	private List<DetalleSesion> listaDS;
	@OneToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "numCuenta")
		private Cuenta cuenta;

	public String getCedulaUsuario() {
		return cedulaUsuario;
	}
	public void setCedulaUsuario(String cedulaUsuario) {
		this.cedulaUsuario = cedulaUsuario;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getApellidoUsuario() {
		return apellidoUsuario;
	}
	public void setApellidoUsuario(String apellidoUsuario) {
		this.apellidoUsuario = apellidoUsuario;
	}
	public String getEmailUsuario() {
		return emailUsuario;
	}
	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}
	public String getTelefonoUsuario() {
		return telefonoUsuario;
	}
	public void setTelefonoUsuario(String telefonoUsuario) {
		this.telefonoUsuario = telefonoUsuario;
	}
	public String getDireccionUsuario() {
		return direccionUsuario;
	}
	public void setDireccionUsuario(String direccionUsuario) {
		this.direccionUsuario = direccionUsuario;
	}
	public String getUsuarioNombreU() {
		return usuarioNombreU;
	}
	public void setUsuarioNombreU(String usuarioNombreU) {
		this.usuarioNombreU = usuarioNombreU;
	}
	public String getContraseniaUsuario() {
		return contraseniaUsuario;
	}
	public void setContraseniaUsuario(String contraseniaUsuario) {
		this.contraseniaUsuario = contraseniaUsuario;
	}
	public List<DetalleSesion> getListaDS() {
		return listaDS;
	}
	public void setListaDS(List<DetalleSesion> listaDS) {
		this.listaDS = listaDS;
	}
	public Cuenta getCuenta() {
		return cuenta;
	}
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	@Override
	public String toString() {
		return "Usuario [cedulaUsuario=" + cedulaUsuario + ", nombreUsuario=" + nombreUsuario + ", apellidoUsuario="
				+ apellidoUsuario + ", emailUsuario=" + emailUsuario + ", telefonoUsuario=" + telefonoUsuario
				+ ", direccionUsuario=" + direccionUsuario + ", usuarioNombreU=" + usuarioNombreU
				+ ", contraseniaUsuario=" + contraseniaUsuario + ", tipoUsuario=" + tipoUsuario + ", listaDS=" + listaDS
				+ ", cuenta=" + cuenta + "]";
	}

	
}
