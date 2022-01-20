package org.iesalandalus.programacion.modelos;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Cita {
	
	// 1.- DECLARAMOS LOS ATRIBUTOS

	public static final String FORMATO_FECHA_HORA = ("dd/MM/yyyy HH:mm");
	private LocalDateTime fechaHora;
	public Paciente paciente;
	
	// 2.- GENERAMOS EL CONSTRUCTOR CON PARAMETROS
	
	public Cita(Paciente paciente, LocalDateTime fechaHora) {
		super();
		setFechaHora(fechaHora);
		setPaciente(paciente);
	}
	
	// 3.- GENERAMOS EL CONSTRUCTOR COPIA
	
	public Cita(Cita copiaCita) {
		if (copiaCita == null) {
			throw new NullPointerException("ERROR: No es posible copiar una cita nula.");
		} else {
			setFechaHora(copiaCita.getFechaHora());
			setPaciente(copiaCita.getPaciente());
		}
	}
	
	// 4.- GENERAMOS LOS METODOS GET Y SET

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		if (fechaHora == null) {
			throw new NullPointerException("ERROR: La fecha y hora de una cita no puede ser nula.");
		}
		this.fechaHora = fechaHora;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	private void setPaciente(Paciente paciente) {
		if (paciente == null) {
			throw new NullPointerException("ERROR: El paciente de una cita no puede ser nulo.");
		}
		this.paciente = new Paciente(paciente);
	}
	
	// 5.- GENERAMOS LOS METODOS EQUALS Y HASHCODE

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fechaHora == null) ? 0 : fechaHora.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cita other = (Cita) obj;
		if (fechaHora == null) {
			if (other.fechaHora != null)
				return false;
		} else if (!fechaHora.equals(other.fechaHora))
			return false;
		return true;
	}
	
	//GENERAMOS EL METODO TOSTRING
	
	@Override
	public String toString() {
		return paciente.toString() + ", fechaHora=" + fechaHora.format(DateTimeFormatter.ofPattern(FORMATO_FECHA_HORA))
				+ "";
	}
}