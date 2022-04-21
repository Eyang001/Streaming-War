package edu.gatech.streamingwars.crudservices;

import edu.gatech.streamingwars.repositories.DemographicGroupRepository;
import edu.gatech.streamingwars.models.DemographicGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemographicGroupCRUDService {

    @Autowired
    private DemographicGroupRepository demographicGroupRepository;

    //DemographicGroupCRUD
    public DemographicGroup getDemographicGroup(Long id) {
        return demographicGroupRepository.findById(id).orElse(null);
    }
    public List<DemographicGroup> getDemographicGroups() {
        return demographicGroupRepository.findAll();
    }

    public DemographicGroup findByShortName(String shortName) {
        return demographicGroupRepository.findByShortName(shortName);
    }

    public DemographicGroup saveDemographicGroup(DemographicGroup demographicGroup) {
        DemographicGroup found = demographicGroupRepository.findByShortName(demographicGroup.getShortName());
        if (found != null) {
            return found;
        }
        return demographicGroupRepository.save(demographicGroup);
    }

    public DemographicGroup updateDemographicGroup(Long id, DemographicGroup demographicGroup) {
        DemographicGroup found = demographicGroupRepository.findById(id).orElse(null);
        if (found == null) {
            demographicGroup.setId(id);
            return demographicGroupRepository.save(demographicGroup);
        }
        //update demographicGroup attributes
        found.setShortName(demographicGroup.getShortName());
        found.setLongName(demographicGroup.getLongName());
        found.setNumber_of_accounts(demographicGroup.getNumber_of_accounts());
        demographicGroupRepository.save(found);
        return found;
    }

    public void deleteDemographicGroup(Long id) {
        demographicGroupRepository.deleteById(id);
    }


}
