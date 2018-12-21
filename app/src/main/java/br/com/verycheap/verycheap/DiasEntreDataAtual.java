package br.com.verycheap.verycheap;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DiasEntreDataAtual {


    public int quantidadeDias(String data) {

        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            java.sql.Date data1 = new java.sql.Date(format.parse(data).getTime());

            Date dataA = new Date(System.currentTimeMillis());
            String dataAtual1 = format.format(dataA);

            java.sql.Date dataAtual2 = new java.sql.Date(format.parse(dataAtual1).getTime());

            int dias = (int) ((dataAtual2.getTime() - data1.getTime()) / 86400000L);

            return dias;

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 999;
    }
}
