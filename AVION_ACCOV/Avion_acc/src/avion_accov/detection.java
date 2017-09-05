/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avion_accov;

/**
 *
 * @author Ne3mat Slim
 */
public class detection {
    
    double arr_pos_x,arr_pos_y,arr_pos_z;

    
    String calculPosition( double position_x, double position_y, double position_z, double cap, double altitude){
        
        return (modify(position_x, position_y, position_z, cap, altitude));
    }
    
    String calculPosition( double arr_pos_x,double arr_pos_y, double arr_pos_z,double position_x, double position_y, double position_z, double cap, double altitude){
       this.arr_pos_x = arr_pos_x;
       this.arr_pos_y = arr_pos_y;
       this.arr_pos_z = arr_pos_z;
       return (modify(position_x, position_y, position_z, cap, altitude));
    }
     
    String modify(double position_x, double position_y, double position_z, double cap, double altitude){
        
       String resultat = (Math.random()*100) + "/" + (Math.random()*100) + "/" +  (Math.random()*100) + "/" + (Math.random()*100) + "/" + (Math.random()*100) ;

       return resultat; //vitesse/cap/altitude
    }
    
}
