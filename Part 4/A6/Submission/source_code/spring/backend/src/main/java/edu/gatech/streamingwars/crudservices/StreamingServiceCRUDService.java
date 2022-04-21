package edu.gatech.streamingwars.crudservices;

import edu.gatech.streamingwars.repositories.StreamingServiceRepository;
import edu.gatech.streamingwars.models.StreamingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StreamingServiceCRUDService {

    @Autowired
    private StreamingServiceRepository streamingServiceRepository;

    // StreamingService
    public StreamingService getStreamingService(Long id) {
        return streamingServiceRepository.findById(id).orElse(null);
    }
    public List<StreamingService> getStreamingServices() {
        return streamingServiceRepository.findAll();
    }

    public StreamingService findByShortName(String shortName) {
        return streamingServiceRepository.findByShortName(shortName);
    }

    public StreamingService saveStreamingService(StreamingService streamingService) {
        StreamingService found = streamingServiceRepository.findByShortName(streamingService.getShortName());
        if (found != null) {
            return found;
        }
        return streamingServiceRepository.save(streamingService);
    }

    public StreamingService updateStreamingService(Long id, StreamingService streamingService) {
        StreamingService found = streamingServiceRepository.findById(id).orElse(null);
        if (found == null) {
            streamingService.setId(id);
            return streamingServiceRepository.save(streamingService);
        }
        //update StreamingService attributes
        found.setShortName(streamingService.getShortName());
        found.setLongName(streamingService.getLongName());
        found.setSubscriptionPrice(streamingService.getSubscriptionPrice());
        streamingServiceRepository.save(found);
        return found;
    }

    public void deleteStreamingService(Long id) {
        streamingServiceRepository.deleteById(id);
    }
}
