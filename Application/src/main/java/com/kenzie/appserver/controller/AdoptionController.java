//
////package com.kenzie.appserver.controller;
////
////import com.kenzie.appserver.controller.model.AdoptionCreateRequest;
////import com.kenzie.appserver.controller.model.AdoptionResponse;
////import com.kenzie.appserver.repositories.model.AdoptedPet;
////import com.kenzie.appserver.repositories.model.Pet;
////import com.kenzie.appserver.repositories.model.User;
////import com.kenzie.appserver.service.AdoptionService;
////import com.kenzie.appserver.service.PetService;
////import org.springframework.http.ResponseEntity;
////import org.springframework.web.bind.annotation.*;
////
////import java.util.ArrayList;
////import java.util.List;
////
////@RestController
////@RequestMapping("/adoptedpets")
////public class AdoptionController {
////    private AdoptionService adoptionService;
////    private PetService petService;
////
////
////    AdoptionController(AdoptionService adoptionService, PetService petService) {
////        this.adoptionService = adoptionService;
////        this.petService = petService;
////    }
////
////
////    @PostMapping
////    public ResponseEntity<AdoptionResponse> adoptPet(
////            @RequestBody AdoptionCreateRequest adoptionCreateRequest) {
////
////        // Add your code here
////        //Call the purchasedTicketService.purchaseTicket method with the ticket id and price paid from the request.
////        AdoptedPet pet = adoptionService.adoptPet(adoptionCreateRequest.getId(), adoptionCreateRequest.getUsername, adoptionCreateRequest.getDateOfAdoption);
////
////        //convert the resulting PurchasedTicket into a PurchasedTicketResponse
////        AdoptionResponse adoptionResponse = createAdoptionResponse(pet);
////
////        // Return your ReservedTicketResponse instead of null
////        return ResponseEntity.ok(adoptionResponse);
////
////    }
////
////    @GetMapping
////    public ResponseEntity<List<AdoptionResponse>> getAllAdoptedPets() {
////        List<Pet> pets = petService.findAllPets();
////
////        if (pets == null ||  pets.isEmpty()) {
////            return ResponseEntity.noContent().build();
////        }
////
////        List<AdoptionResponse> response = new ArrayList<>();
////        for (Pet pet : pets) {
////            response.add(this.createAdoptionResponse(pet));
////        }
////
////        return ResponseEntity.ok(response);
////    }
////
////    private AdoptionResponse createAdoptionResponse(Pet adoptedPet) {
////        AdoptionResponse adoptionResponse = new AdoptionResponse();
////        adoptionResponse.setPetId(adoptedPet.getId());
////
////        return adoptionResponse;
////    }
////
////
////}
////
//package com.kenzie.appserver.controller;
//
//import com.kenzie.appserver.controller.model.AdoptionCreateRequest;
//import com.kenzie.appserver.controller.model.AdoptionResponse;
//import com.kenzie.appserver.repositories.model.AdoptedPet;
//import com.kenzie.appserver.repositories.model.Pet;
//import com.kenzie.appserver.repositories.model.User;
//import com.kenzie.appserver.service.AdoptionService;
//import com.kenzie.appserver.service.PetService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//@RequestMapping("/adoptedpets")
//public class AdoptionController {
//    private AdoptionService adoptionService;
//    private PetService petService;
//
//
//    AdoptionController(AdoptionService adoptionService, PetService petService) {
//        this.adoptionService = adoptionService;
//        this.petService = petService;
//    }
//
//
//   /* @PostMapping
//    public ResponseEntity<AdoptionResponse> adoptPet(
//            @RequestBody AdoptionCreateRequest adoptionCreateRequest) {
//
//        // Add your code here
//        //Call the purchasedTicketService.purchaseTicket method with the ticket id and price paid from the request.
//        //AdoptedPet pet = adoptionService.adoptPet(adoptionCreateRequest.getId(), adoptionCreateRequest.getUsername, adoptionCreateRequest.getDateOfAdoption);
//
//        //convert the resulting PurchasedTicket into a PurchasedTicketResponse
//        //AdoptionResponse adoptionResponse = createAdoptionResponse(pet);
//
//        // Return your ReservedTicketResponse instead of null
//        //return ResponseEntity.ok(adoptionResponse);
//
//    }
//
//    @GetMapping
//    public ResponseEntity<List<AdoptionResponse>> getAllAdoptedPets() {
//        List<Pet> pets = petService.findAllPets();
//
//        if (pets == null ||  pets.isEmpty()) {
//            return ResponseEntity.noContent().build();
//        }
//
//        List<AdoptionResponse> response = new ArrayList<>();
//        for (Pet pet : pets) {
//            response.add(this.createAdoptionResponse(pet));
//        }
//
//        return ResponseEntity.ok(response);
//    }
//
//    private AdoptionResponse createAdoptionResponse(Pet adoptedPet) {
//        AdoptionResponse adoptionResponse = new AdoptionResponse();
//        adoptionResponse.setPetId(adoptedPet.getId());
//
//        return adoptionResponse;
//    }*/
//
//
//}
//

package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.AdoptionCreateRequest;
import com.kenzie.appserver.controller.model.AdoptionResponse;
import com.kenzie.appserver.repositories.model.AdoptedPet;
import com.kenzie.appserver.repositories.model.Pet;
import com.kenzie.appserver.repositories.model.User;
//import com.kenzie.appserver.service.AdoptionService;
import com.kenzie.appserver.service.PetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//@RestController
//@RequestMapping("/adoptedpets")
//public class AdoptionController {
   // private AdoptionService adoptionService;
   // private PetService petService;
//
//
//    AdoptionController(AdoptionService adoptionService, PetService petService) {
//        this.adoptionService = adoptionService;
//        this.petService = petService;
//    }


   /* @PostMapping
    public ResponseEntity<AdoptionResponse> adoptPet(
            @RequestBody AdoptionCreateRequest adoptionCreateRequest) {

        // Add your code here
        //Call the purchasedTicketService.purchaseTicket method with the ticket id and price paid from the request.
        //AdoptedPet pet = adoptionService.adoptPet(adoptionCreateRequest.getId(), adoptionCreateRequest.getUsername, adoptionCreateRequest.getDateOfAdoption);

        //convert the resulting PurchasedTicket into a PurchasedTicketResponse
        //AdoptionResponse adoptionResponse = createAdoptionResponse(pet);

        // Return your ReservedTicketResponse instead of null
        //return ResponseEntity.ok(adoptionResponse);

    }

    @GetMapping
    public ResponseEntity<List<AdoptionResponse>> getAllAdoptedPets() {
        List<Pet> pets = petService.findAllPets();

        if (pets == null ||  pets.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<AdoptionResponse> response = new ArrayList<>();
        for (Pet pet : pets) {
            response.add(this.createAdoptionResponse(pet));
        }

        return ResponseEntity.ok(response);
    }

    private AdoptionResponse createAdoptionResponse(Pet adoptedPet) {
        AdoptionResponse adoptionResponse = new AdoptionResponse();
        adoptionResponse.setPetId(adoptedPet.getId());

        return adoptionResponse;
    }*/


//}


