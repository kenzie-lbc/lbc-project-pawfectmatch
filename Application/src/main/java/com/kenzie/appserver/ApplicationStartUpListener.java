package com.kenzie.appserver;

import com.kenzie.appserver.repositories.model.Pet;
import com.kenzie.appserver.service.PetService;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;


@Component
public class ApplicationStartUpListener {

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // Perform any application start-up tasks
//        PetService petService = event.getApplicationContext().getBean(PetService.class);
//        ConcurrentLinkedQueue pets = event.getApplicationContext().getBean(ConcurrentLinkedQueue.class);
//        List<Pet> petList = petService.getAllPets();
//        pets.addAll(petList);
    }
}
