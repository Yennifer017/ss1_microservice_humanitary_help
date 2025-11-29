package ss1.ong.humanitary.donationUtil;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ss1.ong.humanitary.donationUtil.dto.request.CreateDonationUtilDTO;
import ss1.ong.humanitary.donationUtil.dto.response.DonationUtilDTO;

import java.util.List;

@Mapper(componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.SETTER_PREFERRED,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface DonationUtilMapper {
    DonationUtil createDonationUtilDtoToDonationUtil(CreateDonationUtilDTO createDonationUtilDTO);
    DonationUtilDTO donationUtilToDonationUtilDto(DonationUtil donationUtil);
    List<DonationUtilDTO> donationUtilToDonationUtilDto(List<DonationUtil> donationUtils);
}
