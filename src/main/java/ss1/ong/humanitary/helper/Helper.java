package ss1.ong.humanitary.helper;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import ss1.ong.humanitary.catastrophe.Catastrophe;
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
public class Helper extends Auditor {

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 500)
    private String description;

    @Column(nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn
    private Catastrophe catastrophe;

}
