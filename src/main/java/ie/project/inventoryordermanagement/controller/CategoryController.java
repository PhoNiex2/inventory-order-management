
package ie.project.inventoryordermanagement.controller;

import ie.project.inventoryordermanagement.entity.Category;
import ie.project.inventoryordermanagement.service.CategoryService;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    // Constructor injection
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Display all categories
    @GetMapping
    public String listCategories(Model model) {

        model.addAttribute("categories",
                categoryService.getAllCategories());

        return "categories/list";
    }

    // Display the create category form
    @GetMapping("/new")
    public String showCreateForm(Model model) {

        model.addAttribute("category", new Category());

        return "categories/form";
    }

    // Save a new category with validation
    @PostMapping
    public String createCategory(
            @Valid @ModelAttribute("category") Category category,
            BindingResult result) {

        if (result.hasErrors()) {
            return "categories/form";
        }

        categoryService.createCategory(category);

        return "redirect:/categories";
    }

    // Display the edit category form
    @GetMapping("/edit/{id}")
    public String showEditForm(
            @PathVariable Long id,
            Model model) {

        model.addAttribute("category",
                categoryService.getCategoryById(id));

        return "categories/form";
    }

    // Update an existing category with validation
    @PostMapping("/update/{id}")
    public String updateCategory(
            @PathVariable Long id,
            @Valid @ModelAttribute("category") Category category,
            BindingResult result) {

        if (result.hasErrors()) {
            return "categories/form";
        }

        categoryService.updateCategory(id, category);

        return "redirect:/categories";
    }

    // Delete a category
    @PostMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {

        categoryService.deleteCategory(id);

        return "redirect:/categories";
    }
}
