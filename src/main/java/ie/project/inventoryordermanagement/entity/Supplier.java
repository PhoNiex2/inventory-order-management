
package ie.project.inventoryordermanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "suppliers")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Supplier name is required")
    @Size(max = 150, message = "Supplier name cannot exceed 150 characters")
    @Column(nullable = false, length = 150)
    private String name;

    @NotBlank(message = "Contact person is required")
    @Size(max = 100, message = "Contact person cannot exceed 100 characters")
    private String contactPerson;

    @NotBlank(message = "Email is required")
    @Email(message = "Please enter a valid email address")
    @Column(nullable = false)
    private String email;

    @NotBlank(message = "Phone number is required")
    @Size(max = 30, message = "Phone number cannot exceed 30 characters")
    private String phone;

    @Size(max = 255, message = "Address cannot exceed 255 characters")
    private String address;

    public Supplier() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
