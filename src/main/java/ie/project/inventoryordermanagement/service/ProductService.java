
package ie.project.inventoryordermanagement.service;

import ie.project.inventoryordermanagement.entity.Product;
import ie.project.inventoryordermanagement.entity.Category;
import ie.project.inventoryordermanagement.repository.ProductRepository;
import ie.project.inventoryordermanagement.repository.CategoryRepository;
import ie.project.inventoryordermanagement.exception.DuplicateResourceException;
import ie.project.inventoryordermanagement.exception.ResourceNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(
            ProductRepository productRepository,
            CategoryRepository categoryRepository) {

        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    // Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get product by ID
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product not found with ID: " + id));
    }

    // Create product
    public Product createProduct(Product product) {

        if (productRepository.existsBySku(product.getSku())) {
            throw new DuplicateResourceException(
                    "A product with this SKU already exists.");
        }

        Long categoryId = product.getCategory().getId();

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category not found with ID: " + categoryId));

        product.setCategory(category);

        return productRepository.save(product);
    }

    // Update product
    public Product updateProduct(Long id, Product updatedProduct) {

        Product existingProduct = getProductById(id);

        // Check whether the new SKU belongs to another product
        if (!existingProduct.getSku().equalsIgnoreCase(updatedProduct.getSku())
                && productRepository.existsBySku(updatedProduct.getSku())) {

            throw new DuplicateResourceException(
                    "A product with this SKU already exists.");
        }

        // Verify that the selected category exists
        Long categoryId = updatedProduct.getCategory().getId();

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category not found with ID: " + categoryId));

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setSku(updatedProduct.getSku());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setQuantity(updatedProduct.getQuantity());
        existingProduct.setReorderLevel(updatedProduct.getReorderLevel());
        existingProduct.setCategory(category);

        return productRepository.save(existingProduct);
    }

    // Delete product
    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }


}
