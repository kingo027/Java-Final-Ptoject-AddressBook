
package ca.lambtoncollege.addressbook.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @NotBlank(message = "Must enter your Name")
    @Size(min = 2, max = 40)
    private String name;
    
    @NotBlank(message = "Must enter your Email")
    @Email
    private String email;
    
    @NotBlank(message = "Must enter your Phone Number")
    @Pattern(regexp="\\(\\d{3}\\)\\d{3}-\\d{4}") 
    private String phone;
    
}
