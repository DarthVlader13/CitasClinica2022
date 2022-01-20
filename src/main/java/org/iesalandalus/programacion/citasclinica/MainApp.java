package org.iesalandalus.programacion.citasclinica;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.modelos.Cita;
import org.iesalandalus.programacion.modelos.Citas;
import org.iesalandalus.programacion.modelos.Paciente;
import org.iesalandalus.programacion.vista.Consola;
import org.iesalandalus.programacion.vista.Opciones;

// LA CLASE MAINAPP YA VENIA GENERADA EN EL ESQUELETO DEL PROYECTO

public class MainApp {

	// 1.- DECLARAMOS LOS ATRIBUTOS
	
	public static final int NUM_MAX_CITAS = 30;
	private static Citas listaCitas = new Citas(NUM_MAX_CITAS);

	/* PREVIAMENTE MOSTRAREMOS CON SYSTEM.OUT.PRINTLN UNA BIENVENIDA
	 * AL PROGRAMA PARA DARLE SENTIDO Y ARMONIA A LA APLICACION
	 * Y EJECUTAREMOS OPCION 
	 */
	
	
	public static void main(String[] args) throws OperationNotSupportedException {
		Opciones opcion;
		do {
			System.out.println("Programa para gestionar las citas de la Clínica.");
			System.out.println("");
			Consola.mostrarMenu();
			opcion=Consola.elegirOpcion();
			ejecutarOpcion(opcion);
		}while(opcion!=Opciones.SALIR);
	}
	
	// 2.- CREAMOS EL METODO EJECUTAROPCION

	private static void ejecutarOpcion(Opciones opcion) throws OperationNotSupportedException {
		switch (opcion) {
		case SALIR:
			System.out.println("");
			System.out.print("¡Sesión terminada!");
			break;
		case INSERTAR_CITA:
			insertarCita();
			break;
		case BUSCAR_CITA:
			buscarCita();
			break;
		case BORRAR_CITA:
			borrarCita();
			break;
		case MOSTRAR_CITAS_DIA:
			mostrarCitasDia();
			break;
		case MOSTRAR_CITAS:
			mostrarCitas();
			break;
		}
	}

	// 3.- CREAMOS EL METODO INSERTARCITA
	
	private static void insertarCita() throws OperationNotSupportedException {
		try {
			Cita cita = Consola.leerCita();
			listaCitas.insertar(cita);
			System.out.println("");
			System.out.println("¡Cita asignada correctamente!");
			System.out.println("");
			Consola.mostrarMenu();
			ejecutarOpcion(Consola.elegirOpcion());
		} catch (IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println("");
			System.out.println(e.getMessage());
			System.out.println("");
			System.out.println("Vuelva a introducir los datos :(");
			System.out.println("");
			insertarCita();
		}

	}
	
	// 4.- CREAMOS EL METODO BUSCARCITA

	private static void buscarCita() throws OperationNotSupportedException {

		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		LocalDateTime fecha = Consola.leerFechaHora();

		Paciente paciente = new Paciente("pacienteX", "77160362P", "612345678");
		Cita cita = new Cita(paciente, fecha);

		Cita citaComprobada;
		citaComprobada = listaCitas.buscar(cita);

		if (citaComprobada == null) {
			System.out.println("No existen citas para la fecha: " + fecha.format(formato));
		} else {

			Cita[] citas = listaCitas.getCitas(fecha.toLocalDate());
			for (Cita citaPasada : citas) {
				if (cita.equals(citaPasada)) {
					System.out.println("La cita es " + citaPasada);
				}
			}

		}

		Consola.mostrarMenu();
		ejecutarOpcion(Consola.elegirOpcion());
	}

	// 5.- CREAMOS EL METODO BORRARCITA
	
	private static void borrarCita() throws OperationNotSupportedException {

		LocalDateTime fechaHora = Consola.leerFechaHora();
		Paciente paciente = new Paciente("paciente paciente", "77160362P", "612345678");
		Cita cita = new Cita(paciente, fechaHora);

		try {
			listaCitas.borrar(cita);
			System.out.println("Cita borrada correctamente.");
			Consola.mostrarMenu();
			ejecutarOpcion(Consola.elegirOpcion());
		} catch (Exception ex) {
			System.out.println("Se ha producido el siguiente error: " + ex.getMessage());
			ejecutarOpcion(Consola.elegirOpcion());
		}
	}
	
	// 6.- CREAMOS EL METODO MOSTRARCITASDIA

	private static void mostrarCitasDia() throws OperationNotSupportedException {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate fecha = Consola.leerFecha();
		System.out.println("");
		System.out.println("Lista de citas para fecha: " + fecha.format(formato));
		System.out.println("");
		Cita[] citasFecha = listaCitas.getCitas(fecha);
		boolean citasDisponibles = false;
		for (int i = 0; i <= citasFecha.length - 1; i++) {
			if (citasFecha[i] != null) {
				citasDisponibles = true;
			}
		}

		if (citasFecha.length == 0 || !citasDisponibles) {
			System.out.println("No hay citas para esta fecha :(");
			System.out.println("");
			Consola.mostrarMenu();
			ejecutarOpcion(Consola.elegirOpcion());
		} else {
			for (Cita cita : citasFecha) {
				if (cita != null) {
					System.out.println(cita);
				}
			}
			System.out.println("");
			Consola.mostrarMenu();
			ejecutarOpcion(Consola.elegirOpcion());
		}
	}

	// 7.- CREAMOS EL METODO MOSTRARCITAS
	
	private static void mostrarCitas() throws OperationNotSupportedException {
		System.out.println("");
		System.out.println("Lista de todas las citas");
		System.out.println("_______________________");
		if (listaCitas.getTamano() == 0) {
			System.out.println("");
			System.out.println("No hay citas para mostrar :(");
			System.out.println("");
			Consola.mostrarMenu();
			ejecutarOpcion(Consola.elegirOpcion());
		} else {
			Cita[] citas = listaCitas.getCitas();
			for (Cita cita : citas) {
				if (cita != null) {
					System.out.println(cita);
				}
			}
			System.out.println("");
			Consola.mostrarMenu();
			ejecutarOpcion(Consola.elegirOpcion());
		}
	}
	
}
