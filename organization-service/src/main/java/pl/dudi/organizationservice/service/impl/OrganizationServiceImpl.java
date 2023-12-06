package pl.dudi.organizationservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.dudi.organizationservice.dto.OrganizationDto;
import pl.dudi.organizationservice.entity.Organization;
import pl.dudi.organizationservice.mapper.OrganizationMapper;
import pl.dudi.organizationservice.repository.OrganizationRepository;
import pl.dudi.organizationservice.service.OrganizationService;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationMapper organizationMapper;
    private final OrganizationRepository organizationRepository;


    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
        Organization organizationToSave = organizationMapper.mapToOrganization(organizationDto);
        Organization OrganizationSaved = organizationRepository.save(organizationToSave);
        return organizationMapper.mapToOrganizationDto(OrganizationSaved);
    }

    @Override
    public OrganizationDto getOrganizationByCode(String organizationCode) {
        Organization organization = organizationRepository.findByOrganizationCode(organizationCode);
        return organizationMapper.mapToOrganizationDto(organization);
    }
}
