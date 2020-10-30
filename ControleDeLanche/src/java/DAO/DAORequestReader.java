/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.BufferedReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author makefake
 */
public class DAORequestReader {
    
         
     public JSONObject requestReader(BufferedReader reader){
         JSONObject json=null;
         StringBuffer jb = new StringBuffer();
         String line = null;
    try {
      
        while ((line = reader.readLine()) != null) {
            jb.append(line);
            System.out.println(line);
            
        }
    } catch (Exception e) { /*report an error*/ }

    
    try {
  
  
   
JSONParser parser = new JSONParser();
json = (JSONObject) parser.parse(jb.toString());
 

       
 } catch (Exception e) {
  // crash and burn
  e.printStackTrace();
 }
    
    return json;
     }
    
}
