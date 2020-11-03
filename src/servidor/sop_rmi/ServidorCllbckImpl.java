package servidor.sop_rmi;

import cliente.sop_rmi.UsuarioCllbckInt;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ServidorCllbckImpl extends UnicastRemoteObject implements ServidorCllbckint {

    private List<UsuarioCllbckInt> usuarios;
    private List<String> nick;
    
    public ServidorCllbckImpl() throws RemoteException
    {
        super();
        usuarios= new ArrayList();
        nick= new ArrayList();
    }
    
    @Override
    public synchronized boolean  registrarUsuario(UsuarioCllbckInt usuario, String nickName) throws RemoteException {
        System.out.println("Invocando al método registrar usuario desde el servidor");
        boolean bandera=false;
        if (!usuarios.contains(usuario))
        {
            nick.add(nickName);
            bandera= usuarios.add(usuario);
            for(UsuarioCllbckInt objUsuario: usuarios)
            {
                if(!objUsuario.equals(usuario)){
                    objUsuario.fijarContacto(nickName);//el servidor hace el callback
                }
            }
            listarContactos(usuario);
            
            
            
        }        
        return bandera;       
    }
   
    public void listarContactos(UsuarioCllbckInt objUsuario) throws RemoteException{
        for (int i = 0; i < nick.size(); i++) {
            objUsuario.actualizarContactos(nick.get(i));
        }
    }
    @Override
    public void enviarMensaje(UsuarioCllbckInt  usuario, String mensaje, String nick)throws RemoteException
    {
          notificarUsuarios(usuario ,mensaje , nick );
    }
    
    private void notificarUsuarios(UsuarioCllbckInt usuario ,String nick, String mensaje) throws RemoteException {
        System.out.println("Invocando al método notificar usuarios desde el servidor");
        for(UsuarioCllbckInt objUsuario: usuarios)
        {
            if(!objUsuario.equals(usuario)){
                
                objUsuario.notificar(nick,mensaje);//el servidor hace el callback
            }
        }
    }

    @Override
    public boolean desconectarCliente(UsuarioCllbckInt usuario, String nickname) throws RemoteException {
        usuarios.remove(usuario);
        nick.remove(nickname);
        for(UsuarioCllbckInt objUsuario: usuarios)
        {
            if(!objUsuario.equals(usuario)){
                objUsuario.vaciarContactos();
                listarContactos(objUsuario);
                objUsuario.notificar(nickname,"Deseconectado!");//el servidor hace el callback
            }
        }
        return true;
    }
    
}

