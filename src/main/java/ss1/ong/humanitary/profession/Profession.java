package ss1.ong.humanitary.profession;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import ss1.ong.humanitary.common.models.entities.Auditor;
import ss1.ong.humanitary.donationUtil.DonationUtil;

/**
 * Usuario interno de la aplicación
 *
 * @author Yennifer de Leon
 * @version 1.0
 * @since 2025-08-28
 */
@Entity
@DynamicUpdate
@SQLDelete(sql = "UPDATE profession SET deleted_at = NOW() WHERE id = ?")
@SQLRestriction("deleted_at IS NULL")
@NoArgsConstructor
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Profession extends Auditor {

    @OneToOne
    @JoinColumn(nullable = false)
    private DonationUtil donationUtil;

    @Column(nullable = false)
    private Boolean forPsycho;

}
