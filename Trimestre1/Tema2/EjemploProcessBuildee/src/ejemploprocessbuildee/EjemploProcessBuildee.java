package ejemploprocessbuildee;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class EjemploProcessBuildee {

    public static void main(String[] args) {
        ProcessBuilder pBuilder = new ProcessBuilder("Notepad.exe", "notas.txt");
        pBuilder.directory(new File("C:\\Users\\Alvar\\Desktop"));
        Map<String, String> env = pBuilder.environment();
        for (String key : env.keySet()) {
            System.out.println(key + ": " + env.get(key));
        }
        try {
            Process proceso=pBuilder.start();
            proceso.waitFor();
            int valoresRetorno = proceso.exitValue();
            System.out.println("El valor de salida es: " + valoresRetorno);
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }

    }

}
