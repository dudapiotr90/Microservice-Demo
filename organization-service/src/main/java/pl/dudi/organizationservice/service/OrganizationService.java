package pl.dudi.organizationservice.service;

import pl.dudi.organizationservice.dto.OrganizationDto;

public interface OrganizationService {

    OrganizationDto saveOrganization(OrganizationDto organizationDto);
    OrganizationDto getOrganizationByCode(String organizationCode);
}
