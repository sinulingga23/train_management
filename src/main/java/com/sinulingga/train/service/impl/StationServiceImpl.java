package com.sinulingga.train.service.impl;

import com.sinulingga.train.entity.Station;
import com.sinulingga.train.exception.BadRequestException;
import com.sinulingga.train.exception.CodeAlreadyExistsException;
import com.sinulingga.train.payload.request.StationRequestAdd;
import com.sinulingga.train.repository.StationRepository;
import com.sinulingga.train.service.StationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StationServiceImpl implements StationService {
    private final Logger LOG = LoggerFactory.getLogger(StationServiceImpl.class);

    @Autowired
    private StationRepository stationRepository;

    @Transactional
    @Override
    public void addStation(StationRequestAdd request) throws BadRequestException, CodeAlreadyExistsException, DataIntegrityViolationException {
        try {
            if (request.getCode() == null || request.getCode().trim().length() == 0)
                throw new BadRequestException("Code can't empty");
            if (request.getDescription() == null || request.getDescription().trim().length() == 0)
                throw new BadRequestException("Description can't empty");
            if (request.getName() == null || request.getName().trim().length() == 0)
                throw new BadRequestException("Name can't empty");
            if (request.getAddress() == null || request.getAddress().trim().length() == 0)
                throw new BadRequestException("Address can't empty");

            request.setCode(request.getCode().trim().replace(' ','_'));
            request.setDescription(request.getDescription().trim());
            request.setName(request.getName().trim());
            request.setAddress(request.getAddress().trim());

            Boolean isExists = stationRepository.existsByCode(request.getCode());
            if (isExists)
                throw new CodeAlreadyExistsException("Code already exists.");

            stationRepository.save(new Station(request));
        } catch (BadRequestException e) {
            LOG.info(String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage()));
            e.getStackTrace();
            throw e;
        } catch (CodeAlreadyExistsException e) {
            LOG.info(String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage()));
            e.getStackTrace();
            throw e;
        } catch (DataIntegrityViolationException e) {
            LOG.info(String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage()));
            e.getStackTrace();
            throw e;
        }
    }
}
