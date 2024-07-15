package entidad;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Cliente{
	
	private int _IdCliente;
	private String _DNI;
	private String _CUIL;
	private String _Nombre;
	private String _Apellido;
	private String _Sexo;
	private Nacionalidad _Nacionalidad;
	private LocalDate _Fecha_de_Nacimiento;
	private String _Direccion;
	private Localidad _Localidad;
	private Provincia _Provincia;
	private String _Mail; 
	private int _Teléfono;
	private String _Usuario; 
	private String _Contraseña;
	private int _CantCuentas;
	private boolean _Estado;
	
	///Constructor
	public Cliente(){}
	
	public Cliente(int _IdCliente, String _DNI, String _CUIL, String _Nombre, String _Apellido, String _Sexo, Nacionalidad _Nacionalidad,
			LocalDate _Fecha_de_Nacimiento, String _Direccion, Localidad _Localidad, Provincia _Provincia, String _Mail,
			int _Teléfono, String _Usuario, String _Contraseña, int _Cant, boolean Estado) {
		super();
		this._IdCliente = _IdCliente;
		this._DNI = _DNI;
		this._CUIL = _CUIL;
		this._Nombre = _Nombre;
		this._Apellido = _Apellido;
		this._Sexo = _Sexo;
		this._Nacionalidad = _Nacionalidad;
		this._Fecha_de_Nacimiento = _Fecha_de_Nacimiento;
		this._Direccion = _Direccion;
		this._Localidad = _Localidad;
		this._Provincia = _Provincia;
		this._Mail = _Mail;
		this._Teléfono = _Teléfono;
		this._Usuario = _Usuario;
		this._Contraseña = _Contraseña;
		this._CantCuentas = _Cant;
		this._Estado = Estado;
	}
	
	///Getters and Setters
	public int get_IdCliente() {
		return _IdCliente;
	}
	
	public void set_IdCliente(int _IdCliente) {
		this._IdCliente = _IdCliente;
	}
	
	public String get_DNI() {
		return _DNI;
	}

	public void set_DNI(String _DNI) {
		this._DNI = _DNI;
	}

	public String get_CUIL() {
		return _CUIL;
	}

	public void set_CUIL(String _CUIL) {
		this._CUIL = _CUIL;
	}

	public String get_Nombre() {
		return _Nombre;
	}

	public void set_Nombre(String _Nombre) {
		this._Nombre = _Nombre;
	}

	public String get_Apellido() {
		return _Apellido;
	}

	public void set_Apellido(String _Apellido) {
		this._Apellido = _Apellido;
	}

	public String get_Sexo() {
		return _Sexo;
	}

	public void set_Sexo(String _Sexo) {
		this._Sexo = _Sexo;
	}

	public Nacionalidad get_Nacionalidad() {
		return _Nacionalidad;
	}

	public void set_Nacionalidad(Nacionalidad _Nacionalidad) {
		this._Nacionalidad = _Nacionalidad;
	}

	public LocalDate get_Fecha_de_Nacimiento() {
		return _Fecha_de_Nacimiento;
	}

	public void set_Fecha_de_Nacimiento(LocalDate date) {
		this._Fecha_de_Nacimiento = date;
	}

	public String get_Direccion() {
		return _Direccion;
	}

	public void set_Direccion(String _Direccion) {
		this._Direccion = _Direccion;
	}

	public Localidad get_Localidad() {
		return _Localidad;
	}

	public void set_Localidad(Localidad _Localidad) {
		this._Localidad = _Localidad;
	}

	public Provincia get_Provincia() {
		return _Provincia;
	}

	public void set_Provincia(Provincia _Provincia) {
		this._Provincia = _Provincia;
	}

	public String get_Mail() {
		return _Mail;
	}

	public void set_Mail(String _Mail) {
		this._Mail = _Mail;
	}

	public int get_Teléfono() {
		return _Teléfono;
	}

	public void set_Teléfono(int _Teléfono) {
		this._Teléfono = _Teléfono;
	}

	public String get_Usuario() {
		return _Usuario;
	}

	public void set_Usuario(String _Usuario) {
		this._Usuario = _Usuario;
	}

	public String get_Contraseña() {
		return _Contraseña;
	}

	public void set_Contraseña(String _Contraseña) {
		this._Contraseña = _Contraseña;
	}

	public int get_CantCuentas() {
		return _CantCuentas;
	}

	public void set_CantCuentas(int _CantCuentas) {
		this._CantCuentas = _CantCuentas;
	}

	public boolean get_Estado() {
		return _Estado;
	}

	public void set_Estado(boolean _Estado) {
		this._Estado = _Estado;
	}

	@Override
	public String toString() {
		return "Cliente [_IdCliente=" + _IdCliente + ", _DNI=" + _DNI + ", _CUIL=" + _CUIL + ", _Nombre=" + _Nombre + ", _Apellido=" + _Apellido
				+ ", _Sexo=" + _Sexo + ", _Nacionalidad=" + _Nacionalidad + ", _Fecha_de_Nacimiento="
				+ _Fecha_de_Nacimiento + ", _Direccion=" + _Direccion + ", _Localidad=" + _Localidad + ", _Provincia="
				+ _Provincia + ", _Mail=" + _Mail + ", _Teléfono=" + _Teléfono + ", _Usuario=" + _Usuario
				+ ", _Contraseña=" + _Contraseña + ", _CantCuentas=" + _CantCuentas + ", _Estado=" + _Estado + "]";
	}

	public String getFechaNacimientoFormatted() {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    return _Fecha_de_Nacimiento.format(formatter);
	}
}
