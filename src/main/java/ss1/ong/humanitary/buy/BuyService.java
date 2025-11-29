package ss1.ong.humanitary.buy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ss1.ong.humanitary.auth.users.AppUser;
import ss1.ong.humanitary.auth.users.AppUserService;
import ss1.ong.humanitary.buy.dto.request.CreateBuyDTO;
import ss1.ong.humanitary.buy.dto.response.BuyDTO;
import ss1.ong.humanitary.buy.dto.response.OwnedBuyDTO;
import ss1.ong.humanitary.common.exceptions.NotFoundException;
import ss1.ong.humanitary.common.utils.MoneyUtil;
import ss1.ong.humanitary.helper.Helper;
import ss1.ong.humanitary.helper.HelperService;

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
public class BuyService {

    private final BuyRepository buyRepository;
    private final BuyMapper buyMapper;

    private final AppUserService appUserService;
    private final HelperService helperService;

    public Buy create(CreateBuyDTO createBuyDTO) throws NotFoundException {
        AppUser appUser = appUserService.getProfile();
        Helper helper = helperService.getById(createBuyDTO.getHelperId());

        Buy buy = buyMapper.createBuyDtoToBuy(createBuyDTO);
        buy.setAppUser(appUser);
        buy.setHelper(helper);
        double total = MoneyUtil.round(helper.getPrice() * createBuyDTO.getQuantity());
        buy.setTotalCost(total);
        return buyRepository.save(buy);
    }

    public List<BuyDTO> getAllFromHelper(Integer helperId){
        List<Buy> buys = buyRepository.findByHelperId(helperId);
        return buyMapper.buyToBuyDto(buys);
    }

    public List<OwnedBuyDTO> getAllOwned() throws NotFoundException {
        AppUser appUser = appUserService.getProfile();
        List<Buy> buys = buyRepository.findByAppUserId(appUser.getId());
        return buyMapper.buyToOwnedBuyDto(buys);
    }

}
