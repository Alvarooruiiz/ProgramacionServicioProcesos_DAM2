import javax.swing.*;
import javax.mail.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Properties;

public class BandejaEntrada extends JFrame {
    private JComboBox<String> cbCarpetas;
    private JTextArea taCarpetas;

    private String host = "imap.gmail.com";
    private String username = "alvaro.ruiz.enrique@gmail.com";
    private String password = "";

    private Session session;
    private Store store;

    public BandejaEntrada() {
        setTitle("Email Client");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        cbCarpetas = new JComboBox<>();
        cbCarpetas.addItem("Recibidos");
        cbCarpetas.addItem("Destacados");
        cbCarpetas.addItem("Pospuestos");
        cbCarpetas.addItem("Importantes");
        cbCarpetas.addItem("Enviados");
        cbCarpetas.addItem("Borradores");
        cbCarpetas.addItem("Spam");

        cbCarpetas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cargarCarpeta(cbCarpetas.getSelectedItem().toString());
            }
        });

        topPanel.add(new JLabel("Elige la carpeta:"));
        topPanel.add(cbCarpetas);

        add(topPanel, BorderLayout.NORTH);

        taCarpetas = new JTextArea();
        taCarpetas.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(taCarpetas);
        add(scrollPane, BorderLayout.CENTER);

        connect();
    }

    private void connect() {
        try {
            Properties props = new Properties();
            props.setProperty("mail.store.protocol", "imaps");

            session = Session.getInstance(props, null);
            store = session.getStore();
            store.connect(host, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al conectar con el servidor del correo", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarCarpeta(String folderName) {
        try {
            Folder folder = store.getFolder(obtenerNombresDeCarpetas(folderName));
            folder.open(Folder.READ_ONLY);

            Message[] messages = folder.getMessages();
            taCarpetas.setText("");

            for (int i = 0; i < messages.length; i++) {
                taCarpetas.append("De: " + messages[i].getFrom()[0] + "\n");
                taCarpetas.append("Asunto: " + messages[i].getSubject() + "\n");
                taCarpetas.append("Fecha: " + messages[i].getSentDate() + "\n");
                taCarpetas.append("\n");
            }

            folder.close(false);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar la carpeta.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String obtenerNombresDeCarpetas(String folderName) {
        switch (folderName) {
            case "Recibidos":
                return "INBOX";
            case "Destacados":
                return "[Gmail]/Destacados";
            case "Pospuestos":
                return "[Gmail]/Pospuestos";
            case "Importantes":
                return "[Gmail]/Importantes";
            case "Enviados":
                return "[Gmail]/Enviados";
            case "Borradores":
                return "[Gmail]/Borradores";
            case "Spam":
                return "[Gmail]/Spam";
            default:
                return folderName;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                BandejaEntrada client = new BandejaEntrada();
                client.setVisible(true);
            }
        });
    }
}