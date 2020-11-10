package servidor.sop_rmi;

import cliente.sop_rmi.UsuarioCllbckInt;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ServidorCllbckImpl extends UnicastRemoteObject implements ServidorCllbckint {

    private List<UsuarioCllbckInt> usuarios;
    private ArrayList<usuario> nick;

    public ServidorCllbckImpl() throws RemoteException {
        super();
        usuarios = new ArrayList();
        nick = new ArrayList();
    }

    public List<UsuarioCllbckInt> getUsuarios() {
        return usuarios;
    }

    public ArrayList<usuario> getNick() {
        return nick;
    }

    @Override
    public synchronized boolean registrarUsuario(UsuarioCllbckInt usuario, String nickName) throws RemoteException {
        System.out.println("Invocando al método registrar usuario desde el servidor");
        usuario objUsuer = new usuario();
        boolean bandera = false;
        if (!usuarios.contains(usuario)) {
            if (validarNick(nickName)) {
                objUsuer.llenar(nickName);
                nick.add(objUsuer);
                bandera = usuarios.add(usuario);
                for (UsuarioCllbckInt objUsuario : usuarios) {
                    if (!objUsuario.equals(usuario)) {
                        objUsuario.fijarContacto(nickName);//el servidor hace el callback
                    }
                }
                listarContactos(usuario);
            }
        } else {
            bandera = false;
        }
        return bandera;
    }

    public boolean validarNick(String nickString) throws RemoteException {
        for (int i = 0; i < this.nick.size(); i++) {
            if (nick.get(i).getNick().equals(nickString)) {
                return false;
            }
        }
        return true;
    }

    public void listarContactos(UsuarioCllbckInt objUsuario) throws RemoteException {
        for (int i = 0; i < nick.size(); i++) {
            objUsuario.actualizarContactos(nick.get(i).getNick());
        }
    }

    @Override
    public void enviarMensaje(UsuarioCllbckInt usuario, String mensaje, String nick) throws RemoteException {
        System.out.println("Invocando al método enviar mensaje a los usuarios desde el servidor");
        int pos = buscarObj(nick);
        this.nick.get(pos).agragarListaHoras();
        notificarUsuarios(usuario, nick, mensaje);
    }
    
    private void notificarUsuarios(UsuarioCllbckInt usuario, String nick, String mensaje) throws RemoteException {
        System.out.println("Invocando al método notificar usuarios desde el servidor");
        for (UsuarioCllbckInt objUsuario : usuarios) {
            if (!objUsuario.equals(usuario)) {

                objUsuario.notificar(nick, mensaje);//el servidor hace el callback
            }
        }
    }
    
    @Override
    public boolean desconectarCliente(UsuarioCllbckInt usuario, String nickname) throws RemoteException {
        System.out.println("Invocando al método desonectarCliente desde el servidor");
        usuarios.remove(usuario);
        borrarNick(nickname);
        for (UsuarioCllbckInt objUsuario : usuarios) {
            if (!objUsuario.equals(usuario)) {
                objUsuario.vaciarContactos();
                listarContactos(objUsuario);
                
                objUsuario.notificar(nickname, "Deseconectado!");//el servidor hace el callback
            }
        }
        return true;
    }
    
    public int buscarObj(String prmNick){
        for (int i = 0; i < this.nick.size(); i++) {
            if(this.nick.get(i).getNick().equals(prmNick)){
               return i; 
            }
        }
        return -1;
    }
    
    public void borrarNick(String nick) {
        usuario objUser = null;
        for (int i = 0; i < this.nick.size(); i++) {
            if (this.nick.get(i).getNick().equals(nick)) {
                objUser = this.nick.get(i);
            }
        }
        this.nick.remove(objUser);
    }


}
