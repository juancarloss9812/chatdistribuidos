package cliente.sop_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UsuarioCllbckInt extends Remote
{	
	public void notificar(String nickName ,String mensaje) throws RemoteException;
        public void actualizarContactos(String contactos) throws RemoteException;
        public void fijarContacto(String nickName) throws RemoteException;
        public void vaciarContactos() throws RemoteException;
}


