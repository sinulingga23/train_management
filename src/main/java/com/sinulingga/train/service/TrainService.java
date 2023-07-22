package com.sinulingga.train.service;

import com.sinulingga.train.exception.BadRequestException;
import com.sinulingga.train.exception.CodeAlreadyExistsException;
import com.sinulingga.train.exception.DataNotFoundException;
import com.sinulingga.train.payload.request.TrainRequestAdd;
import com.sinulingga.train.payload.request.TrainRequestUpdate;
import com.sinulingga.train.payload.response.TrainResponseDetail;
import org.springframework.dao.DataIntegrityViolationException;

import javax.xml.crypto.Data;
import java.util.List;

public interface TrainService {
    void addTrain(TrainRequestAdd request)
            throws BadRequestException,
            CodeAlreadyExistsException,
            DataIntegrityViolationException;
    TrainResponseDetail getTrainById(String id)
            throws DataNotFoundException,
            IllegalArgumentException;
    void updateTrainById(TrainRequestUpdate request, String id)
            throws BadRequestException,
            DataNotFoundException,
            CodeAlreadyExistsException,
            DataIntegrityViolationException,
            IllegalArgumentException;
    void deleteTrainById(String id)
            throws
            DataNotFoundException,
            IllegalArgumentException;
    List<TrainResponseDetail> getTrains()
            throws DataNotFoundException;
}
