package ss1.ong.humanitary.subscription;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ss1.ong.humanitary.catastrophe.CatastropheMapper;
import ss1.ong.humanitary.subscription.dto.response.EventSubscriptionDTO;
import ss1.ong.humanitary.subscription.dto.response.OwnedSubscriptionDTO;

import java.util.List;

@Mapper(componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.SETTER_PREFERRED,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = CatastropheMapper.class
)
public interface SubscriptionMapper {
    public EventSubscriptionDTO subscriptionToEventSubscriptionDTO(Subscription subscription);
    public List<EventSubscriptionDTO> subscriptionToEventSubscriptionDTO(List<Subscription> subscriptions);

    public OwnedSubscriptionDTO subscriptionToOwnedSubscriptionDto(Subscription subscription);
    public List<OwnedSubscriptionDTO> subscriptionToOwnedSubscriptionDto(List<Subscription> subscriptions);
}
