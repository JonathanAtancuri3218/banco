package ec.ups.edu.banco.on;


import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.ups.edu.banco.dao.PolizaAdminDAO;
import ec.ups.edu.banco.modelo.Cuenta;
import ec.ups.edu.banco.modelo.PolizaAdmin;

@Stateless
public class PolizaAdminON {

	@Inject
	private PolizaAdminDAO PolizaAdmindao;
	
	public List<PolizaAdmin> listaAdministrador(){
 		return PolizaAdmindao.listarPolizaAdm();
	}
	
	/**
	 * Metodo para crear cuenta, recibe como parametro el objeto cuenta
	 * @param cuenta
	 */
	public void crearPolizaParam(PolizaAdmin param) {
		PolizaAdmindao.crearPolizaAdmin(param);
	}
	
	/**
	 * Metodo para actualizar cuenta, recibe como parametro el objeto cuenta
	 * @param cuenta
	 */
	public void actualizarparam(PolizaAdmin param) {
		PolizaAdmindao.actualizarPolizaAdmin(param);
	}
	
	
	
}
