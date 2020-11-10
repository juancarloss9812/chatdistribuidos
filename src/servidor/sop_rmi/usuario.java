/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.sop_rmi;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author JUAN
 */
public class usuario {

    private String nick;
    private ArrayList<LocalDateTime> listaHoras;
    private int numeroMsgUltiMin;

    public usuario() {
        this.listaHoras = new ArrayList<>();
        this.numeroMsgUltiMin = 0;
    }

    public String getNick() {
        return nick;
    }
    
    public void setNick(String nick) {
        this.nick = nick;
    }

    public ArrayList<LocalDateTime> getListaHoras() {
        return listaHoras;
    }

    public void agragarListaHoras() {
        LocalDateTime hora = LocalDateTime.now();
        this.listaHoras.add(hora);
    }

    public int getNumeroMsgUltiMin() {
        return numeroMsgUltiMin;
    }

    public void llenar(String nick) {
        this.nick = nick;
        this.numeroMsgUltiMin = 0;
    }

    public void setListaHoras(ArrayList<LocalDateTime> listaHoras) {
        this.listaHoras = listaHoras;
    }

    
    
    public void setNumeroMsgUltiMin(LocalDateTime horaActual) {
        numeroMsgUltiMin=0;
        ArrayList<LocalDateTime> aux = new ArrayList<>();
        int minutos = horaActual.getMinute()-1;
        int segundos = horaActual.getSecond();
        for (LocalDateTime hora : listaHoras) {
            if((horaActual.getHour()==hora.getHour() && hora.getMinute() == minutos) && (hora.getSecond()>=segundos)){
                aux.add(hora);
                numeroMsgUltiMin++; 
            }else if((horaActual.getHour()==hora.getHour() && hora.getMinute() == horaActual.getMinute()) && (hora.getSecond()<=segundos)){
                aux.add(hora);
                numeroMsgUltiMin++;
            }
        }
        listaHoras= aux;
    }
    
}
