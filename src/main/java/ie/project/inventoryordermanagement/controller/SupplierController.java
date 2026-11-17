
package ie.project.inventoryordermanagement.controller;

import ie.project.inventoryordermanagement.entity.Supplier;
import ie.project.inventoryordermanagement.service.SupplierService;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    // Display all suppliers
    @GetMapping
    public String listSuppliers(Model model) {
        model.addAttribute("suppliers", supplierService.getAllSuppliers());
        return "suppliers/list";
    }

    // Show create supplier form
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("supplier", new Supplier());
        return "suppliers/form";
    }

    // Save new supplier
    @PostMapping
    public String createSupplier(
            @Valid @ModelAttribute("supplier") Supplier supplier,
            BindingResult result) {

        if (result.hasErrors()) {
            return "suppliers/form";
        }

        supplierService.createSupplier(supplier);
        return "redirect:/suppliers";
    }

    // Show edit supplier form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("supplier", supplierService.getSupplierById(id));
        return "suppliers/form";
    }

    // Update supplier
    @PostMapping("/update/{id}")
    public String updateSupplier(
            @PathVariable Long id,
            @Valid @ModelAttribute("supplier") Supplier supplier,
            BindingResult result) {

        if (result.hasErrors()) {
            return "suppliers/form";
        }

        supplierService.updateSupplier(id, supplier);
        return "redirect:/suppliers";
    }

    // Delete supplier
    @PostMapping("/delete/{id}")
    public String deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
        return "redirect:/suppliers";
    }
}
