// Fig. 23.13: Consumidor.java
// Consumidor con un método run que itera y lee 10 valores del búfer.
import java.util.Random;
import java.util.Calendar;
import java.util.GregorianCalendar;
//los medicos en este caso funcionan como consumidor.

public class Consumidor implements Runnable
{ 
   private final static Random generador = new Random();
   private final Bufer ubicacionCompartida; // referencia al objeto compartido
   //constantes de tiempo
   private final int VEINTE_SEGUNDOS =1000*20;//Tiempo minimo que dura un medico en atender un paciente
   private final int DIEZ_SEGUNDOS =1000*10;//Tiempo extra al tiempo minimo que un medico tarda en atender
   private final int OCHO_MINUTOS =1000*60*8;//horario de la clinica (8 horas lo trabajamos a 8 min)


   // constructor
   public Consumidor( Bufer compartido )
   {
      ubicacionCompartida = compartido;
   } // fin del constructor de Consumidor

   // lee el valor de ubicacionCompartida 10 veces y suma los valores
   public void run()                                           
   {
      int particularConsulta = 0;
      int particularSeguimiento = 0;
      int trabajadorBanco = 0;

      //ciclo de 8 minutos
      Calendar tiempo = new GregorianCalendar();
      long horaInicial = tiempo.getTimeInMillis();
      long horaActual = tiempo.getTimeInMillis();

      //Mantener mientras este abierto o haya pacientes siendo atendidos
      while ((horaActual-horaInicial)<=OCHO_MINUTOS || !ubicacionCompartida.estaVacio()){

         try
         {//trabajo de los doctores
            Thread.sleep( VEINTE_SEGUNDOS+generador.nextInt( DIEZ_SEGUNDOS ) );
            int tipoServicio = ubicacionCompartida.obtener();
            String paciente = "";

            //Acumular segun tipo de servicio
            if (tipoServicio==1){
               particularConsulta++;
               paciente = "particular(Consulta)";

            } else if (tipoServicio==2) {
               particularSeguimiento++;
               paciente = "particular(Seguimiento)";

            }else{
               trabajadorBanco++;
               paciente = "trabajador de banco";
            }
            System.out.println(
                    "Medico atendió paciente "+ paciente );
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
         } // fin de try
         // si las líneas 26 o 27 se interrumpen, imprime el rastreo de la pila
         catch ( InterruptedException excepcion )
         {
            excepcion.printStackTrace();
         } // fin de catch

         //Actualizar la hora actual
         tiempo = new GregorianCalendar();
         horaActual = tiempo.getTimeInMillis();
      }
   }// fin del método run
} // fin de la clase Consumidor



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