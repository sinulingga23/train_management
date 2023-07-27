package com.sinulingga.train.service;

import com.sinulingga.train.exception.BadRequestException;
import com.sinulingga.train.exception.CodeAlreadyExistsException;
import com.sinulingga.train.exception.DataNotFoundException;
import com.sinulingga.train.payload.request.StationRequestAdd;
import com.sinulingga.train.payload.response.StationResponseDetail;
import org.springframework.dao.DataIntegrityViolationException;

public interface StationService {
    void addStation(StationRequestAdd request)
            throws BadRequestException,
            CodeAlreadyExistsException,
            DataIntegrityViolationException;
    StationResponseDetail getStationById(String id)
            throws DataNotFoundException,
            IllegalArgumentException;
}
