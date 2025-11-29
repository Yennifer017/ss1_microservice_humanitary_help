package ss1.ong.humanitary.buy;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ss1.ong.humanitary.buy.dto.request.CreateBuyDTO;
import ss1.ong.humanitary.buy.dto.response.BuyDTO;
import ss1.ong.humanitary.buy.dto.response.MinBuyDTO;
import ss1.ong.humanitary.buy.dto.response.OwnedBuyDTO;

import java.util.List;

@Mapper(componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.SETTER_PREFERRED,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface BuyMapper {
    public OwnedBuyDTO buyToOwnedBuyDto(Buy buy);
    public List<OwnedBuyDTO> buyToOwnedBuyDto(List<Buy> buys);

    public BuyDTO buyToBuyDto(Buy buy);
    public List<BuyDTO> buyToBuyDto(List<Buy> buys);

    public Buy createBuyDtoToBuy(CreateBuyDTO createBuyDTO);

    public MinBuyDTO buyToMinBuyDto(Buy buy);
}
