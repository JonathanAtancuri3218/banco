package ups.edu.ec.transferproject.servicios;

import java.util.List;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;

import ec.ups.edu.transferproject.on.CuentaON;
import ec.ups.edu.transferproject.on.TransaccionON;
import ec.ups.edu.transferproject.on.UsuarioON;
import ups.edu.ec.modelos.Mensajes;
import ups.edu.ec.modelos.Usuario;

@WebService
public class GestionServicios {	
	@Inject
	private CuentaON con;	
	@Inject
	private UsuarioON usuarioOn;
	@Inject
	private TransaccionON transaccionOn;

	@WebMethod
	public Mensajes realizarRetiro(String cuenta, double valor) {
		Mensajes m=new Mensajes();
		List<Usuario> us =usuarioOn.consultarCuentaUsuario(cuenta);
		if(valor>us.get(0).getCuenta().getSaldoCuenta() || us.isEmpty()) {
			m.setId(99);
			m.setMensaje("No cuenta con el saldo suficiente o la cuenta es incorrecta");
		}else {
			con.retiro(valor, us.get(0));
			m.setId(1);
			m.setMensaje("El retiro se ha realizado con exito");
		}
		return m;
	}
	@WebMethod
	public Mensajes realizarDeposito(String cuenta, double valor ) {
		Mensajes m=new Mensajes();
		List<Usuario> us=usuarioOn.consultarCuentaUsuario(cuenta);
		if(us.isEmpty()) {
			m.setId(99);
			m.setMensaje("Los datos ingresados son incorrectos");
		}else {
		con.deposito(valor, us.get(0));
		m.setId(1);
		m.setMensaje("El deposito se ha realizado con exito");
		}
		return m;
	}
	/*@WebMethod
	public Mensajes realizarTransaccion(String cuentaO, String cuentaD, double valor) {
		Mensajes m=new Mensajes();
		List<Usuario> us=usuarioOn.consultarCuentaUsuario(cuentaO);
		List<Usuario> us1=usuarioOn.consultarCuentaUsuario(cuentaD);
		if(us.isEmpty() && us1.isEmpty() || valor>us.get(0).getCuenta().getSaldoCuenta()) {
			m.setId(99);
			m.setMensaje("Los datos ingresados son incorrectos");
		}else {
		con.transaccion(valor, us.get(0), us1.get(0));
		m.setId(1);
		m.setMensaje("El transaccion se ha realizado con exito");
		}
		return m;
	}*/
}
