package clases;

public class Paciente{
	//Atributos de la clase
	private int codPaciente, edad, celular, estado;
	private String nombres, apellidos, dni;
	
	
	//Constructor Inicializador
	public Paciente(int codPaciente, String nombres, String apellidos, String dni, int edad, int celular, int estado) {
		this.codPaciente=codPaciente;
		this.nombres=nombres;
		this.apellidos=apellidos;
		this.dni=dni;
		this.edad=edad;
		this.celular=celular;
		this.estado = estado;
	}

	public int getCodPaciente() {
		return codPaciente;
	}

	public void setCodPaciente(int codPaciente) {
		this.codPaciente = codPaciente;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getCelular() {
		return celular;
	}

	public void setCelular(int celular) {
		this.celular = celular;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
	
	
}