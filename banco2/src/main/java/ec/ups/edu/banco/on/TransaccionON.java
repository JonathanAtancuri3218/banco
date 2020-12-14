package ec.ups.edu.banco.on;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.ups.edu.banco.dao.TransaccionDAO;
import ec.ups.edu.banco.modelo.Cuenta;
import ec.ups.edu.banco.modelo.Transaccion;

@Stateless
public class TransaccionON {

	@Inject
	private TransaccionDAO trandao;
	
	
	
	/**
	 * Devuele una lista unicamente de los transacciones con el tipo indicado
	 * @param tipoMov especifica el tipo de movimiento
	 * @param cuenta referencia el numero de cuenta de la persona a consultar las transacciones
	 * @return
	 */
	public List<Transaccion> ListarTipoMovimiento(String tipoMov, String cuenta) {
	return trandao.ListaTipoTransaccion(tipoMov, cuenta);
	}
	/**
	 * Devuelve una lista de las transacciones de una cuenta
	 * @param cuenta se pasa como parametro el numero de cuenta
	 * @return 
	 */
	public List<Transaccion> listarCuentaTransacciones(String cuenta){
		return trandao.ListaCuentaTransaccion(cuenta);
	}
	/**
	 * Devuelve una lista de las transacciones entre dos fechas haciendo referencia al numero de cuenta especificado
	 * @param desde fecha desde la cual se desea consultar
	 * @param hasta fecha hasta la cual se desea consultar
	 * @param cuenta numero de cuenta de la cual se consultara las transacciones
	 * @return
	 */
	public List<Transaccion> listarTransaccionesPorfecha(String desde, String hasta, String cuenta){
		List<Transaccion> list=trandao.ListaCuentaTransaccion(cuenta);
		List<Transaccion> listaFechas=new ArrayList<Transaccion>();
		DateFormat f = DateFormat.getDateInstance(DateFormat.SHORT);
		String desdef;
		String hastaf;
		Date date1 = null;
		Date date2 = null;
		Calendar  cal1 = new GregorianCalendar();
		Calendar  cal2 = new GregorianCalendar();
	     try {
	    	  desdef = desde; // Formato “dd/mm/aa�?
			 date1 = f.parse(desde);
			 cal1.setTime(date1);
			 hastaf = hasta; // Formato “dd/mm/aa�?
		     date2 = f.parse(hasta);
		     cal2.setTime(date2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	     
       for (Transaccion transaccion : list) {
		String[] str= transaccion.getFechaTransaccion().split(" ");
		Date date3 = null;
		try {
			
			date3 = f.parse(str[0]);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
        Calendar  cal3 = new GregorianCalendar();
        cal3.setTime(date3);
        
        if (cal3.after(cal1) && cal3.before(cal2) || cal3.equals(cal1) && cal3.before(cal2) || cal3.after(cal1)&& cal3.equals(cal2)){
            listaFechas.add(transaccion);
        }else {
        	System.out.println("NO hay fechas");
        }
	}
		return listaFechas;
	}
	/**
	 * 
	 * @param cuenta cuenta en la que se va a guardar la transcaccion
	 * @param monto el monto de la transsacion a realizar
	 * @param tipo el tipo de transaccion deposito y retiro
	 * 
	 */
	
	public void guardarTransaccion(Cuenta cuenta,double monto,String tipo) {
		Transaccion tr = new Transaccion();
		tr.setCuenta(cuenta);
		tr.setFechaTransaccion(generarFecha());
		tr.setLocalidadTransaccion("Cuenca");
		tr.setTipoTransaccion(tipo);
		tr.setValorTransaccion(monto);
		trandao.crearTransaccion(tr);
	}
	/**
	 * 
	 * @return string con la fecha actual
	 */
	public String generarFecha() {
		String pattern = "dd/MM/yyyy HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		return date;
	}
	
	
}
