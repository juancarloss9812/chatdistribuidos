/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.sop_rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;

/**
 *
 * @author JUAN
 */
public class estadisticaImp extends UnicastRemoteObject implements estadisticasInt{
    ServidorCllbckImpl objServidor;
    
    public estadisticaImp(ServidorCllbckImpl prmServidor) throws RemoteException {
        super();
        objServidor = prmServidor;
    }
    @Override
    public int darCantidadConectados() throws RemoteException {
        System.out.println("Invocando al método darCantidadConectados desde el servidor.");
        return objServidor.getUsuarios().size();
    }

    @Override
    public String NickMasMsg() throws RemoteException {
        System.out.println("Incando al metodo que envia el nick que envio mas mensajes en el ultimo minuto.");
        if (!objServidor.getNick().isEmpty()) {
            String mensaje;
            agregarContadorMsg();
            ordenar();
            if (objServidor.getNick().get(0).getNumeroMsgUltiMin() != 0) {
                mensaje = "El usuario que envio más mensajes en el ultimo minuto fue " + objServidor.getNick().get(0).getNick() + " envio " + objServidor.getNick().get(0).getNumeroMsgUltiMin() + " mensajes.";
                return mensaje;
            } else {
                return "En el ultimo minuto no han enviado mensajes los usuarios.";
            }
        }
        return "No hay usuarios conectados.";
    }
        public void agregarContadorMsg() {
        LocalDateTime horaActual = LocalDateTime.now();
        
        for (int i = 0; i < this.objServidor.getNick().size(); i++) {
            this.objServidor.getNick().get(i).setNumeroMsgUltiMin(horaActual);
        }

    }

    public void ordenar() {
        usuario aux;
        boolean movimiento = true;
        int contRondas = 0;
        while (movimiento) {
            movimiento = false;
            for (int i = 1; i < this.objServidor.getNick().size() - contRondas; i++) {
                if (this.objServidor.getNick().get(i).getNumeroMsgUltiMin() > this.objServidor.getNick().get(i - 1).getNumeroMsgUltiMin()) {

                    movimiento = true;
                    aux = this.objServidor.getNick().get(i);
                    this.objServidor.getNick().set(i, this.objServidor.getNick().get(i - 1));
                    this.objServidor.getNick().set(i - 1, aux);
                }
            }
        }
    }

}
