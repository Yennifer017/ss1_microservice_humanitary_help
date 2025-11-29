package ss1.ong.humanitary.event;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import ss1.ong.humanitary.catastrophe.Catastrophe;
import ss1.ong.humanitary.common.models.entities.Auditor;
import ss1.ong.humanitary.event.enums.EventTypeEnum;

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
public class Event extends Auditor {

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = true)
    private LocalDateTime limitDate;

    @Column(nullable = false, length = 500)
    private String details;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventTypeEnum type;

    @ManyToOne
    @JoinColumn
    private Catastrophe catastrophe;

}
