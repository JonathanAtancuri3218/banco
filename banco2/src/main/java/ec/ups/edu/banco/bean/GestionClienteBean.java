package ec.ups.edu.banco.bean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import ec.ups.edu.banco.modelo.Cuenta;
import ec.ups.edu.banco.modelo.DetalleSesion;
import ec.ups.edu.banco.modelo.Transaccion;
import ec.ups.edu.banco.modelo.Usuario;
import ec.ups.edu.banco.on.CuentaON;
import ec.ups.edu.banco.on.DetalleSesionON;
import ec.ups.edu.banco.on.TransaccionON;
import ec.ups.edu.banco.on.UsuarioON;



@ManagedBean
@ViewScoped
public class GestionClienteBean implements Serializable{

	private List<Transaccion> listarTransaccionTipo;
	private List<DetalleSesion> listarDetalleSesionTipo;
	private List<Transaccion> listarTransaccionCuenta;
	private List<Usuario> consultaCliente;
	private List<Cuenta> listarCuentas;
	
	private Transaccion transaccion;
	@Inject
	private TransaccionON transaccionON;
	@Inject
	private UsuarioON usuarioON;
	@Inject
	private DetalleSesionON detalleSesionON;
	private DetalleSesion detSesion;
	//@Inject
	//private CuentaON cuentaON;
	private Cuenta cuenta;
	

	private Date desde;
	private Date hasta;
	SimpleDateFormat simpleDateFormat;
	String pattern = "";
	String cuentac;

	@PostConstruct
	public void init() {
		pattern = "dd/MM/yy";;
		simpleDateFormat = new SimpleDateFormat(pattern);
		transaccion = new Transaccion();
		//cuenta=new Cuenta();
		detSesion=new DetalleSesion();
		transaccion.setTipoTransaccion("Todos");
		consultarCuentaCliente();
		String cuentac = GestionProyectoBean.UsuarioL.get(0).getCuenta().getNumCuenta();
	}

	public DetalleSesion getDetSesion() {
		return detSesion;
	}

	public void setDetSesion(DetalleSesion detSesion) {
		this.detSesion = detSesion;
	}

	public List<DetalleSesion> getListarDetalleSesionTipo() {
		return listarDetalleSesionTipo;
	}

	public void setListarDetalleSesionTipo(List<DetalleSesion> listarDetalleSesionTipo) {
		this.listarDetalleSesionTipo = listarDetalleSesionTipo;
	}

	public String getCuentac() {
		return cuentac;
	}

	public void setCuentac(String cuentac) {
		this.cuentac = cuentac;
	}

	public List<Transaccion> getListarTransaccionTipo() {
		return listarTransaccionTipo;
	}

	public void setListarTransaccionTipo(List<Transaccion> listarTransaccionTipo) {
		this.listarTransaccionTipo = listarTransaccionTipo;
	}

	public List<Transaccion> getListarTransaccionCuenta() {
		return listarTransaccionCuenta;
	}

	public void setListarTransaccionCuenta(List<Transaccion> listarTransaccionCuenta) {
		this.listarTransaccionCuenta = listarTransaccionCuenta;
	}

	public Transaccion getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(Transaccion transaccion) {
		this.transaccion = transaccion;
	}

	public TransaccionON getTransaccionON() {
		return transaccionON;
	}

	public void setTransaccionON(TransaccionON transaccionON) {
		this.transaccionON = transaccionON;
	}

	public List<Usuario> getConsultaCliente() {
		return consultaCliente;
	}

	public void setConsultaCliente(List<Usuario> consultaCliente) {
		this.consultaCliente = consultaCliente;
	}


	public Date getDesde() {
		return desde;
	}

	public void setDesde(Date desde) {
		this.desde = desde;
	}

	public Date getHasta() {
		return hasta;
	}

