import javax.swing.*;
import java.awt.*;

public class AppGUI extends JFrame {
    private JTextArea logArea;

    public AppGUI() {
        setTitle("Gestión de Personas Oracle 19C");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        logArea = new JTextArea();
        logArea.setEditable(false);

        JButton insertButton = new JButton("Insertar 2826 Registros");
        insertButton.addActionListener(e -> insertar());

        JButton deleteButton = new JButton("Eliminar Registros");
        deleteButton.addActionListener(e -> eliminar());

        JPanel panelBotones = new JPanel();
        panelBotones.add(insertButton);
        panelBotones.add(deleteButton);

        add(new JScrollPane(logArea), BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void insertar() {
        appendLog("Insertando...");
        int total = StoredProcedures.insertarRegistros();
        if (total > 0) appendLog("✅ Insertados: " + total);
        else appendLog("❌ Error al insertar registros");
    }

    private void eliminar() {
        appendLog("Eliminando...");
        int total = StoredProcedures.eliminarRegistros();
        if (total > 0) appendLog("🗑️ Eliminados: " + total);
        else appendLog("❌ Error al eliminar registros");
    }

    private void appendLog(String msg) {
        logArea.append(msg + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AppGUI::new);
    }
}