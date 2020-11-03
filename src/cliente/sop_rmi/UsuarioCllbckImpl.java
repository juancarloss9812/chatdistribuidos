package cliente.sop_rmi;

import cliente.GUICliente;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class UsuarioCllbckImpl extends UnicastRemoteObject implements UsuarioCllbckInt
{
    GUICliente GUIC;
    String nick="";
    public UsuarioCllbckImpl(GUICliente GUIC) throws RemoteException {
        super();
        this.GUIC = GUIC;
    }
    
    
    @Override
    public void notificar(String nickName ,String mensaje) throws RemoteException {
            GUIC.fijarTexto(nickName, mensaje);
    }
    @Override
    public void actualizarContactos(String contactos) throws RemoteException {
        GUIC.fijarContacto(contactos);
    }
    @Override
    public void fijarContacto(String nickName) throws RemoteException {
        nick= nickName;
            GUIC.fijarContacto(nickName);
    }
    @Override
    public void vaciarContactos() throws RemoteException {
        GUIC.borrarLista();
    }
}
