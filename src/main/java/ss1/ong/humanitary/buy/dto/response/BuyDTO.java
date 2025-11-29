package ss1.ong.humanitary.buy.dto.response;

import lombok.Value;

@Value
public class BuyDTO {
    Integer id;
    Integer quantity;
    Double totalCost;
}
