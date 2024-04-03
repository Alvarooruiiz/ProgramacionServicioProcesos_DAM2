/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prebaurlconnection;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author alvar
 */
public class PrebaURLConnection {

    public static void main(String[] args) throws IOException {
        URL url = new URL("http://www.parainfo.es");
        URLConnection conexionURL = url.openConnection();
        InputStream is = conexionURL.getInputStream();
        
        int c;
        while((c=is.read())!=-1){
            System.out.print((char)c);
        }
        is.close();   
    }
}
