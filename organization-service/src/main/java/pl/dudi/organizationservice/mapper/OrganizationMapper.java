package pl.dudi.organizationservice.mapper;

import org.mapstruct.Mapper;
import pl.dudi.organizationservice.dto.OrganizationDto;
import pl.dudi.organizationservice.entity.Organization;

@Mapper(componentModel = "spring")
public interface OrganizationMapper {

    Organization mapToOrganization(OrganizationDto organizationDto);
    OrganizationDto mapToOrganizationDto(Organization organization);
}
