package ss1.ong.humanitary.auth.users;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import ss1.ong.humanitary.auth.users.enums.GenreEnum;
import ss1.ong.humanitary.auth.users.enums.RolesEnum;
import ss1.ong.humanitary.common.models.entities.Auditor;

/**
 * Usuario interno de la aplicación
 * 
 * @author Yennifer de Leon
 * @version 1.0
 * @since 2025-08-28
 */
@Entity
@DynamicUpdate
@NoArgsConstructor
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AppUser extends Auditor {

    @Column(nullable = false, unique = true, length = 20)
    private String username;

    @Column(nullable = true, length = 150)
    private String address;

    @Column(nullable = true, length = 50)
    private String phoneNumber;

    @Column(nullable = false)
    private Integer bloodType;
    
    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String lastname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GenreEnum genre;

    @Column(nullable = false, length = 50)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RolesEnum role;
}
