package entidad;

import java.time.LocalDate;

public class Movimiento {
	
	private int IdMoviento;
	private Cuenta NroCuenta;
	private int CBU_Origen;
	private int CBU_Destino;
	private LocalDate FechaDeMovimiento;
	private String Detalle;
	private double Importe;
	private TipoDeMovientos tipoDeMovientos;
	
	public Movimiento() {}
	
	public Movimiento(int idMoviento, Cuenta nroCuenta, int cBU_Origen, int cBU_Destino, LocalDate fechaDeMovimiento,
			String detalle, double importe, TipoDeMovientos tipoDeMovientos) {
		IdMoviento = idMoviento;
		NroCuenta = nroCuenta;
		CBU_Origen = cBU_Origen;
		CBU_Destino = cBU_Destino;
		FechaDeMovimiento = fechaDeMovimiento;
		Detalle = detalle;
		Importe = importe;
		this.tipoDeMovientos = tipoDeMovientos;
	}

	public int getIdMoviento() {
		return IdMoviento;
	}

	public void setIdMoviento(int idMoviento) {
		IdMoviento = idMoviento;
	}

	public Cuenta getNroCuenta() {
		return NroCuenta;
	}

	public void setNroCuenta(Cuenta nroCuenta) {
		NroCuenta = nroCuenta;
	}

	public int getCBU_Origen() {
		return CBU_Origen;
	}

	public void setCBU_Origen(int cBU_Origen) {
		CBU_Origen = cBU_Origen;
	}

	public int getCBU_Destino() {
		return CBU_Destino;
	}

	public void setCBU_Destino(int cBU_Destino) {
		CBU_Destino = cBU_Destino;
	}

	public LocalDate getFechaDeMovimiento() {
		return FechaDeMovimiento;
	}

	public void setFechaDeMovimiento(LocalDate fechaDeMovimiento) {
		FechaDeMovimiento = fechaDeMovimiento;
	}

	public String getDetalle() {
		return Detalle;
	}

	public void setDetalle(String detalle) {
		Detalle = detalle;
	}

	public double getImporte() {
		return Importe;
	}

	public void setImporte(double importe) {
		Importe = importe;
	}

	public TipoDeMovientos getTipoDeMovientos() {
		return tipoDeMovientos;
	}

	public void setTipoDeMovientos(TipoDeMovientos tipoDeMovientos) {
		this.tipoDeMovientos = tipoDeMovientos;
	};
	
	
}
