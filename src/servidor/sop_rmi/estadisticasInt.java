/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.sop_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author JUAN
 */
public interface estadisticasInt extends Remote {
        public int darCantidadConectados()throws RemoteException;
        public String NickMasMsg() throws RemoteException;

}
