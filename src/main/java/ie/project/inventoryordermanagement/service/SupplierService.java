
package ie.project.inventoryordermanagement.service;

import ie.project.inventoryordermanagement.entity.Supplier;
import ie.project.inventoryordermanagement.repository.SupplierRepository;
import ie.project.inventoryordermanagement.exception.DuplicateResourceException;
import ie.project.inventoryordermanagement.exception.ResourceNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    // Get all suppliers
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    // Get supplier by ID
    public Supplier getSupplierById(Long id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Supplier not found with ID: " + id));
    }

    // Create supplier
    public Supplier createSupplier(Supplier supplier) {

        if (supplierRepository.existsByEmail(supplier.getEmail())) {
            throw new DuplicateResourceException(
                    "A supplier with this email already exists.");
        }

        return supplierRepository.save(supplier);
    }

    // Update supplier
    public Supplier updateSupplier(Long id, Supplier updatedSupplier) {

        Supplier existingSupplier = getSupplierById(id);

        if (!existingSupplier.getEmail().equalsIgnoreCase(updatedSupplier.getEmail())
                && supplierRepository.existsByEmail(updatedSupplier.getEmail())) {

            throw new DuplicateResourceException(
                    "A supplier with this email already exists.");
        }

        existingSupplier.setName(updatedSupplier.getName());
        existingSupplier.setContactPerson(updatedSupplier.getContactPerson());
        existingSupplier.setEmail(updatedSupplier.getEmail());
        existingSupplier.setPhone(updatedSupplier.getPhone());
        existingSupplier.setAddress(updatedSupplier.getAddress());

        return supplierRepository.save(existingSupplier);
    }

    // Delete supplier
    public void deleteSupplier(Long id) {
        Supplier supplier = getSupplierById(id);
        supplierRepository.delete(supplier);
    }
}
