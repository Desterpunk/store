package org.example.ui;

import org.example.model.User;
import org.example.service.UserService;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private UserService userService;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame(UserService userService) {
        this.userService = userService;
        initComponents();
    }

    private void initComponents() {
        setTitle("Store - Iniciar Sesión");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel de título - Usar HTML para mejor soporte de caracteres
        JLabel titleLabel = new JLabel("<html><center>🛒 Store Manager</center></html>", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Panel de formulario
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        JLabel userLabel = new JLabel("Usuario:");
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        formPanel.add(userLabel);
        usernameField = new JTextField();
        formPanel.add(usernameField);

        JLabel passLabel = new JLabel("Contraseña:");
        passLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        formPanel.add(passLabel);
        passwordField = new JPasswordField();
        formPanel.add(passwordField);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton loginButton = new JButton("Ingresar");
        JButton registerButton = new JButton("Registrarse");

        loginButton.addActionListener(e -> login());
        registerButton.addActionListener(e -> register());

        // Permitir login con Enter
        passwordField.addActionListener(e -> login());

        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void login() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Por favor ingrese usuario y contraseña",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        User user = userService.authenticate(username, password);

        if (user != null) {
            dispose();
            if (user.isAdmin()) {
                new AdminFrame(user).setVisible(true);
            } else {
                new CustomerFrame(user).setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(this,
                "Usuario o contraseña incorrectos",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            passwordField.setText("");
        }
    }

    private void register() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Por favor ingrese usuario y contraseña",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        userService.registerUser(username, password);
        JOptionPane.showMessageDialog(this,
            "Usuario registrado exitosamente",
            "Éxito",
            JOptionPane.INFORMATION_MESSAGE);

        usernameField.setText("");
        passwordField.setText("");
    }
}
