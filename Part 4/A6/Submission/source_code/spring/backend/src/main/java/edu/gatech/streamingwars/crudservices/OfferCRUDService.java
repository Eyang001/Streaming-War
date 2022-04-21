package edu.gatech.streamingwars.crudservices;

import edu.gatech.streamingwars.repositories.OfferRepository;
import edu.gatech.streamingwars.models.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferCRUDService {

    @Autowired
    private OfferRepository offerRepository;

    // Offer
    public Offer getOffer(Long id) {
        return offerRepository.findById(id).orElse(null);
    }

    public List<Offer> getOffers() {
        return offerRepository.findAll();
    }

    public Offer findByStreamingServiceIdAndEventId(long streamingServiceId, long eventId) {
        return offerRepository.findByStreamingServiceIdAndEventId(streamingServiceId, eventId);
    }

    public Offer findByStreamingServiceNameAndEventNameAndYearProduced(String streamingServiceName, String eventName, String yearProduced) {
        return offerRepository.findByStreamingServiceNameAndEventNameAndYearProduced(streamingServiceName,
                eventName,
                yearProduced);
    }

    public List<Offer> findByStreamingServiceShortName(String streamingServiceShortName) {
        return offerRepository.findByStreamingServiceShortName(streamingServiceShortName);
    }

    public Offer saveOffer(Offer offer) {
        Offer found = offerRepository.findByStreamingServiceIdAndEventId(offer.getStreamingServiceId(), offer.getEventId());
        if (found != null) {
            return found;
        }
        return offerRepository.save(offer);
    }

    public Offer updateOffer(Long id, Offer offer) {
        Offer found = offerRepository.findById(id).orElse(null);
        if (found == null) {
            offer.setId(id);
            return offerRepository.save(offer);
        }
        //update offer attributes
        found.setStreamingServiceId(offer.getStreamingServiceId());
        found.setEventId(offer.getEventId());
        found.setPrice(offer.getPrice());
        offerRepository.save(found);
        return found;
    }

    public void deleteOffer(Long id) {
        offerRepository.deleteById(id);
    }

    public void deleteAllInBatch() {
        offerRepository.deleteAllInBatch();
    }
}
