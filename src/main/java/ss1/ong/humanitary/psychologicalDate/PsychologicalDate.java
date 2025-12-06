package ss1.ong.humanitary.psychologicalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import ss1.ong.humanitary.auth.users.AppUser;
import ss1.ong.humanitary.common.models.entities.Auditor;
import ss1.ong.humanitary.psychoHelp.PsychoHelp;
import ss1.ong.humanitary.psychologicalDate.enums.PsychoDateStatusEnum;

import java.time.LocalDateTime;

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
public class PsychologicalDate extends Auditor {

    @Column(nullable = false)
    private LocalDateTime init;

    @Column(nullable = false)
    private LocalDateTime endDateTime;

    @Column(nullable = true, length = 100)
    private String urlEvidence;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PsychoDateStatusEnum status;

    @ManyToOne
    @JoinColumn
    private PsychoHelp psychoHelp;

    @ManyToOne
    @JoinColumn
    private AppUser psycho;

    @Column(nullable = true, length = 255)
    private String notes;

}
