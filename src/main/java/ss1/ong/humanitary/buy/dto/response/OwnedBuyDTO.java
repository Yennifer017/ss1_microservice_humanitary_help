package ss1.ong.humanitary.buy.dto.response;

import lombok.Value;
import ss1.ong.humanitary.helper.dto.response.HelperDTO;

@Value
public class OwnedBuyDTO {
    Integer id;
    HelperDTO helper;
    Integer quantity;
    Double totalCost;
}
