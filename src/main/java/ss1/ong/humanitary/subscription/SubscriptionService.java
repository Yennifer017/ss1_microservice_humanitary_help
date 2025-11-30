package ss1.ong.humanitary.subscription;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ss1.ong.humanitary.auth.users.AppUser;
import ss1.ong.humanitary.auth.users.AppUserService;
import ss1.ong.humanitary.common.exceptions.NotFoundException;
import ss1.ong.humanitary.event.Event;
import ss1.ong.humanitary.event.EventService;
import ss1.ong.humanitary.subscription.dto.response.EventSubscriptionDTO;
import ss1.ong.humanitary.subscription.dto.response.OwnedSubscriptionDTO;

import java.util.List;
/**
 *
 * @author Yennifer de Leon
 * @version 1.0
 * @since 2025-08-28
 */
@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;

    private final EventService eventService;
    private final AppUserService appUserService;

    public Subscription create(Integer eventId) throws NotFoundException {
        AppUser appUser = appUserService.getProfile();
        Event event = eventService.getEventById(eventId);
        return subscriptionRepository.save(new Subscription(appUser, event));
    }

    public List<EventSubscriptionDTO> getByEventId(Integer eventId){
        List<Subscription> subscriptions = subscriptionRepository.findByEventId(eventId);
        return subscriptionMapper.subscriptionToEventSubscriptionDTO(subscriptions);
    }

    public List<OwnedSubscriptionDTO> getOwned() throws NotFoundException {
        AppUser appUser = appUserService.getProfile();
        List<Subscription> subscriptions = subscriptionRepository.findByAppUserId(appUser.getId());
        return subscriptionMapper.subscriptionToOwnedSubscriptionDto(subscriptions);
    }

    public void deleteSubscription(Integer eventId) throws NotFoundException {
        AppUser appUser = appUserService.getProfile();
        Subscription subscription = subscriptionRepository.findByAppUserIdAndEventId(appUser.getId(), eventId)
                .orElseThrow(() -> new NotFoundException("No se encontro una suscripcion relacionada al usuario"));
        subscriptionRepository.deleteById(subscription);
    }

}
