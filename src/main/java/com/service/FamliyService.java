package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Famliy;
import com.repository.FamliyRepository;

@Service
public class FamliyService {

    @Autowired
    private FamliyRepository repo;

    public void saveFamliy(Famliy famliy) {
        repo.save(famliy);
    }

    public Famliy getFamliy(Long id){
        return repo.findById(id).get();
    }

    public Famliy getLastFamliy() {
        return repo.findTopByOrderByIdDesc();

    }

    public Famliy familyExist(String familyName) {
        return repo.findByFamliyName(familyName);
    }
}
