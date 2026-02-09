package org.example.ui;

import org.example.model.CartItem;
import org.example.model.Product;
import org.example.model.User;
import org.example.service.CartService;
import org.example.service.ProductService;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class CustomerFrame extends JFrame {
    private User user;
    private ProductService productService;
    private CartService cartService;
    private DefaultListModel<Product> productListModel;
    private DefaultListModel<CartItem> cartListModel;
    private JList<Product> productList;
    private JList<CartItem> cartList;
    private JLabel totalLabel;

    public CustomerFrame(User user) {
        this.user = user;
        this.productService = new ProductService();
        this.cartService = new CartService();
        initComponents();
        loadProducts();
    }

    private void initComponents() {
        setTitle("Store - Cliente");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel superior
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Bienvenido: " + user.getUsername());
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        topPanel.add(welcomeLabel, BorderLayout.WEST);

        JButton logoutButton = new JButton("Cerrar Sesión");
        logoutButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        logoutButton.addActionListener(e -> logout());
        topPanel.add(logoutButton, BorderLayout.EAST);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Panel central dividido
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(500);

        // Panel izquierdo - Productos disponibles
        JPanel leftPanel = new JPanel(new BorderLayout(5, 5));
        leftPanel.setBorder(BorderFactory.createTitledBorder("Productos Disponibles"));

        productListModel = new DefaultListModel<>();
        productList = new JList<>(productListModel);
        productList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        productList.setCellRenderer(new ProductCellRenderer());
        productList.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        JScrollPane productScrollPane = new JScrollPane(productList);
        leftPanel.add(productScrollPane, BorderLayout.CENTER);

        JButton addToCartButton = new JButton("Agregar al Carrito");
        addToCartButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        addToCartButton.addActionListener(e -> addToCart());
        leftPanel.add(addToCartButton, BorderLayout.SOUTH);

        splitPane.setLeftComponent(leftPanel);

        // Panel derecho - Carrito
        JPanel rightPanel = new JPanel(new BorderLayout(5, 5));
        rightPanel.setBorder(BorderFactory.createTitledBorder("Carrito de Compras"));

        cartListModel = new DefaultListModel<>();
        cartList = new JList<>(cartListModel);
        cartList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cartList.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        JScrollPane cartScrollPane = new JScrollPane(cartList);
        rightPanel.add(cartScrollPane, BorderLayout.CENTER);

        // Panel inferior del carrito
        JPanel cartBottomPanel = new JPanel(new BorderLayout(5, 5));

        totalLabel = new JLabel("Total: $0.00");
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        totalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        cartBottomPanel.add(totalLabel, BorderLayout.NORTH);

        JPanel cartButtonPanel = new JPanel(new GridLayout(1, 2, 5, 5));

        JButton removeFromCartButton = new JButton("Quitar del Carrito");
        JButton checkoutButton = new JButton("Comprar");

        removeFromCartButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        checkoutButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        removeFromCartButton.addActionListener(e -> removeFromCart());
        checkoutButton.addActionListener(e -> checkout());

        cartButtonPanel.add(removeFromCartButton);
        cartButtonPanel.add(checkoutButton);

        cartBottomPanel.add(cartButtonPanel, BorderLayout.SOUTH);

        rightPanel.add(cartBottomPanel, BorderLayout.SOUTH);

        splitPane.setRightComponent(rightPanel);

        mainPanel.add(splitPane, BorderLayout.CENTER);

        add(mainPanel);
    }

    private void loadProducts() {
        productListModel.clear();
        for (Product product : productService.getAllProducts()) {
            productListModel.addElement(product);
        }
    }

    private void updateCart() {
        cartListModel.clear();
        for (CartItem item : cartService.getCartItems()) {
            cartListModel.addElement(item);
        }
        totalLabel.setText(String.format("Total: $%.2f", cartService.getTotal()));
    }

    private void addToCart() {
        Product selectedProduct = productList.getSelectedValue();
        if (selectedProduct == null) {
            JOptionPane.showMessageDialog(this,
                "Por favor seleccione un producto",
                "Advertencia",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        cartService.addProduct(selectedProduct);
        updateCart();
        JOptionPane.showMessageDialog(this,
            "Producto agregado al carrito",
            "Éxito",
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void removeFromCart() {
        CartItem selectedItem = cartList.getSelectedValue();
        if (selectedItem == null) {
            JOptionPane.showMessageDialog(this,
                "Por favor seleccione un producto del carrito",
                "Advertencia",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        cartService.removeProduct(selectedItem);
        updateCart();
    }

    private void checkout() {
        if (cartService.getCartItems().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "El carrito está vacío",
                "Advertencia",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
            String.format("¿Confirmar compra por un total de $%.2f?", cartService.getTotal()),
            "Confirmar Compra",
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            sendWhatsAppMessage();
            cartService.clearCart();
            updateCart();
        }
    }

    private void sendWhatsAppMessage() {
        String phoneNumber = "573213391720"; // Número de WhatsApp
        String message = cartService.getOrderSummary();

        try {
            String encodedMessage = URLEncoder.encode(message, StandardCharsets.UTF_8);
            String url = "https://wa.me/" + phoneNumber + "?text=" + encodedMessage;

            // Abrir WhatsApp en el navegador
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.BROWSE)) {
                desktop.browse(new java.net.URI(url));
                JOptionPane.showMessageDialog(this,
                    "Se abrirá WhatsApp para confirmar su orden",
                    "Compra Exitosa",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error al abrir WhatsApp: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void logout() {
        dispose();
        new LoginFrame(new org.example.service.UserService()).setVisible(true);
    }

    // Renderer personalizado para mostrar productos con imagen
    private class ProductCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value,
                int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            setFont(new Font("Segoe UI", Font.PLAIN, 12));

            if (value instanceof Product) {
                Product product = (Product) value;
                setText(String.format("<html><b>%s</b><br/>$%.2f</html>",
                    product.getName(), product.getPrice()));

                // Cargar imagen si existe
                if (product.getImagePath() != null && !product.getImagePath().isEmpty()) {
                    File imageFile = new File(product.getImagePath());
                    if (imageFile.exists()) {
                        ImageIcon icon = new ImageIcon(product.getImagePath());
                        Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                        setIcon(new ImageIcon(img));
                    }
                }

                setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            }

            return this;
        }
    }
}
