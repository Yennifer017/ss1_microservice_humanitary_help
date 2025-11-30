package ss1.ong.humanitary.buy.dto.response;

import lombok.Value;
import ss1.ong.humanitary.auth.users.dto.response.SimpleUserDTO;

@Value
public class BuyDTO {
    Integer id;
    Integer quantity;
    Double totalCost;
    SimpleUserDTO appUser;
}
