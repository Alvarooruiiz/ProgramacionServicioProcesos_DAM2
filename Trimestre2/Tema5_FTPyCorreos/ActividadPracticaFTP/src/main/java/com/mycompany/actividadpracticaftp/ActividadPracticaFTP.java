package com.mycompany.actividadpracticaftp;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class ActividadPracticaFTP {
    
    private FTPClient clienteFTP;
    private static final String SERVIDOR = "localhost";
    private static final int PUERTO = 21;
    private static final String USUARIO = "admin";
    private static final String PASSWORD = "usuario"; 
    
    public ActividadPracticaFTP(){
        clienteFTP = new FTPClient();
    }
    
    private void conectar() throws SocketException, IOException {
        clienteFTP.connect(SERVIDOR,PUERTO);
        int respuesta = clienteFTP.getReplyCode();
        if (!FTPReply.isPositiveCompletion(respuesta)) {
            clienteFTP.disconnect();
            throw new IOException("Error al conectar con el servidor FTP");
        }
        
        boolean credencialesOK = clienteFTP.login(USUARIO,PASSWORD);
        
        if (!credencialesOK) {
            throw new IOException("Error al conectar con el servidor FTP. Credenciales incorrectas");     
        }
        
        clienteFTP.setFileType(FTP.BINARY_FILE_TYPE);
    }
    
    
    private void desconectar() throws IOException {
        clienteFTP.disconnect();
    }
    
    
    private boolean subirFichero(String path) throws IOException {
        File ficheroLocal = new File(path);
        InputStream is = new FileInputStream(ficheroLocal);
        boolean enviado = clienteFTP.storeFile(ficheroLocal.getName(), is);
        is.close();
        return enviado;
    }
    
    
    private boolean descargarFichero(String ficheroRemoto, String pathLocal) throws IOException {
        OutputStream os = new BufferedOutputStream(new FileOutputStream(pathLocal));
        boolean recibido = clienteFTP.retrieveFile(ficheroRemoto,os);
        os.close();
        return recibido;
    }
    
    public void descargaArchivos(String directorioRemoto, String directorioLocal) throws IOException {
        try {
            conectar();

            FTPFile[] archivosRemotos = clienteFTP.listFiles(directorioRemoto);

            for (FTPFile archivoRemoto : archivosRemotos) {
                if (archivoRemoto.isFile()) {
                    String nombreArchivoRemoto = archivoRemoto.getName();
                    String rutaLocal = directorioLocal + File.separator + nombreArchivoRemoto;

                    boolean descargado = descargarFichero(nombreArchivoRemoto, rutaLocal);

                    if (descargado) {
                        System.out.println("Archivo descargado: " + nombreArchivoRemoto);
                    } else {
                        System.out.println("Error al descargar el archivo: " + nombreArchivoRemoto);
                    }
                }
            }
            System.out.println("Los archivos descargados se encuentran en la carpeta ArchivosDescargadosFTP del proyecto");
        } finally {
            desconectar();
        }
    }
    
    public static void main(String[] args) {
        ActividadPracticaFTP ftp = new ActividadPracticaFTP();
        String directorioRemoto = "/";
        String directorioLocal = "./ArchivosDescargadosFTP";
        
        try {
            ftp.descargaArchivos(directorioRemoto, directorioLocal);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
