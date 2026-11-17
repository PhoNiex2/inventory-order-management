
package ie.project.inventoryordermanagement.repository;

import ie.project.inventoryordermanagement.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    boolean existsByEmail(String email);

}
