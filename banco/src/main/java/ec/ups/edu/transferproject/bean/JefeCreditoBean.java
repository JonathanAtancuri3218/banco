package ec.ups.edu.transferproject.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import ec.ups.edu.transferproject.on.CreditoON;
import ec.ups.edu.transferproject.on.CuentaON;
import ec.ups.edu.transferproject.on.UsuarioON;
import ups.edu.ec.modelos.Amortizacion;
import ups.edu.ec.modelos.Credito;
import ups.edu.ec.modelos.Cuenta;
import ups.edu.ec.modelos.Usuario;

@ManagedBean
@ViewScoped
public class JefeCreditoBean implements Serializable{

	
	@Inject
	private CreditoON con;
	@Inject
	private CuentaON cuenon;
	private Credito credito;
	@Inject
	private UsuarioON uon;
	private Usuario usuario;
	private Cuenta cuenta;
	private List<Credito> ListaCreditos;
	private static List<Usuario> detallesUsuario;
	private static List<Credito> creditoUsuario;
	private static String URLCedula;
	private static String URLPlanilla;
	private static String URLRol;
	
	@PostConstruct
	public void init() {
		credito=new Credito();
		usuario=new Usuario();
		listarCredito();
	}

	public Credito getCredito() {
		return credito;
	}

	public void setCredito(Credito credito) {
		this.credito = credito;
	}
	
	public List<Credito> getListaCreditos() {
		return ListaCreditos;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public void setListaCreditos(List<Credito> listaCreditos) {
		ListaCreditos = listaCreditos;
	}
	public Usuario getUsuario() {
		return usuario;
	}

	public CreditoON getCon() {
		return con;
	}

	public void setCon(CreditoON con) {
		this.con = con;
	}

	public UsuarioON getUon() {
		return uon;
	}

	public void setUon(UsuarioON uon) {
		this.uon = uon;
	}

	public String getURLCedula() {
		return URLCedula;
	}

	public void setURLCedula(String uRLCedula) {
		URLCedula = uRLCedula;
	}

	public String getURLPlanilla() {
		return URLPlanilla;
	}

	public void setURLPlanilla(String uRLPlanilla) {
		URLPlanilla = uRLPlanilla;
	}

	public String getURLRol() {
		return URLRol;
	}

	public void setURLRol(String uRLRol) {
		URLRol = uRLRol;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getDetallesUsuario() {
		return detallesUsuario;
	}

	public void setDetallesUsuario(List<Usuario> detallesUsuario) {
		this.detallesUsuario = detallesUsuario;
	}

	public List<Credito> getCreditoUsuario() {
		return creditoUsuario;
	}

	public void setCreditoUsuario(List<Credito> creditoUsuario) {
		this.creditoUsuario = creditoUsuario;
	}

	public void listarCredito() {
		ListaCreditos=con.listarCreditosPendientes();
	}

	public String VerDescripcion(String NumeroCuenta) {
		System.out.println("---------------------------------------"+NumeroCuenta);
		detallesUsuario=uon.consultarCuentaUsuario(NumeroCuenta);
		creditoUsuario=con.listarCreditoID(NumeroCuenta);
		URLCedula="/DownloadFileServelt?tipo=cedula&id="+NumeroCuenta;
		System.out.println(URLCedula);
		URLPlanilla="/DownloadFileServelt?tipo=planilla&id="+NumeroCuenta;
		URLRol="/DownloadFileServelt?tipo=rol&id="+NumeroCuenta;
		return "DescripcionCredito";
	}
	public String aceptarCredito() {
		List<Amortizacion> lista=new ArrayList<>();
		Amortizacion amortizacion=new Amortizacion();
		amortizacion.setCuotaAmortizacion(100);
		amortizacion.setFechaCuota("1/8/2020");
		lista.add(amortizacion);
		usuario.setEmailUsuario(detallesUsuario.get(0).getEmailUsuario());
		//ACTUALIZAR CREDITO
		List<Amortizacion> lm=con.calculoAmortizacion(creditoUsuario.get(0));
		cuenon.MailCreditoAceptado(usuario, lm);
		
		cuenta.setSaldoCuenta(creditoUsuario.get(0).getMonto()+creditoUsuario.get(0).getCuenta().getSaldoCuenta());
		cuenon.actualizarCuenta(cuenta);
		return "CreditoJefeCredito";
	}
	public String rechazarCredito() {
		usuario.setEmailUsuario(detallesUsuario.get(0).getEmailUsuario());
		cuenon.MailCreditoRechazado(usuario);
		credito.setEstadoCredito("Rechazado");
		cuenon.actualizarEstadoCredito(credito);
		return "CreditoJefeCredito";
	}
	
}
