package FileReader;

import modelo.Eventos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public  class Constantes {

    public static ArrayList<String> NOMBRES=new ArrayList<String>();
    public static ArrayList<Date> FECHAS= new ArrayList<Date>();
    public static ArrayList<Integer> EDADES= new ArrayList<Integer>();
    public static final SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
    public static Integer NUMERO_DE_EVENTOS;
    public static ArrayList<Eventos> EVENTOS=new ArrayList<Eventos>();


}
