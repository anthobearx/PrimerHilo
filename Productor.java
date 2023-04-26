// Fig. 23.12: Productor.java
// Productor con un método run que inserta los valores del 1 al 10 en el búfer.
import java.util.Random;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Productor implements Runnable
{
   private final static Random generador = new Random();
   private final Bufer ubicacionCompartida; // referencia al objeto compartido

   //constantes de tiempo
   private final int UN_MINUTO =1000*60*1;//tiempo maximo en llegar un paciente nuevo (1 hora, en el programa 1 minuto)
   private final int OCHO_MINUTOS =1000*60*8;//horario de clinica(Simula horario de 10 a 18, es decir 8 horas, 8 minutos en el programa)

   // constructor
   public Productor( Bufer compartido )
   {
       ubicacionCompartida = compartido;
   } // fin del constructor de Productor

   // almacena valores del 1 al 10 en ubicacionCompartida
   public void run()                             
   {
      int particularConsulta = 0;
      int particularSeguimiento = 0;
      int trabajadorBanco = 0;

      //ciclo de 8 minutos
      Calendar tiempo = new GregorianCalendar();
      long horaInicial = tiempo.getTimeInMillis();
      long horaActual = tiempo.getTimeInMillis();

      while ((horaActual-horaInicial)<=OCHO_MINUTOS){//Ciclo que simula el horario de la clinica
         try // permanece inactivo de 0 a 1 minuto
         {
            Thread.sleep( generador.nextInt( UN_MINUTO ) ); // periodo de llegada de pacientes (0 a 1 hora)
            int tipoServicio = generador.nextInt(3)+1;//tipos de servicios que hay (1-3)
            ubicacionCompartida.establecer( tipoServicio ); // establece el tipo de servicio en el bufer

            //Acumular segun tipo de servicio
            if (tipoServicio==1){
               particularConsulta++;

            } else if (tipoServicio==2) {
               particularSeguimiento++;

            }else{
               trabajadorBanco++;
            }
         } // fin de try
         // si las líneas 25 o 26 se interrumpen, imprime el rastreo de la pila
         catch ( InterruptedException excepcion )
         {
            excepcion.printStackTrace();
         } // fin de catch


         //Actualizar la hora actual
          tiempo = new GregorianCalendar();
          horaActual = tiempo.getTimeInMillis();

      }
      System.out.println(
              "Se cierra clinica" );

      //calculando totales
      double impParticularConsulta = particularConsulta*300.0;
      double impParticularSeguimiento = particularSeguimiento*100.0;
      double impTrabajadorBanco = trabajadorBanco*0.0;

      int totalCantidad = particularConsulta + particularSeguimiento + trabajadorBanco;
      double totalImporte = impParticularConsulta+ impParticularSeguimiento + impTrabajadorBanco;

      System.out.printf("Servicio\t\tCantidad\t\tImporte\n");
      System.out.printf("P. Consulta\t\t%,5d\t\t%,10.2f\n",particularConsulta,impParticularConsulta);
      System.out.printf("P. Seguimiento\t\t%,1d\t\t%,10.2f\n",particularSeguimiento,impParticularSeguimiento);
      System.out.printf("T. Banco\t\t%,5d\t\t%,10.2f\n",trabajadorBanco,impTrabajadorBanco);
      System.out.printf("Total\t\t%,9d\t\t%,10.2f\n",totalCantidad,totalImporte);


   } // fin del método run
} // fin de la clase Productor



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