package com.sinulingga.train.service;

import com.sinulingga.train.exception.BadRequestException;
import com.sinulingga.train.exception.CodeAlreadyExistsException;
import com.sinulingga.train.exception.DataNotFoundException;
import com.sinulingga.train.payload.request.TrainRequestAdd;
import com.sinulingga.train.payload.response.TrainResponseDetail;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

public interface TrainService {
    void addTrain(TrainRequestAdd request)
            throws BadRequestException, CodeAlreadyExistsException, DataIntegrityViolationException;
    TrainResponseDetail getTrainById(String id) throws DataNotFoundException, IllegalArgumentException;
}
