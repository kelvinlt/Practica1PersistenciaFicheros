package bugs;

import java.io.*;
import java.util.*;

public class main {
    //Fichero donde estan guardados los datos de los bugs y que es lo que falla
    static File bugsFile = new File("rcBugPackage.txt");
    //Fichero donde estan guardados los datos de las personas/empresas que mantienen el software
    static File emailsFile = new File("packageMaintainer.txt");
    //Fichero donde se guardara el "Email" para el que mantiene el programa
    static File dest = new File("emailMainteiner.txt");

    public static void main(String[] args) {
        try {
            //Entrada para que pongan la ID del bug por un metodo que pilla un string
            String entrada = pedirCadena("Introduce ID");

            String[] respuesta1 = search1(entrada);
            String[] respuesta2 = search2(respuesta1[1]);

            sendEmail(respuesta1,respuesta2);


        } catch (Exception e) {
        }
    }

    public static String[] search1(String entrada) {
        String[] respuesta= new String[2];
        try {
            FileReader fr = new FileReader(bugsFile);
            BufferedReader br = new BufferedReader(fr);
            String linea1;

            while ((linea1 = br.readLine()) != null) {
                String[] temp = splitStringX(linea1);
                if (temp[0].equals(entrada)) {
                    System.out.println("Bug ID: " + temp[0]);
                    System.out.println("Package Name: " + temp[1]);
                    respuesta[0] = temp[0];
                    respuesta[1] = temp[1];
                }
            }
            fr.close();


        } catch (Exception e) {

        }
        return respuesta;
    }

    public static String[] search2(String entrada){
        String[] respuesta = new String[2];
        try {
            FileReader fr = new FileReader(emailsFile);
            BufferedReader br = new BufferedReader(fr);
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] temp = splitStringX(linea);
                if (temp[0].equals(entrada)) {
                    System.out.println("Nombre Encargado: " + temp[1]);
                    System.out.println("Email Encargado: " + temp[2]);
                    respuesta[0] = temp[1];
                    respuesta[1] = temp[2];
                }
            }
            fr.close();
        }catch (Exception e){

        }
        return respuesta;
    }

    public static String[] splitStringX(String algo) {
        String conversor = algo;
        String[] splitString = conversor.split(";");
        return splitString;
    }

    public static void sendEmail(String[] respuesta1,String[] respuesta2) {
        try {
            FileWriter fw = new FileWriter("emailMainteiner.txt");
            BufferedWriter bw = new BufferedWriter(fw);

            System.out.println("");
            String emailToSend = "From: owner@bugs.debian.org";
//                    + System.getProperty("line.separator") +
//                    "To: "+respuesta2[1]+""
//                    + System.getProperty("line.separator") +
//                    "Dear "+respuesta2[0]+""
//                    + System.getProperty("line.separator") +
//                    "You have a new bug:"
//                    + System.getProperty("line.separator") +
//                    ""+respuesta1[1]+" RC bug number #"+respuesta1[0]+""
//                    + System.getProperty("line.separator") +
//                    "Please, fix it as soon as possible."
//                    + System.getProperty("line.separator") +
//                    "Cheers";
            System.out.println(emailToSend);
            bw.write(emailToSend);

        }catch (Exception e){
        }
    }

    public static String pedirCadena(String mensaje) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String texto = "";
        boolean error = true;
        do {
            System.out.println(mensaje);
            try {
                texto = br.readLine();
                if (texto.equals("")) {
                    System.out.println("No puedes dejar en blanco los datos");

                } else {
                    error = false;
                }
            } catch (IOException ex) {
                System.out.println("Error de entrada y salida");
            }
        } while (error);
        return texto;
    }


}
