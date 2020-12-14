package ec.ups.edu.banco.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import ec.ups.edu.banco.modelo.Cuenta;
import ec.ups.edu.banco.modelo.Usuario;
import ec.ups.edu.banco.on.CuentaON;
import ec.ups.edu.banco.on.TransaccionON;
import ec.ups.edu.banco.on.UsuarioON;


@ManagedBean
@ViewScoped
public class CajeroBean implements Serializable{
	@Inject
	private TransaccionON transaccionON;
	@Inject
	private UsuarioON usuarioON;
	@Inject
	private CuentaON cuentaON;
	
	private double monto;
	private String mensajeErrorRetiro;
	
	private List<Usuario> listarClientes;

	private Usuario usu;
	private Cuenta cuenta;
	
	@PostConstruct
	public void init() {
		ListarClientes();
		cuenta= new Cuenta();
		usu=new Usuario();
	}
	
	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}




	public String getMensajeErrorRetiro() {
		return mensajeErrorRetiro;
	}




	public void setMensajeErrorRetiro(String mensajeErrorRetiro) {
		this.mensajeErrorRetiro = mensajeErrorRetiro;
	}





	public TransaccionON getTransaccionON() {
		return transaccionON;
	}
	public void setTransaccionON(TransaccionON transaccionON) {
		this.transaccionON = transaccionON;
	}
	public UsuarioON getUsuarioON() {
		return usuarioON;
	}
	public void setUsuarioON(UsuarioON usuarioON) {
		this.usuarioON = usuarioON;
	}
	public CuentaON getCuentaON() {
		return cuentaON;
	}
	public void setCuentaON(CuentaON cuentaON) {
		this.cuentaON = cuentaON;
	}
	
	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public Usuario getUsu() {
		return usu;
	}

	public void setUsu(Usuario usu) {
		this.usu = usu;
	}



	public List<Usuario> getListarClientes() {
		return listarClientes;
	}


	public void setListarClientes(List<Usuario> listarClientes) {
		this.listarClientes = listarClientes;
	}




	public String cargarClientes() {
		System.out.println("----- " + cuenta.getNumCuenta());

		for (Usuario u : listarClientes) {
			System.out.println(u.getCedulaUsuario());

			if (u.getCuenta().getNumCuenta().equals(cuenta.getNumCuenta())) {
				usu = u;
			} else {
				System.out.println("no existe");
			}

		}
		return null;
	}


	
	public String retiro() {
		if(mensajeErrorRetiro.equals("El monto es mayor a la cantidad actual")) {
			return null;
		}else {
		System.out.println(usu.getCuenta().getSaldoCuenta()-monto);
		usu.getCuenta().setSaldoCuenta(usu.getCuenta().getSaldoCuenta()-monto);
		cuenta=usu.getCuenta();
		cuentaON.actualizarCuenta(cuenta);
		transaccionON.guardarTransaccion(cuenta, monto, "Retiro");
		}
		return "InicioCajero";
		}
	
public String deposito() {
		
		System.out.println(usu.getCuenta().getSaldoCuenta()+monto);
		usu.getCuenta().setSaldoCuenta(usu.getCuenta().getSaldoCuenta()+monto);
		cuenta=usu.getCuenta();
		cuentaON.actualizarCuenta(cuenta);
		transaccionON.guardarTransaccion(cuenta, monto, "Deposito");
		return "InicioCajero";
	}
public void validarMonto() {
	
	if(monto<usu.getCuenta().getSaldoCuenta()) {
		mensajeErrorRetiro="";
	}else {
		mensajeErrorRetiro="El monto es mayor a la cantidad actual";
	}
	}
public String ListarClientes() {
	listarClientes=usuarioON.listarClientes();
	return "lista de clientes";
}
}
