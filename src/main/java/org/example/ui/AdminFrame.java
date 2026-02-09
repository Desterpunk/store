package org.example.ui;

import org.example.model.Product;
import org.example.model.User;
import org.example.service.ProductService;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class AdminFrame extends JFrame {
    private User user;
    private ProductService productService;
    private DefaultListModel<Product> productListModel;
    private JList<Product> productList;

    public AdminFrame(User user) {
        this.user = user;
        this.productService = new ProductService();
        initComponents();
        loadProducts();
    }

    private void initComponents() {
        setTitle("Store - Panel de Administrador");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel superior
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Bienvenido Admin: " + user.getUsername());
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        topPanel.add(welcomeLabel, BorderLayout.WEST);

        JButton logoutButton = new JButton("Cerrar Sesión");
        logoutButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        logoutButton.addActionListener(e -> logout());
        topPanel.add(logoutButton, BorderLayout.EAST);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Panel central - Lista de productos
        productListModel = new DefaultListModel<>();
        productList = new JList<>(productListModel);
        productList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        productList.setCellRenderer(new ProductCellRenderer());
        productList.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(productList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Productos"));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Panel de botones
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));

        JButton addButton = new JButton("Agregar Producto");
        JButton editButton = new JButton("Editar Producto");
        JButton deleteButton = new JButton("Eliminar Producto");

        addButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        editButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        deleteButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        addButton.addActionListener(e -> addProduct());
        editButton.addActionListener(e -> editProduct());
        deleteButton.addActionListener(e -> deleteProduct());

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void loadProducts() {
        productListModel.clear();
        for (Product product : productService.getAllProducts()) {
            productListModel.addElement(product);
        }
    }

    private void addProduct() {
        ProductDialog dialog = new ProductDialog(this, null);
        dialog.setVisible(true);

        Product newProduct = dialog.getProduct();
        if (newProduct != null) {
            productService.addProduct(newProduct);
            loadProducts();
        }
    }

    private void editProduct() {
        Product selectedProduct = productList.getSelectedValue();
        if (selectedProduct == null) {
            JOptionPane.showMessageDialog(this,
                "Por favor seleccione un producto",
                "Advertencia",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        ProductDialog dialog = new ProductDialog(this, selectedProduct);
        dialog.setVisible(true);

        Product updatedProduct = dialog.getProduct();
        if (updatedProduct != null) {
            productService.updateProduct(selectedProduct, updatedProduct);
            loadProducts();
        }
    }

    private void deleteProduct() {
        Product selectedProduct = productList.getSelectedValue();
        if (selectedProduct == null) {
            JOptionPane.showMessageDialog(this,
                "Por favor seleccione un producto",
                "Advertencia",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
            "¿Está seguro de eliminar el producto: " + selectedProduct.getName() + "?",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            productService.removeProduct(selectedProduct);
            loadProducts();
        }
    }

    private void logout() {
        dispose();
        new LoginFrame(new org.example.service.UserService()).setVisible(true);
    }

    // Renderer personalizado para mostrar productos
    private class ProductCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value,
                int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            setFont(new Font("Segoe UI", Font.PLAIN, 12));

            if (value instanceof Product) {
                Product product = (Product) value;
                setText(String.format("%s - $%.2f", product.getName(), product.getPrice()));

                // Cargar imagen si existe
                if (product.getImagePath() != null && !product.getImagePath().isEmpty()) {
                    File imageFile = new File(product.getImagePath());
                    if (imageFile.exists()) {
                        ImageIcon icon = new ImageIcon(product.getImagePath());
                        Image img = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                        setIcon(new ImageIcon(img));
                    }
                }
            }

            return this;
        }
    }
}

// Diálogo para agregar/editar productos
class ProductDialog extends JDialog {
    private JTextField nameField;
    private JTextField priceField;
    private JTextField imagePathField;
    private JButton browseButton;
    private Product product;
    private boolean confirmed = false;

    public ProductDialog(Frame parent, Product product) {
        super(parent, product == null ? "Agregar Producto" : "Editar Producto", true);
        this.product = product;
        initComponents();
    }

    private void initComponents() {
        setSize(450, 250);
        setLocationRelativeTo(getParent());
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel de formulario
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        JLabel nameLabel = new JLabel("Nombre:");
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        formPanel.add(nameLabel);
        nameField = new JTextField(product != null ? product.getName() : "");
        nameField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        formPanel.add(nameField);

        JLabel priceLabel = new JLabel("Precio:");
        priceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        formPanel.add(priceLabel);
        priceField = new JTextField(product != null ? String.valueOf(product.getPrice()) : "");
        priceField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        formPanel.add(priceField);

        JLabel imageLabel = new JLabel("Imagen:");
        imageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        formPanel.add(imageLabel);
        JPanel imagePanel = new JPanel(new BorderLayout(5, 5));
        imagePathField = new JTextField(product != null ? product.getImagePath() : "");
        imagePathField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        imagePathField.setEditable(false);
        browseButton = new JButton("Buscar...");
        browseButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        browseButton.addActionListener(e -> browseImage());
        imagePanel.add(imagePathField, BorderLayout.CENTER);
        imagePanel.add(browseButton, BorderLayout.EAST);
        formPanel.add(imagePanel);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton saveButton = new JButton("Guardar");
        JButton cancelButton = new JButton("Cancelar");

        saveButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        cancelButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        saveButton.addActionListener(e -> save());
        cancelButton.addActionListener(e -> dispose());

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void browseImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter(
            "Imágenes (*.jpg, *.png, *.gif)", "jpg", "png", "gif", "jpeg"));

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            // Copiar imagen a carpeta de recursos del proyecto
            try {
                File imagesDir = new File("images");
                if (!imagesDir.exists()) {
                    imagesDir.mkdirs();
                }

                String extension = selectedFile.getName().substring(selectedFile.getName().lastIndexOf("."));
                File destFile = new File(imagesDir, UUID.randomUUID().toString() + extension);
                Files.copy(selectedFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                imagePathField.setText(destFile.getAbsolutePath());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                    "Error al copiar la imagen: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void save() {
        String name = nameField.getText().trim();
        String priceText = priceField.getText().trim();
        String imagePath = imagePathField.getText().trim();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Por favor ingrese el nombre del producto",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        double price;
        try {
            price = Double.parseDouble(priceText);
            if (price < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "Por favor ingrese un precio válido",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        String id = product != null ? product.getId() : UUID.randomUUID().toString();
        this.product = new Product(id, name, price, imagePath);
        confirmed = true;
        dispose();
    }

    public Product getProduct() {
        return confirmed ? product : null;
    }
}
