
package ie.project.inventoryordermanagement.service;

import ie.project.inventoryordermanagement.entity.Category;
import ie.project.inventoryordermanagement.repository.CategoryRepository;
import ie.project.inventoryordermanagement.exception.DuplicateResourceException;

import org.springframework.stereotype.Service;
import ie.project.inventoryordermanagement.exception.ResourceNotFoundException;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Get all categories
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    // Get category by ID
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category not found with ID: " + id));
    }

    // Create category
    public Category createCategory(Category category) {

        if (categoryRepository.existsByName(category.getName())) {
            throw new DuplicateResourceException(
                    "A category with this name already exists.");
        }

        return categoryRepository.save(category);
    }

    // Update category
    public Category updateCategory(Long id, Category updatedCategory) {

        Category existingCategory = getCategoryById(id);

        if (!existingCategory.getName().equalsIgnoreCase(updatedCategory.getName())
                && categoryRepository.existsByName(updatedCategory.getName())) {

            throw new DuplicateResourceException(
                    "A category with this name already exists.");
        }

        existingCategory.setName(updatedCategory.getName());
        existingCategory.setDescription(updatedCategory.getDescription());

        return categoryRepository.save(existingCategory);
    }

    // Delete category
    public void deleteCategory(Long id) {
        Category category = getCategoryById(id);
        categoryRepository.delete(category);
    }
}
