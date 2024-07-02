package com.suffixit.kieb.service;

import com.suffixit.kieb.dto.BoardTypeDTO;
import com.suffixit.kieb.dto.ResultTypeDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ResourceService {
    List<ResultTypeDTO> getAllResultType();

    List<BoardTypeDTO> getAllBoard();

}
