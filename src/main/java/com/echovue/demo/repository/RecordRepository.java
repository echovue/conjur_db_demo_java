package com.echovue.demo.repository;

import com.echovue.demo.model.Record;
import org.springframework.data.repository.CrudRepository;

public interface RecordRepository extends CrudRepository<Record, Integer> {

}
