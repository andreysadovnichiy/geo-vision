package com.solveast.muleteer.service.jpa;

import com.solveast.muleteer.model.Muletrack;
import com.solveast.muleteer.repositories.MuleTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class MuleTrackService {
    @Autowired
    MuleTrackRepository muleTrackRepository;

    public void saveAll(final List<Muletrack> cache) {
        muleTrackRepository.save(cache);

//        List<TrackingMode> modes =
//
//        for (int i = 0; i < cache.size(); i++) {
//            cache.get(i);
//        }

        muleTrackRepository.flush();
    }

    public void processDelete(final Set<Muletrack> mulesDeleted) {
        if (!mulesDeleted.isEmpty()) {
            muleTrackRepository.delete(mulesDeleted);
            muleTrackRepository.flush();
            mulesDeleted.clear();
        }
    }
}
