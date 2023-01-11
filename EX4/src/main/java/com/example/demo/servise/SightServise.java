package com.example.demo.servise;
import com.example.demo.sighter.Sight;
import com.example.demo.Repository.SightRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.List;

@Service
public class SightServise {

    @Autowired
    public SightRepository repository;

    public List<Sight> getWhere(String zone){
        return repository.findByZoneLike(zone);
    }

    public List<Sight> getAllWhere(){
        return repository.findAll();
    }

    public Sight createWhere(Sight request){
        Sight where = new Sight();
        where.setsightName(request.getsightName());
        where.setzone(request.getzone());
        where.setcategory(request.getcategory());
        where.setphotoUrl(request.getphotoUrl());
        where.setdescription(request.getdescription());
        where.setaddress(request.getaddress());
        return repository.insert(where);
    }


    public void deleteWhere(String zone){
        repository.findByZoneLike(zone);
    }

}