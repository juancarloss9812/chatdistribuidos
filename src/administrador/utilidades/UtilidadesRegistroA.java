/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administrador.utilidades;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author aldair
 */
public class UtilidadesRegistroA {

    public static Remote obtenerObjRemoto(String dirIp, int puerto, String nameObjReg) {
        String URLRegistro;
        URLRegistro = "rmi://" + dirIp + ":" + puerto + "/" + nameObjReg;
        try {
            return Naming.lookup(URLRegistro);
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            System.out.println("Excepcion en obtencon del objeto remoto " + e);
            return null;
        }
    }
}
