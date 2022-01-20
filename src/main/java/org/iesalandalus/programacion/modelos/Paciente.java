package org.iesalandalus.programacion.modelos;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Paciente {
	
	// 1.- DECLARACION
	
	private static final String ERROR_DNI = ("([0-9]{8})([A-Za-z])");
	private static final String ERROR_TELEFONOS = ("[69][0-9]{8}");
	private String nombre;
	private String dni;
	private String telefono;
	
	// ESTO ES EL CONSTRUCTOR CON PARAMETROS
	public Paciente(String nombre, String dni, String telefono) {
		setNombre(nombre);
		setDni(dni);
		setTelefono(telefono);
	}
	
	// CONSTRUCTOR COPIA

	public Paciente(Paciente copiaPaciente) {
		if (copiaPaciente == null) {
			throw new NullPointerException("ERROR: No es posible copiar un paciente nulo.");
		} else {
			setNombre(copiaPaciente.nombre);
			setDni(copiaPaciente.dni);
			setTelefono(copiaPaciente.telefono);
			
			/* Si copiaPaciente es igual a null lanzaremos el NullPointerException y en caso de no serlo le daremos
			 * en los setters el valor del nombre, dni y telefono.
			 */
		}
	}
	
	// CREAMOS EL METODO GET Y SET PARA NOMBRE
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre == null || nombre.trim().isEmpty()) { //El isEmpty quiere decir que está vacio, para poder generar la excepcion.
			throw new NullPointerException("ERROR: El nombre de un paciente no puede ser nulo o vacío.");
		} else {
			this.nombre = formateaNombre(nombre);
		}

	}

	// 2.- CREAMOS EL METODO FORMATEANOMBRE
	
	private String formateaNombre(String nombreObjeto) {
		if (nombreObjeto == null || nombreObjeto.trim().length() == 0) {
			throw new NullPointerException("ERROR: El nombre de un paciente no puede ser nulo.");
		}

		nombreObjeto = nombreObjeto.replace("  ", " ");

		String[] words = nombreObjeto.split("\\s+");

		if (words.length == 0) {
			throw new NullPointerException("ERROR: El nombre de un paciente no puede ser nulo.");
		}

		StringBuilder nombreFormateado = new StringBuilder();
		for (int i = 0; i < words.length; i++) {
			String word = words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase();
			nombreFormateado.append(word).append(" ");
		}

		return nombreFormateado.toString().trim();
	}
	
	// CREAMOS EL METODO GET Y SET PARA DNI
	
	public String getDni() {
		return dni;
	}

	private void setDni(String dni) {
		if (dni == null || dni.trim().isEmpty()) {
			throw new NullPointerException("ERROR: El DNI de un paciente no puede ser nulo o vacío.");
		} else {
			Pattern patron;
			Matcher comparador;

			patron = Pattern.compile(ERROR_DNI);
			comparador = patron.matcher(dni);

			if (comparador.matches()) {
				if (comprobarLetraDni(dni) == true) {
					this.dni = dni;
				} else {
					throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
				}
			} else {
				throw new IllegalArgumentException("ERROR: El DNI no tiene un formato válido.");
			}
		}
	}
	
	// 3.- CREAMOS EL METODO COMPROBARLETRADNI
	private boolean comprobarLetraDni(String dni) {
		return dniValido(dni);
	}
	
	public static boolean dniValido(String dni) {

		if (dni == null || dni.trim().isEmpty())
			throw new NullPointerException("ERROR: El dni no puede ser nulo ni estar vacÃ­o.");

		int numerosDni;
		char letra;

		char[] asignarLetra = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q',
				'V', 'H', 'L', 'C', 'K', 'E' };

		Pattern p = Pattern.compile(ERROR_DNI);
		Matcher m = p.matcher(dni);

		boolean dniFormado = false;

		if (!m.matches()) {
			return false;
		}

		try {
			numerosDni = Integer.parseInt(m.group(1));
		} catch (NumberFormatException e) {
			numerosDni = 0;
		}

		letra = asignarLetra[numerosDni % 23];

		if (m.group(2).charAt(0) == letra) {
			dniFormado = true;

		} else {
			throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
		}
		return dniFormado;
	}	
		
	
	
	// CREAMOS EL METODO GET Y SET PARA TELEFONO

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		if (telefono == null || telefono.trim().isEmpty()) {
			throw new NullPointerException("ERROR: El teléfono de un paciente no puede ser nulo o vacío.");
		} else if (telefono.matches(ERROR_TELEFONOS)) {
			this.telefono = telefono;
		} else {
			throw new IllegalArgumentException("ERROR: El teléfono no tiene un formato válido.");
		}
	}

	// METODO HASHCODE
	
	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}
	
	//METODO EQUALS

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Paciente)) {
			return false;
		}
		Paciente other = (Paciente) obj;
		return Objects.equals(dni, other.dni);
	}

	// METODO TOSTRING Y GETINICIALES
	
	@Override
	public String toString() {
		return String.format("nombre=%s (%s), DNI=%s, teléfono=%s", getNombre(), getIniciales(), getDni(),
				getTelefono());
	}

	public String getIniciales() {
		String[] palabra = nombre.split(" ");
		String iniciales = "";
		for (int i = 0; i < palabra.length; i++) {
			iniciales += palabra[i].charAt(0);
		}
		return iniciales.toUpperCase();
	}
}