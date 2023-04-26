// Fig. 23.11: Bufer.java
// La interfaz Bufer especifica los m�todos que el Productor y el Consumidor llaman.
public interface Bufer
{
   /**Bufer que tiene los 3 metodos
    *
    * Establecer: sirve para la clase productor: en este caso producte pacientes
    *
    * Obtener: Sirve para
    *
    * **/

   // coloca valor int value en Bufer
   public void establecer( int valor ) throws InterruptedException; 

   // obtiene valor int de Bufer
   public int obtener() throws InterruptedException;

   //tercer metodo de interfaz
   public boolean estaVacio();
} // fin de la interfaz Bufer


/**************************************************************************
 * (C) Copyright 1992-2007 por Deitel & Associates, Inc. y                *
 * Pearson Education, Inc. Todos los derechos reservados.                 *
 *                                                                        *
 * RENUNCIA: Los autores y el editor de este libro han realizado su mejor *
 * esfuerzo para preparar este libro. Esto incluye el desarrollo, la      *
 * investigaci�n y prueba de las teor�as y programas para determinar su   *
 * efectividad. Los autores y el editor no hacen ninguna garant�a de      *
 * ning�n tipo, expresa o impl�cita, en relaci�n con estos programas o    *
 * con la documentaci�n contenida en estos libros. Los autores y el       *
 * editor no ser�n responsables en ning�n caso por los da�os consecuentes *
 * en conexi�n con, o que surjan de, el suministro, desempe�o o uso de    *
 * estos programas.                                                       *
 *************************************************************************/