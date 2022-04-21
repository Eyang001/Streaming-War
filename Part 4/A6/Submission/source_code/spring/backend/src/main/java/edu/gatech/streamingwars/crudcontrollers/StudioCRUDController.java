package edu.gatech.streamingwars.crudcontrollers;

import edu.gatech.streamingwars.crudservices.StudioCRUDService;
import edu.gatech.streamingwars.models.Studio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crud/studio")
@CrossOrigin(origins = "http://localhost:3001")
public class StudioCRUDController {

    @Autowired
    private StudioCRUDService studioCRUDService;

    // Studio
    @PostMapping
    Studio createStudio(@RequestBody Studio studio) {
        return studioCRUDService.saveStudio(studio);
    }

    @GetMapping
    List<Studio> allStudios() {
        return studioCRUDService.getStudios();
    }

    @GetMapping("/single")
    Studio getStudioByShortName(@RequestParam String shortName) {
        Studio studio = studioCRUDService.findByShortName(shortName);
        return studioCRUDService.getStudio(studio.getId());
    }

    @GetMapping("/{id}")
    Studio getStudio(@PathVariable Long id) {
        return studioCRUDService.getStudio(id);
    }

    @PutMapping("/{id}")
    Studio saveStudio(@PathVariable Long id, @RequestBody Studio studio) {
        return studioCRUDService.updateStudio(id, studio);
    }

    @DeleteMapping("/{id}")
    void deleteStudio(@PathVariable Long id) {
        studioCRUDService.deleteStudio(id);
    }

}
