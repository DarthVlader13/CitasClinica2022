package org.iesalandalus.programacion.modelos;

import java.time.LocalDate;

import javax.naming.OperationNotSupportedException;

public class Citas {

	// 1.- DECLARAMOS LOS ATRIBUTOS

	private int capacidad, tamano;

	Cita[] coleccionCitas;

	public Citas(int capacidadColeccion) {
		if (capacidadColeccion <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		coleccionCitas = new Cita[capacidadColeccion];
		this.capacidad = capacidadColeccion;
		tamano = 0;
	}

	public Cita[] getCitas() {
		return coleccionCitas;
	}

	// 2.- CREAMOS LOS CONSTRUCTORES

	public Cita[] getCitas(LocalDate fecha) {
		if (fecha == null) {
			throw new NullPointerException("ERROR: No se pueden devolver las citas para un día nulo.");

		}
		Cita[] coleccionCitasFecha = new Cita[tamano];
		int j = 0;
		for (int i = 0; !tamanoSuperado(i); i++) {
			if (coleccionCitas[i].getFechaHora().toLocalDate().equals(fecha)) {
				coleccionCitasFecha[j++] = coleccionCitas[i];

			}
		}
		return coleccionCitasFecha;
	}

	// 3.- GENERAMOS METODO SET SIN PARAMETROS

	public int getTamano() {
		return tamano;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void insertar(Cita cita) throws OperationNotSupportedException {
		if (cita == null) {
			throw new NullPointerException("ERROR: No se puede insertar una cita nula.");
		}
		int indice = buscarIndice(cita);
		if (capacidadSuperada(indice)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más citas.");
		}
		if (tamanoSuperado(indice)) {
			coleccionCitas[indice] = new Cita(cita);
			tamano++;
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe una cita para esa fecha y hora.");
		}
	}

	// 4.- CREAMOS METODO BUSCARINDICE

	private int buscarIndice(Cita cita) {
		int indice = 0;
		boolean citaEncontrada = false;
		while (!tamanoSuperado(indice) && !citaEncontrada) {
			if (coleccionCitas[indice].equals(cita)) {
				citaEncontrada = true;
			} else {
				indice++;
			}
		}
		return indice;
	}

	// 5.- CREAMOS METODO PARA TAMANOSUPERADO Y CAPACIDAD SUPERADA

	private boolean tamanoSuperado(int indice) {
		boolean tamanoSuperado;
		tamanoSuperado = (indice >= tamano);
		return tamanoSuperado;
	}

	private boolean capacidadSuperada(int indice) {
		boolean capacidadSuperada;
		capacidadSuperada = (indice >= capacidad);
		return capacidadSuperada;
	}

	// 6.- CREAMOS METODO PARA BUSCAR

	public Cita buscar(Cita cita) {
		int indice = buscarIndice(cita);
		if (tamanoSuperado(indice)) {
			return null;
		} else {
			return new Cita(cita);
		}
	}

	// 7.- METODO PARA BORRAR UNA CITA

	public void borrar(Cita cita) throws OperationNotSupportedException {
		if (cita == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar una cita nula.");
		}
		int indice = buscarIndice(cita);
		if (!tamanoSuperado(indice)) {
			desplazarUnaPosicionHaciaIzquierda(indice);
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ninguna cita para esa fecha y hora.");
		}
	}

	// 8.- METODO PARA DESPLAZAR UNA POSICIONHACIAIZQUIERDA

	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		for (int i = indice; !tamanoSuperado(i); i++) {
			coleccionCitas[i] = coleccionCitas[i + 1];
		}
		tamano--;
	}

}