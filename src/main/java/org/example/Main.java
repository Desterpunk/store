package org.example;

import org.example.service.UserService;
import org.example.ui.LoginFrame;

import javax.swing.*;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        // Configurar codificación UTF-8
        System.setProperty("file.encoding", "UTF-8");
        System.setProperty("sun.jnu.encoding", "UTF-8");

        // Configurar el Look and Feel del sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            // Configurar fuente predeterminada para soportar caracteres especiales
            java.awt.Font defaultFont = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12);
            java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();
            while (keys.hasMoreElements()) {
                Object key = keys.nextElement();
                Object value = UIManager.get(key);
                if (value instanceof javax.swing.plaf.FontUIResource) {
                    UIManager.put(key, new javax.swing.plaf.FontUIResource(defaultFont));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Iniciar la aplicación en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            UserService userService = new UserService();
            LoginFrame loginFrame = new LoginFrame(userService);
            loginFrame.setVisible(true);
        });
    }
}