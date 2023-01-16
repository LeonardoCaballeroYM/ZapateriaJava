package Utilidades;

import java.util.Calendar;

/**
 *
 * @author Ing. Luis Antonio Tapia Mondragón
 */
public class Fecha {
    
    String dia, diaSemana, mes, anio, nuevaFecha; 
    int hora, minuto, segundo;
    Calendar fecha = Calendar.getInstance();
    
    public String getAnioActual()
    {
        return Integer.toString(fecha.get(Calendar.YEAR));
    }

    public String getMesActual()
    {
        return Integer.toString(fecha.get(Calendar.MONTH) + 1);
    }

    public String getDiaActual()
    {
        return Integer.toString(fecha.get(Calendar.DAY_OF_MONTH));
    }

    public String getFechaActual()
    {
        dia = getDiaActual();
        mes = getMesActual();
        anio = getAnioActual();
        diaSemana = TraducirDia(fecha.get(Calendar.DAY_OF_WEEK));
        nuevaFecha = anio + "/" + (fecha.get(Calendar.MONTH) + 1) + "/" + dia;
        
        return nuevaFecha;
    }
    
    public String getHora()
    {
        hora = fecha.get(Calendar.HOUR);
        if (hora == 0) 
            hora = 12;
        
        if (hora < 10)
            return "0" + hora;
        else
            return "" + hora;
    }
    
    public String getMinuto()
    {
        if (fecha.get(Calendar.MINUTE) < 10)
            return "0" + fecha.get(Calendar.MINUTE);
        else
            return "" + fecha.get(Calendar.MINUTE);
    }
    
    public String getSegundo()
    {
        if (fecha.get(Calendar.SECOND) < 10)
            return "0" + fecha.get(Calendar.SECOND);
        else
            return "" + fecha.get(Calendar.SECOND);
    }
    
    public String AmPm()
    {
        if (Calendar.AM_PM == Calendar.AM)
            return "A.M.";
        else
            return "P.M.";
    }
    
    public String TraducirMes(int mes)
    {
        String[] espaniol = {
            "Enero", "Febrero", "Marzo",
            "Abril", "Mayo", "Junio",
            "Julio", "Agosto", "Septiembre",
            "Octubre", "Noviembre", "Diciembre"
        };
        return espaniol[mes];
    }

    public String TraducirDia(int dia)
    {
        String[] es = {"Día desconocido", "Domingo", "Lunes", "Martes", 
            "Miércoles", "Jueves", "Viernes", "Sábado"};
        return es[dia];
    }

}
