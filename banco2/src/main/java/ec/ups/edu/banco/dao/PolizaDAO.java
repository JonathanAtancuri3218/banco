package ec.ups.edu.banco.dao;

import ec.ups.edu.banco.modelo.Poliza;

public class PolizaDAO {
	
	
	public Double parametrizar(Poliza p) {
		
		if (Integer.parseInt(p.getPlazo()) >= 30 && Integer.parseInt(p.getPlazo()) <= 59) {
			p.setInteres(5.50);
		}

		if (Integer.parseInt(p.getPlazo()) >= 60 && Integer.parseInt(p.getPlazo()) <= 89) {
			p.setInteres(5.75);
		}
		
		if (Integer.parseInt(p.getPlazo()) >= 90 && Integer.parseInt(p.getPlazo()) <= 179) {
			p.setInteres(6.25);
		}
		
		if (Integer.parseInt(p.getPlazo()) >= 180 && Integer.parseInt(p.getPlazo()) <= 269) {
			p.setInteres(7.00);
		}
		
		if (Integer.parseInt(p.getPlazo()) >= 270 && Integer.parseInt(p.getPlazo()) <= 359) {
			p.setInteres(7.50);
		}
		
		if (Integer.parseInt(p.getPlazo()) >= 360) {
			p.setInteres(8.50);
		}

		return p.getInteres();

	}

}
