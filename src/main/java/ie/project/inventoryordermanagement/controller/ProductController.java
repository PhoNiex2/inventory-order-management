
package ie.project.inventoryordermanagement.controller;

import ie.project.inventoryordermanagement.entity.Product;
import ie.project.inventoryordermanagement.service.ProductService;
import ie.project.inventoryordermanagement.service.CategoryService;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(
            ProductService productService,
            CategoryService categoryService) {

        this.productService = productService;
        this.categoryService = categoryService;
    }

    // Display all products
    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products/list";
    }

    // Show create product form
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "products/form";
    }

    // Save new product
    @PostMapping
    public String createProduct(
            @Valid @ModelAttribute("product") Product product,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());
            return "products/form";
        }

        productService.createProduct(product);
        return "redirect:/products";
    }

    // Show edit product form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("categories", categoryService.getAllCategories());
        return "products/form";
    }

    // Update product
    @PostMapping("/update/{id}")
    public String updateProduct(
            @PathVariable Long id,
            @Valid @ModelAttribute("product") Product product,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());
            return "products/form";
        }

        productService.updateProduct(id, product);
        return "redirect:/products";
    }

    // Delete product
    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
