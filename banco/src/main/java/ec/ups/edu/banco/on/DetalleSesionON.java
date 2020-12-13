package ec.ups.edu.banco.on;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.ups.edu.banco.dao.SesionDAO;
import ec.ups.edu.banco.modelo.DetalleSesion;

@Stateless
public class DetalleSesionON {
 
	@Inject
	private SesionDAO sesionDAO;
	/**
	 * Metodo listar tipos de sesion ya sean solo exitosos o erroneos
	 * @param tipo pasa como parametro la variable tipo que puede ser exitoso o erroneo
	 * @param cedula parametro cedula para especificar de quien se traera los detalles de sesion
	 * @return devuelve una lista de los detalles de sesion
	 */
	public List<DetalleSesion> listarDetalleSesionTipo(String tipo, String cedula){
		return sesionDAO.ListaTipoSesion(tipo, cedula);
	}
	/**
	 * Metodo listar detalles de sesion del usuario
	 * @param cedula pasamos como parametro la cedula para traer datos unicamente del usuario cuya cedula sea la especificada
	 * @return devuelve una lista de detalles de sesion
	 */
	public List<DetalleSesion> listarDetalleSesion(String cedula){
		return sesionDAO.ListaDetalleSesionUsuario(cedula);
	}
}
