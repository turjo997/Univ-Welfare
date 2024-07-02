package com.suffixit.kieb.controller;

import com.suffixit.kieb.dto.CommitteeDTO;
import com.suffixit.kieb.service.CommitteeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/committee")
public class CommitteeController {

    private final CommitteeService committeeService;


    public CommitteeController(CommitteeService committeeService) {
        this.committeeService = committeeService;
    }
    @PostMapping("/add")
    public ResponseEntity<String> addCommittee(@RequestBody CommitteeDTO committeeDTO){
        return committeeService.addCommittee(committeeDTO);
    }

}
