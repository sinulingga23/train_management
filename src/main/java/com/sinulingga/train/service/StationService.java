package com.sinulingga.train.service;

import com.sinulingga.train.exception.BadRequestException;
import com.sinulingga.train.exception.CodeAlreadyExistsException;
import com.sinulingga.train.payload.request.StationRequestAdd;
import org.springframework.dao.DataIntegrityViolationException;

public interface StationService {
    void addStation(StationRequestAdd request)
            throws BadRequestException,
            CodeAlreadyExistsException,
            DataIntegrityViolationException;

}
