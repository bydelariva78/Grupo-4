package FileReader;

import java.io.BufferedReader;
import java.io.FileReader;


public class Reader{
    private static final String file = "src/FileReader/discotecas_actuales.txt";

    public static void leerOpciones(){
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String l=br.readLine();
            Constantes.NUMERO_DE_EVENTOS=0;
            while (l!=null)
            {
                String[] s= l.split(",");
                System.out.println(Constantes.NUMERO_DE_EVENTOS);
                Constantes.NOMBRES.add(s[0]);
                Constantes.FECHAS.add(Constantes.dateFormat.parse(s[1]));
                Constantes.EDADES.add(Integer.parseInt(s[2]));
                Constantes.NUMERO_DE_EVENTOS= Constantes.NUMERO_DE_EVENTOS+1;
                l= br.readLine();
            }
        }
        catch(Exception e)
        {
            System.out.println("Se produjo una excepción: " + e.getClass().getSimpleName());
            System.out.println("Mensaje: " + e.getMessage());
            e.printStackTrace();
        }


    }

}