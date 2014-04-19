/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Antoine
 */
//CTRL + SHIFT + O pour générer les imports
public class Connexion {
  public static void main(String[] args) {      
    try {
      Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
      System.out.println("Driver O.K.");

      String url = "jdbc:derby://localhost:1527/Routard";
      String user = "B3428";
      String passwd = "B3428";

      Connection conn = DriverManager.getConnection(url, user, passwd);
      System.out.println("Connexion effective !");         
         
    } catch (Exception e) {
      e.printStackTrace();
    }      
  }
}