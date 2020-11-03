package servidor.sop_rmi;

import cliente.sop_rmi.UsuarioCllbckInt;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServidorCllbckint extends Remote
{
	public boolean registrarUsuario(UsuarioCllbckInt  usuario, String nickName) throws RemoteException;
        public void enviarMensaje(UsuarioCllbckInt  usuario,String nick, String mensaje)throws RemoteException;
        public boolean desconectarCliente(UsuarioCllbckInt usuario, String nickname)throws RemoteException;
}


