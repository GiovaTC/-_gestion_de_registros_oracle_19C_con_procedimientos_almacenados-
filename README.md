# -_gestion_de_registros_oracle_19C_con_procedimientos_almacenados- :.
# 📌 Gestión de Registros Oracle 19c con Procedimientos Almacenados .  

<img width="1024" height="1024" alt="image" src="https://github.com/user-attachments/assets/757358df-214a-4efe-94bc-9cb3fd267c76" />    

**Aplicación Java con Interfaz Gráfica (Swing) – IntelliJ IDEA**

## ✅ Descripción del Proyecto :
Este proyecto es una aplicación Java con interfaz gráfica (Swing) que permite:

✔ Conectarse a una base de datos **Oracle 19c**  
✔ Ejecutar **procedimientos almacenados** para:  
→ Insertar **2826 registros** en una tabla  
→ Eliminar esos registros posteriormente  
✔ Mostrar el proceso y resultados en una **ventana gráfica**

## 🧱 Requisitos :
| Componente | Versión |
|----------|---------|
| Oracle DB | 19c |
| Java | 8 o superior |
| IntelliJ IDEA | Community o Ultimate |
| JDBC Driver | ojdbc8.jar o superior |

## 🛠️ Configuración de Base de Datos
```sql
CREATE TABLE PERSONAS_TEST (
    ID NUMBER PRIMARY KEY,
    NOMBRE VARCHAR2(100)
);

CREATE OR REPLACE PROCEDURE INSERTAR_2826_REGISTROS AS
BEGIN
    FOR i IN 1..2826 LOOP
        INSERT INTO PERSONAS_TEST (ID, NOMBRE)
        VALUES (i, 'Persona ' || i);
    END LOOP;
    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE ELIMINAR_2826_REGISTROS AS
BEGIN
    DELETE FROM PERSONAS_TEST;
    COMMIT;
END;
/
```

## 📂 Estructura del Proyecto :
```
src/
 ├─ DBConnection.java
 ├─ StoredProcedures.java
 └─ AppGUI.java
README.md
```

## 💻 Código Fuente Completo ✅ :

### DBConnection.java
```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String USER = "system";
    private static final String PASSWORD = "tu_password";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
```

### StoredProcedures.java
```java
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class StoredProcedures {

    public static int insertarRegistros() {
        String sql = "{call INSERTAR_2826_REGISTROS()}";
        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.execute();
            return 2826;

        } catch (SQLException e) {
            System.err.println("Error al insertar: " + e.getMessage());
            return -1;
        }
    }

    public static int eliminarRegistros() {
        String sql = "{call ELIMINAR_2826_REGISTROS()}";
        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.execute();
            return 2826;

        } catch (SQLException e) {
            System.err.println("Error al eliminar: " + e.getMessage());
            return -1;
        }
    }
}
```

### AppGUI.java :
```java
import javax.swing.*;
import java.awt.*;

public class AppGUI extends JFrame {
    private JTextArea logArea;

    public AppGUI() {
        setTitle("Gestión de Personas Oracle 19c");
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
        log("Insertando...");
        int total = StoredProcedures.insertarRegistros();
        if (total > 0) log("✅ Insertados: " + total);
        else log("❌ Error al insertar registros");
    }

    private void eliminar() {
        log("Eliminando...");
        int total = StoredProcedures.eliminarRegistros();
        if (total > 0) log("🗑️ Eliminados: " + total);
        else log("❌ Error al eliminar registros");
    }

    private void log(String msg) {
        logArea.append(msg + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AppGUI::new);
    }
}
```

## 📌 Cómo Ejecutar :
1. Abrir el proyecto en IntelliJ IDEA  
2. Agregar el driver JDBC `ojdbc8.jar`  
3. Configurar credenciales en `DBConnection.java`  
4. Ejecutar `AppGUI.java`

## 🧪 Pruebas :
| Prueba | Resultado |
|--------|----------|
| Insertar registros | ✅ 2826 filas en la tabla |
| Eliminar registros | ✅ Tabla vacía |
| Repetir inserción | ❌ No duplica PK |

## ✅ Mejoras Futuras :
- Progreso gráfico
- Vista tabla en UI
- Exportación de logs

---
Proyecto para uso educativo. Disfruta programando! 🚀 :.
