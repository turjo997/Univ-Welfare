package com.suffixit.kieb.controller;

import com.suffixit.kieb.dto.BoardTypeDTO;
import com.suffixit.kieb.dto.ResultTypeDTO;
import com.suffixit.kieb.service.CommitteeService;
import com.suffixit.kieb.service.ResourceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/resource")
public class ResourceController {

    private final ResourceService resourceService;

    private final CommitteeService committeeService;

    public ResourceController(ResourceService resourceService, CommitteeService committeeService) {
        this.resourceService = resourceService;
        this.committeeService = committeeService;
    }

    @GetMapping("/result-type/all")
    public List<ResultTypeDTO> getAllResultType() {
        return resourceService.getAllResultType();
    }

    @GetMapping("/board/all")
    public List<BoardTypeDTO> getAllBoard() {
        return resourceService.getAllBoard();
    }



}
