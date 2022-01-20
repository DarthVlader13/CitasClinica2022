package org.iesalandalus.programacion.vista;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.modelos.Cita;
import org.iesalandalus.programacion.modelos.Paciente;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
	
	// 1.- CREAMOS EL CONSTRUCTOR CONSOLA

	private Consola() {

	}
	
	// 2.- CREAMOS EL METODO MOSTRARMENU

	public static void mostrarMenu() {
		System.out.println("----------------------------------------------------");
		System.out.println("                   GESTION DE CITAS                 ");
		System.out.println("----------------------------------------------------");
		System.out.println("1 - Insertar una cita");
		System.out.println("2 - Buscar una cita");
		System.out.println("3 - Borrar cita");
		System.out.println("4 - Mostrar todas las citas");
		System.out.println("5 - Mostrar todas las citas de una fecha ");
		System.out.println("----------------------------------------------------");
		System.out.println("6 - Salida del programa");
		System.out.println("----------------------------------------------------");
	}
	
	// 3.- METODO ELEGIROPCION

	public static Opciones elegirOpcion() {
		int opcion;
		do {
			System.out.println("¿Qué opción desea ejecutar? (1-6)");
			opcion = Entrada.entero();
		} while (opcion < 1 || opcion > 6);

		switch (opcion) {
		case 1:
			return Opciones.INSERTAR_CITA;
		case 2:
			return Opciones.BUSCAR_CITA;
		case 3:
			return Opciones.BORRAR_CITA;
		case 4:
			return Opciones.MOSTRAR_CITAS;
		case 5:
			return Opciones.MOSTRAR_CITAS_DIA;
		default:
			return Opciones.SALIR;
		}
	}

	// 4.- CREAMOS METODO LEERCITA
	
	public static Cita leerCita() throws OperationNotSupportedException {
		Cita cita = new Cita(leerPaciente(), leerFechaHora());
		return cita;
	}

	// 5.- CREAMOS METODO LEERPACIENTE
	
	public static Paciente leerPaciente() throws OperationNotSupportedException {
		Paciente paciente;
		System.out.println("");
		System.out.println("Introduzca el nombre:");
		String nombre = Entrada.cadena();
		System.out.println("Introduzca el teléfono:");
		String telefono = Entrada.cadena();
		System.out.println("Introduzca el DNI:");
		String dni = Entrada.cadena();
		paciente = new Paciente(nombre, dni, telefono);
		return paciente;
	}

	// 6.- CREAMOS METODO LEERFECHAHORA
	
	public static LocalDateTime leerFechaHora() {
		String formatoCadena = "dd/MM/yyyy HH:mm";
		DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern(formatoCadena);
		LocalDateTime fechaHora = null;
		boolean fechaValida = false;
		do {
			try {
				System.out.println("Introduzca una fecha y hora con el siguiente formato: dd/MM/aaaa HH:mm:");
				fechaHora = LocalDateTime.parse(Entrada.cadena(), formatoFecha);
				fechaValida = true;
			} catch (DateTimeParseException e) {
				fechaValida = false;
			}
		} while (!fechaValida);
		return fechaHora;
	}

	// 7.- CREAMOS METODO LEERFECHA
	
	public static LocalDate leerFecha() {
		String formatoCadena = "dd/MM/yyyy";
		DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern(formatoCadena);
		LocalDate fecha = null;
		boolean fechaValida = false;
		do {
			try {
				System.out.println("Introduzca una fecha con el siguiente formato: dd/MM/aaaa:");
				fecha = LocalDate.parse(Entrada.cadena(), formatoFecha);
				fechaValida = true;
			} catch (DateTimeParseException e) {
				fechaValida = false;
			}
		} while (!fechaValida);
		return fecha;
	}

}
