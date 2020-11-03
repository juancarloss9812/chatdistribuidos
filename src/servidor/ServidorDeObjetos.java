package servidor;

import servidor.utilidades.UtilidadesConsola;
import servidor.utilidades.UtilidadesRMIServidor;
import servidor.sop_rmi.ServidorCllbckImpl;

public class ServidorDeObjetos {
    public static void main(String args[])
    {         
        int numPuertoRMIRegistry = 0;
        String direccionIpRMIRegistry = "";
        
            System.out.println("Cual es el la dirección ip donde se encuentra  el rmiregistry ");
            direccionIpRMIRegistry = UtilidadesConsola.leerCadena();
            System.out.println("Cual es el número de puerto por el cual escucha el rmiregistry ");
            numPuertoRMIRegistry = UtilidadesConsola.leerEntero(); 
        try
        {
            ServidorCllbckImpl objRemoto = new ServidorCllbckImpl();  
            UtilidadesRMIServidor.ArrancarNS(numPuertoRMIRegistry);
            UtilidadesRMIServidor.RegistrarObjetoRemoto(objRemoto, direccionIpRMIRegistry, numPuertoRMIRegistry,"ServidorUsuarios");            
            System.out.println("Objeto remoto registrado, esperado peticiones ...");
        } catch (Exception e)
        {
            System.err.println("No se pudo Arrancar el NS o Registrar el objeto remoto");
        }                
    }
}
