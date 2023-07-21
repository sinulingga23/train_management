package com.sinulingga.train.service;

import com.sinulingga.train.exception.BadRequestException;
import com.sinulingga.train.payload.request.TrainRequestAdd;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

public interface TrainService {
    void addTrain(TrainRequestAdd request) throws BadRequestException, DataIntegrityViolationException;
    void addTrains(List<TrainRequestAdd> request) throws BadRequestException;
}
