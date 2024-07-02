package com.suffixit.kieb.service.impl;

import com.suffixit.kieb.dto.BoardTypeDTO;
import com.suffixit.kieb.dto.ResultTypeDTO;
import com.suffixit.kieb.entities.ResultType;
import com.suffixit.kieb.entities.University;
import com.suffixit.kieb.repository.ResultTypeRepository;
import com.suffixit.kieb.repository.UniversityRepository;
import com.suffixit.kieb.service.ResourceService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResourceServiceImpl implements ResourceService {

    private final ResultTypeRepository resultTypeRepository;
    private final UniversityRepository universityRepository;

    public ResourceServiceImpl(ResultTypeRepository resultTypeRepository, UniversityRepository universityRepository) {
        this.resultTypeRepository = resultTypeRepository;
        this.universityRepository = universityRepository;
    }

    @Override
    public List<ResultTypeDTO> getAllResultType() {
        List<ResultType> resultTypes = resultTypeRepository.findAll();

        if (resultTypes.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No result types found");
        }

        return resultTypes.stream().map(resultType -> ResultTypeDTO.builder().id(resultType.getId()).resultType(resultType.getResultTypeName()).build()).collect(Collectors.toList());
    }

    @Override
    public List<BoardTypeDTO> getAllBoard() {
        List<University> boardInfos = universityRepository.findAll();

        if (boardInfos.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No boards found");
        }

        return boardInfos.stream().map(board -> BoardTypeDTO.builder().id(board.getId()).boardName(board.getBoardUniversity()).build()).collect(Collectors.toList());
    }
}
