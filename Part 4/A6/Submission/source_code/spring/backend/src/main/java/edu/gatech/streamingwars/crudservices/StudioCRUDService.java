package edu.gatech.streamingwars.crudservices;

import edu.gatech.streamingwars.repositories.StudioRepository;
import edu.gatech.streamingwars.models.Studio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudioCRUDService {

    @Autowired
    private StudioRepository studioRepository;

    // Studio
    public Studio getStudio(Long id) {
        return studioRepository.findById(id).orElse(null);
    }
    public List<Studio> getStudios() {
        return studioRepository.findAll();
    }

    public Studio findByShortName(String shortName) {
        return studioRepository.findByShortName(shortName);
    }

    public Studio saveStudio(Studio studio) {
        Studio found = studioRepository.findByShortName(studio.getShortName());
        if (found != null) {
            return found;
        }
        return studioRepository.save(studio);
    }

    public Studio updateStudio(Long id, Studio studio) {
        Studio found = studioRepository.findById(id).orElse(null);
        if (found == null) {
            studio.setId(id);
            return studioRepository.save(studio);
        }
        //update studio attributes
        found.setShortName(studio.getShortName());
        found.setLongName(studio.getLongName());
        studioRepository.save(found);
        return found;
    }

    public void deleteStudio(Long id) {
        studioRepository.deleteById(id);
    }

}
