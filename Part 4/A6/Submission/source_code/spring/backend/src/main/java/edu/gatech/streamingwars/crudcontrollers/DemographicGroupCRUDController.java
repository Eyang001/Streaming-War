package edu.gatech.streamingwars.crudcontrollers;

import edu.gatech.streamingwars.crudservices.DemographicGroupCRUDService;
import edu.gatech.streamingwars.models.DemographicGroup;
import edu.gatech.streamingwars.models.WatchRecord;
import edu.gatech.streamingwars.repositories.WatchServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crud/demographicgroup")
@CrossOrigin(origins = "http://localhost:3001")
public class DemographicGroupCRUDController {

    @Autowired
    private DemographicGroupCRUDService demographicGroupCRUDService;

    @Autowired
    private WatchServiceRepository watchServiceRepository;

    // DemographicGroup
    @PostMapping
    DemographicGroup createDemographicGroup(@RequestBody DemographicGroup demographicGroup) {
        return demographicGroupCRUDService.saveDemographicGroup(demographicGroup);
    }

    @GetMapping
    List<DemographicGroup> allDemographicGroups() {
        return demographicGroupCRUDService.getDemographicGroups();
    }

    @GetMapping("/single")
    DemographicGroup getDemographicGroupByShortName(@RequestParam String shortName) {
        DemographicGroup demographicGroup = demographicGroupCRUDService.findByShortName(shortName);
        return demographicGroupCRUDService.getDemographicGroup(demographicGroup.getId());
    }

    @GetMapping("/{id}")
    DemographicGroup getDemographicGroup(@PathVariable Long id) {
        return demographicGroupCRUDService.getDemographicGroup(id);
    }

    //[13] update_demo
    @PutMapping
    DemographicGroup saveDemographicGroup(@RequestBody DemographicGroup demographicGroup) {
        DemographicGroup found = demographicGroupCRUDService.findByShortName(demographicGroup.getShortName());
        if (found == null) { // demo group not exist, invalid
            return null; // TODO: return error message, how to do it?
        }
        List<WatchRecord> watchRecords = watchServiceRepository.findByDemographicGroupShortName(demographicGroup.getShortName());
        if (watchRecords != null && watchRecords.size() != 0) { // has watching record so far, can't update numberOfAccount
            return null; // TODO: return error message, how to do it?
        }
        long id = found.getId();
        return demographicGroupCRUDService.updateDemographicGroup(id, demographicGroup);
    }

    //[13] update_demo
    @PutMapping("/{id}")
    DemographicGroup saveDemographicGroup(@PathVariable Long id, @RequestBody DemographicGroup demographicGroup) {
        DemographicGroup found = demographicGroupCRUDService.findByShortName(demographicGroup.getShortName());
        if (found == null) { // demo group not exist, invalid
            return null; // TODO: return error message, how to do it?
        }
        List<WatchRecord> watchRecords = watchServiceRepository.findByDemographicGroupShortName(demographicGroup.getShortName());
        if (watchRecords != null && watchRecords.size() != 0) { // has watching record so far, can't update numberOfAccount
            return null; // TODO: return error message, how to do it?
        }
        return demographicGroupCRUDService.updateDemographicGroup(id, demographicGroup);
    }

    @DeleteMapping("/{id}")
    void deleteDemographicGroup(@PathVariable Long id) {
        demographicGroupCRUDService.deleteDemographicGroup(id);
    }
}
