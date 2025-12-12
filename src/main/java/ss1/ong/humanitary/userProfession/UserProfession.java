package ss1.ong.humanitary.userProfession;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import ss1.ong.humanitary.auth.users.AppUser;
import ss1.ong.humanitary.common.models.entities.Auditor;
import ss1.ong.humanitary.profession.Profession;

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
public class UserProfession extends Auditor {

    @ManyToOne
    @JoinColumn
    private AppUser appUser;

    @ManyToOne
    @JoinColumn
    private Profession profession;

    @Column(nullable = false, length = 250)
    private String degreeUrl;

}
