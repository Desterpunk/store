package org.example.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.model.Product;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private static final String DATA_FILE = "products.json";
    private List<Product> products;
    private Gson gson;

    public ProductService() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        products = new ArrayList<>();
        loadProducts();
    }

    public void loadProducts() {
        File file = new File(DATA_FILE);
        if (file.exists()) {
            try (Reader reader = new FileReader(file)) {
                Type listType = new TypeToken<ArrayList<Product>>(){}.getType();
                products = gson.fromJson(reader, listType);
                if (products == null) {
                    products = new ArrayList<>();
                }
            } catch (IOException e) {
                e.printStackTrace();
                products = new ArrayList<>();
            }
        }
    }

    public void saveProducts() {
        try (Writer writer = new FileWriter(DATA_FILE)) {
            gson.toJson(products, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addProduct(Product product) {
        products.add(product);
        saveProducts();
    }

    public void removeProduct(Product product) {
        products.remove(product);
        saveProducts();
    }

    public void updateProduct(Product oldProduct, Product newProduct) {
        int index = products.indexOf(oldProduct);
        if (index != -1) {
            products.set(index, newProduct);
            saveProducts();
        }
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    public Product getProductById(String id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
