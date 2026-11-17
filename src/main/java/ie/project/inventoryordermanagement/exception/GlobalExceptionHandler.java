
package ie.project.inventoryordermanagement.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {



    // Handle duplicate resource errors
    @ExceptionHandler(DuplicateResourceException.class)
    public String handleDuplicateResource(
            DuplicateResourceException exception,
            Model model) {

        model.addAttribute("errorMessage", exception.getMessage());

        return "error/duplicate-resource";
    }

    // Handle resource not found errors
    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleResourceNotFound(
            ResourceNotFoundException exception,
            Model model) {

        model.addAttribute("errorMessage", exception.getMessage());

        return "error/resource-not-found";
    }
}
