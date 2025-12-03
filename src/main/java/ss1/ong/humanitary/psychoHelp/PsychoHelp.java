package ss1.ong.humanitary.psychoHelp;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import ss1.ong.humanitary.auth.users.AppUser;
import ss1.ong.humanitary.common.models.entities.Auditor;
import ss1.ong.humanitary.psychoHelp.enums.PsychoHelpStatusEnum;

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
public class PsychoHelp extends Auditor {

    @Column(nullable = true, length = 200)
    private String notes;

    @Column(nullable = false)
    private Integer progress;

    @ManyToOne
    private AppUser patient;

    @ManyToOne
    private AppUser currentPsycho;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PsychoHelpStatusEnum status;


}
