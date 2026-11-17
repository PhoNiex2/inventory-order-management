
package ie.project.inventoryordermanagement.repository;

import ie.project.inventoryordermanagement.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Check whether a category name already exists
    boolean existsByName(String name);

}
