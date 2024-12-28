package Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;



import Properties.PropertiesISW;
import modelo.Usuario;
import message.Message;

public class Client {
    private String host;
    private int port;

    public Client(String host, int port) {
        this.host=host;
        this.port=port;
    }
    public Client() {
        this.host = PropertiesISW.getInstance().getProperty("host");//Que es lo que se ejecuta aqui
        this.port = Integer.parseInt(PropertiesISW.getInstance().getProperty("port"));
    }
    public HashMap<String, Object> sentMessage(String Context, HashMap<String, Object> session) {

        System.out.println("Host: "+host+" port"+port);
        Message mensajeEnvio=new Message();
        Message mensajeVuelta=new Message();
        mensajeEnvio.setContext(Context);
        mensajeEnvio.setSession(session);
        this.sent(mensajeEnvio,mensajeVuelta);
        session = mensajeVuelta.getSession();
        return session;
    }
    public void sent(Message messageOut, Message messageIn) {
        try {

            System.out.println("Connecting to host " + host + " on port " + port + ".");

            Socket echoSocket = null;
            OutputStream out = null;
            InputStream in = null;

            try {
                echoSocket = new Socket(host, port);
                in = echoSocket.getInputStream();
                out = echoSocket.getOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);

                //Create the objetct to send
                objectOutputStream.writeObject(messageOut);

                // create a DataInputStream so we can read data from it.
                ObjectInputStream objectInputStream = new ObjectInputStream(in);
                Message msg=(Message)objectInputStream.readObject();
                messageIn.setContext(msg.getContext());
                messageIn.setSession(msg.getSession());
		        /*System.out.println("\n1.- El valor devuelto es: "+messageIn.getContext());
		        String cadena=(String) messageIn.getSession().get("Nombre");
		        System.out.println("\n2.- La cadena devuelta es: "+cadena);*/

            } catch (UnknownHostException e) {
                System.err.println("Unknown host: " + host);
                System.exit(1);
            } catch (IOException e) {
                System.err.println("Unable to get streams from server");
                System.out.println(e.getMessage());
                System.exit(1);
            }

            /** Closing all the resources */
            out.close();
            in.close();
            echoSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
