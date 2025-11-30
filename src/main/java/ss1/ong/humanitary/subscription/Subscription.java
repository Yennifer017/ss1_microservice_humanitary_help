package ss1.ong.humanitary.subscription;

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
import ss1.ong.humanitary.event.Event;

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
public class Subscription extends Auditor {

    @ManyToOne
    @JoinColumn(nullable = false)
    private AppUser appUser;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Event event;

}
