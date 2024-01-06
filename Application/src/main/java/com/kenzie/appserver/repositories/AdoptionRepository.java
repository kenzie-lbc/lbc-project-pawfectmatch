package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.AdoptionRecord;
import com.kenzie.appserver.repositories.model.ExampleRecord;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface AdoptionRepository extends CrudRepository<ExampleRecord, String> {
    AdoptionRecord saveRecord(AdoptionRecord adoptionRecord);

}
