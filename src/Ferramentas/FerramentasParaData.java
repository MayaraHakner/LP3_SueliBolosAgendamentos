package Ferramentas;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FerramentasParaData {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public FerramentasParaData() {
        simpleDateFormat.setLenient(false);//faz com que datas erradas sejam detectadas
    }

    public String converteDeDateParaString(Date data) {
        try {
            return simpleDateFormat.format(data); //converte a data para string
        } catch (Exception e) {
            return null;//se algo estiver errado na data, retorne null
            //tem que tratar o erro na classe que chamou
        }
    }

    public Date converteDeStringParaDate(String s) {
        try {
            return simpleDateFormat.parse(s);//converte
        } catch (Exception e) {
            return null;// se algo estiver errado, retorne null
        }
    }

}
