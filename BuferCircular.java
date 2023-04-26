// Fig. 23.20: BuferCircular.java
// Sincronización del acceso a un búfer delimitado compartido, con tres elementos.
public class BuferCircular implements Bufer
{
   private final int[] bufer = { -1, -1, -1 ,-1,-1}; // búfer compartido (5 clinicas, 1 doctor)

   private int celdasOcupadas = 0; // número de búferes utilizados
   private int indiceEscritura = 0; // índice del siguiente elemento a escribir
   private int indiceLectura = 0; // índice del siguiente elemento a leer
   
   // coloca un valor en el búfer
   public synchronized void establecer( int valor ) throws InterruptedException
   {
      // imprime información del subproceso y del búfer, después espera;
      // mientras no haya ubicaciones vacías, coloca el subproceso en estado de espera
      while ( celdasOcupadas == bufer.length ) 
      {
         System.out.printf( "La clinica está llena. Paciente espera.\n" );
         wait(); // espera hasta que haya una celda libre en el búfer
      } // fin de while

      bufer[ indiceEscritura ] = valor; // establece nuevo valor del búfer

      // actualiza índice de escritura circular
      indiceEscritura = ( indiceEscritura + 1 ) % bufer.length;

      ++celdasOcupadas; // una celda más del búfer está llena
      mostrarEstado( "Paciente solicita servicio n. " + valor );
      notifyAll(); // notifica a los subprocesos en espera para que lean del búfer
   } // fin del método establecer
    
   // devuelve un valor del búfer
   public synchronized int obtener() throws InterruptedException
   {
      // espera hasta que el búfer tenga datos, después lee el valor;
      // mientras no haya datos para leer, coloca el subproceso en estado de espera
      while ( celdasOcupadas == 0 ) 
      {
         System.out.printf( "Clinica vacia. Los Doctores esperan.\n" );
         wait(); // espera hasta que se llene una celda del búfer
      } // fin de while

      int valorLeido = bufer[ indiceLectura ]; // lee un valor del búfer

      // actualiza índice de lectura circular
      indiceLectura = ( indiceLectura + 1 ) % bufer.length;

      --celdasOcupadas; // hay una celda ocupada menos en el búfer
      mostrarEstado( "Doctor atiende servicio tipo " + valorLeido );
      notifyAll(); // notifica a los subprocesos en espera que pueden escribir en el búfer

      return valorLeido;
   } // fin del método obtener
    
   // muestra la operación actual y estado del búfer
   public void mostrarEstado( String mensajeBienvenida )
   {
      // imprime operacion y número de celdas ocupadas del búfer
      System.out.printf( "%s%s%d)\n", mensajeBienvenida,
         " (consultorios utilizados: ", celdasOcupadas );



      System.out.println( "\n" );
   } // fin del método mostrarEstado
   public boolean estaVacio(){

      return celdasOcupadas==0;
   }

} // fin de la clase BuferCircular


/**************************************************************************
 * (C) Copyright 1992-2007 por Deitel & Associates, Inc. y                *
 * Pearson Education, Inc. Todos los derechos reservados.                 *
 *                                                                        *
 * RENUNCIA: Los autores y el editor de este libro han realizado su mejor *
 * esfuerzo para preparar este libro. Esto incluye el desarrollo, la      *
 * investigación y prueba de las teorías y programas para determinar su   *
 * efectividad. Los autores y el editor no hacen ninguna garantía de      *
 * ningún tipo, expresa o implícita, en relación con estos programas o    *
 * con la documentación contenida en estos libros. Los autores y el       *
 * editor no serán responsables en ningún caso por los daños consecuentes *
 * en conexión con, o que surjan de, el suministro, desempeño o uso de    *
 * estos programas.                                                       *
 *************************************************************************/