	public void setHasta(Date hasta) {
		this.hasta = hasta;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public String ListarTransaccionesCuenta() {

		String cuenta = GestionProyectoBean.UsuarioL.get(0).getCuenta().getNumCuenta();
		System.out.println(cuenta);
		listarTransaccionCuenta = transaccionON.listarCuentaTransacciones(cuenta);
		return "lista de transacciones";
	}

	public String ListarTipoMovimiento() {
		
		String a="",b="";
		String tipo = transaccion.getTipoTransaccion();
		if(tipo.equals("Fecha")) {
			a=simpleDateFormat.format(desde);
			b=simpleDateFormat.format(hasta);
			System.out.println(a);
			System.out.println(b);
		}
		System.out.println("+++++++++++++++ " + tipo);
		
		String cuenta = GestionProyectoBean.UsuarioL.get(0).getCuenta().getNumCuenta();
		if (tipo.equals("Transacciones")) {
		
			listarTransaccionTipo = transaccionON.ListarTipoMovimiento("Transaccion", cuenta);
		} else if (tipo.equals("Depositos")) {
			listarTransaccionTipo = transaccionON.ListarTipoMovimiento("Deposito", cuenta);
		} else if (tipo.equals("Retiros")) {
			listarTransaccionTipo = transaccionON.ListarTipoMovimiento("Retiro", cuenta);
		} else if (tipo.equals("Todos")) {
			listarTransaccionTipo = transaccionON.listarCuentaTransacciones(cuenta);
		} else if (tipo.equals("Fecha")) {
			listarTransaccionCuenta = transaccionON.listarTransaccionesPorfecha(a, b, cuenta);
			listarTransaccionTipo=listarTransaccionCuenta;
		} else {
			System.out.println("error de consulta");
		}
		return "lista de movimientos por tipo";
	}
public String ListarTipoSesion() {
	String tipo =detSesion.getDetalleSesion();
	System.out.println(tipo);
		String cedula = GestionProyectoBean.UsuarioL.get(0).getCedulaUsuario();
		System.out.println(cedula);
		if (tipo.equals("Todos")) {
			listarDetalleSesionTipo= detalleSesionON.listarDetalleSesion(cedula);
		} else if (tipo.equals("Exitosos")) {
			listarDetalleSesionTipo =detalleSesionON.listarDetalleSesionTipo("Exitosa", cedula);
		} else if (tipo.equals("Erroneos")) {
			listarDetalleSesionTipo = detalleSesionON.listarDetalleSesionTipo("Erronea", cedula);
		}  else {
			System.out.println("error de consulta");
		}
		return "lista de sesiones por tipo";
	}

/*
public String ListarTipoCuentas() {
	String tipo =cuenta.getTipo();
	System.out.println(tipo);
		//String cedula = GestionProyectoBean.UsuarioL.get(0).getCedulaUsuario();
		//System.out.println(cedula);
		String cuenta = GestionProyectoBean.CuentaL.get(0).getNumCuenta();
		System.out.println(cuenta);
		
		if (tipo.equals("Cuenta Ahorro")) {
			//listarCuentas= detalleSesionON.listarDetalleSesion(cedula);
			listarCuentas=cuentaON.ListarTipoCuenta("Cuenta Ahorro", cuenta);
		} else if (tipo.equals("Cuenta Corriente")) {
			//listarDetalleSesionTipo =detalleSesionON.listarDetalleSesionTipo("Exitosa", cedula);
			listarCuentas=cuentaON.ListarTipoCuenta("Cuenta Corriente", cuenta);
		} else {
			System.out.println("error de consulta");
		}
		return "lista de cuentas por tipo";
	}

*/
	public String cambiarDetalle() {
		return "DetalleCuenta";
	}

	public String cambiarMovimiento() {
		return "ConsultaMovimiento";
	}

	public String consultarCuentaCliente() {
		String cuenta = GestionProyectoBean.UsuarioL.get(0).getCuenta().getNumCuenta();
		consultaCliente = usuarioON.consultarCuentaUsuario(cuenta);
		return "consulta de la cuenta";
	}
}

