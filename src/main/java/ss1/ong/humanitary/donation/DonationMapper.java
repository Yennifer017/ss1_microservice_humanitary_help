package ss1.ong.humanitary.donation;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ss1.ong.humanitary.donation.dto.request.CreateDonationDTO;
import ss1.ong.humanitary.donation.dto.response.DonationDTO;

import java.util.List;

@Mapper(componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.SETTER_PREFERRED,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface DonationMapper {
    public Donation createDonationDtoToDonation(CreateDonationDTO createDonationDTO);
    public DonationDTO donationToDonationDto(Donation donation);
    public List<DonationDTO> donationToDonationDto(List<Donation> donations);
}
