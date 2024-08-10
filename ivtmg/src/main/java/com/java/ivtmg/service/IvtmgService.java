package com.java.ivtmg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.java.ivtmg.model.Ivtmg;
import com.java.ivtmg.repository.IvtmgRepo;

@Service
public class IvtmgService {
    @Autowired
    private IvtmgRepo ivtmgRepo;

    public Ivtmg addIvtmg(Ivtmg ivtmg) {
        return ivtmgRepo.save(ivtmg);
    }

    public Page<Ivtmg> getAllIvtmgs(Pageable pageable) {
        return ivtmgRepo.findAll(pageable);
    }

    public Ivtmg getIvtmgById(Integer id) {
        return ivtmgRepo.findById(id).orElse(null);
    }

    public Ivtmg updateIvtmg(Ivtmg ivtmg) {
        return ivtmgRepo.save(ivtmg);
    }

    public void deleteIvtmgById(Integer id) {
        ivtmgRepo.deleteById(id);
    }

    public boolean existsById(Integer id) {
        return ivtmgRepo.existsById(id);
    }

    public Page<Ivtmg> findByUsername(String username, Pageable pageable) {
        return ivtmgRepo.findByUsername(username, pageable);
    }
}
