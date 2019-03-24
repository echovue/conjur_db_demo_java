package com.echovue.demo.controller;

import com.echovue.demo.model.Record;
import com.echovue.demo.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/demo")
public class MainController {
    @Autowired
    private RecordRepository recordRepository;

    @GetMapping(path="/add")
    public @ResponseBody String addNewRecord (
            @RequestParam String value1,
            @RequestParam String value2,
            @RequestParam String value3) {

        Record r = new Record();
        r.setValue1(value1);
        r.setValue2(value2);
        r.setValue3(value3);
        recordRepository.save(r);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Record> getAllRecords() {
        return recordRepository.findAll();
    }
}
