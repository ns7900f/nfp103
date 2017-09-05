/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avion_accov;

/**
 *
 * @author Ne3mat slim
 */

import java.io.*;
import static java.lang.Thread.sleep;
import java.net.*;

public class Avion {
    static final int INTERVALLE = 3000;//millisecondes
    static final String hostName = "localhost";
    static final int portNumber = 350;
    static final int VITMIN = 200;
    int id; //numero du vol
    String nom;
    String depart,arrivee;
    double arr_pos_x,arr_pos_y,arr_pos_z;
    double cap,altitude;
    double vitesse_x,vitesse_y,vitesse_z;
    double position_x,position_y,position_z;
    
    public Avion(int id,String nom,String depart,String arrivee, double arr_pos_x,double arr_pos_y,double arr_pos_z){
        this.id =id;
        this.nom = nom;
        this.depart = depart;
        this.arrivee = arrivee;
        this.arr_pos_x = arr_pos_x;
        this.arr_pos_y = arr_pos_y;
        this.arr_pos_z = arr_pos_z;
        position_x = 0;
        position_y = 0;
        position_z = 0;
        vitesse_x = 0;
        vitesse_y = 0;
        vitesse_z = 0;
        cap = 0;
        altitude = 0;
        
    }
    
    int modif(double vitesse_x,double vitesse_y,double vitesse_z,double cap,double altitude){
        this.vitesse_x = vitesse_x;
        this.vitesse_y = vitesse_y;
        this.vitesse_z = vitesse_z;
        this.cap = cap;
        this.altitude = altitude;
    
        
        double cosinus, sinus, tang;
        double dep_x, dep_y, dep_z;

        cosinus = Math.cos(cap * 2 * Math.PI / 360);
        sinus = Math.sin(cap * 2 * Math.PI / 360);
        tang = Math.tan(cap * 2 * Math.PI/360);

        
        dep_x = cosinus * vitesse_x * 10 / VITMIN;
        dep_y = sinus * vitesse_y * 10 / VITMIN;
        dep_z = tang * vitesse_z * 10/ VITMIN;

        if ((dep_x > 0) && (dep_x < 1)) dep_x = 1;
        if ((dep_x < 0) && (dep_x > -1)) dep_x = -1;

        if ((dep_y > 0) && (dep_y < 1)) dep_y = 1;
        if ((dep_y < 0) && (dep_y > -1)) dep_y = -1;
        
        if ((dep_z > 0) && (dep_z < 1)) dep_z = 1;
        if ((dep_z < 0) && (dep_z > -1)) dep_z = -1;

        //printf(" x : %f y : %f\n", dep_x, dep_y);

        position_x = position_x + (int) dep_x;
        position_y = position_y + (int) dep_y;
        position_z = position_z + (int) dep_z;
       
        
        if((position_x == arr_pos_x) && (position_y == arr_pos_y) && (position_z == arr_pos_z) ){
            //arrivee a destination
            return -1;
        }
        return 0;
    }
    
    void analyse(Socket clientSocket){
        try{
            BufferedReader inFromSaca;
            try (PrintWriter outToSaca = new PrintWriter(clientSocket.getOutputStream())) {
                inFromSaca = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                outToSaca.println(id + "/" + nom + "/" + arr_pos_x + "/" + arr_pos_y + "/" + arr_pos_z ); //evoie id/nom/arrivee
                outToSaca.flush();
                while(true){
                    String [] s = inFromSaca.readLine().split("/"); //vitesse/cap/altitude
                    int res = modif(Double.parseDouble(s[0]),Double.parseDouble(s[1]),Double.parseDouble(s[2]),Double.parseDouble(s[3]),Double.parseDouble(s[4]));
                    if(res == -1){
                        outToSaca.println(position_x + "/" + position_y + "/" + position_z + "/end");
                        outToSaca.flush();
                        break;
                    }
                    sleep(INTERVALLE);
                    
                    outToSaca.println(position_x + "/" + position_y + "/" + position_z);
                    outToSaca.flush();
                }
            }
            inFromSaca.close();
        }catch(IOException | NumberFormatException | InterruptedException e){System.out.println("arret d'execution"); System.exit(0);}
    }
    
    public void start ()throws Exception{
        
        try (Socket clientSocket = new Socket(hostName,portNumber)) {
            analyse(clientSocket);
        }
   
    }   
    public void Destroy ()throws Exception{
        
        try (Socket clientSocket = new Socket(hostName,portNumber)) {
            clientSocket.close();
        }
   
    }   
}
