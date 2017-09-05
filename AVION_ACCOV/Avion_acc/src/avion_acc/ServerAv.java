/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avion_acc;

import avion_accov.Radar_detector;
import avion_accov.Saca;

/*
 *
 * @author ne3mat slim
 */

public class ServerAv {
   
    public static void main(String args[]) throws Exception{
        Radar_detector r = new Radar_detector();
        Saca c = new Saca(r);
         c.start();
    }
    
    
}
