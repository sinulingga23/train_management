package com.sinulingga.train.service.impl;

import com.sinulingga.train.entity.Train;
import com.sinulingga.train.exception.BadRequestException;
import com.sinulingga.train.payload.request.TrainRequestAdd;
import com.sinulingga.train.repository.TrainRepository;
import com.sinulingga.train.service.TrainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrainServiceImpl implements TrainService {
    private final Logger LOG = LoggerFactory.getLogger(TrainServiceImpl.class);

    @Autowired
    private TrainRepository trainRepository;

    @Transactional
    @Override
    public void addTrain(TrainRequestAdd request)
            throws BadRequestException, DataIntegrityViolationException {
        try {
            if (request.getName() == null || request.getName().trim().length() == 0)
                throw new BadRequestException("Name can't empty.");
            if (request.getCapacity() == null || request.getCapacity() <= 0)
                throw new BadRequestException("Capacity should greater than 0.");
            if (request.getCode() == null || request.getCode().trim().length() == 0)
                throw new BadRequestException("Code can't empty");
            if (request.getDescription() == null || request.getDescription().trim().length() == 0)
                throw new BadRequestException("Description can't empty");

            request.setName(request.getName().trim());
            request.setCode(request.getCode().trim().replace(' ','_').toLowerCase());
            request.setDescription(request.getDescription().trim());

            Boolean isExists = trainRepository.existsByCode(request.getCode());
            if (isExists)
                throw new BadRequestException("Code already exists.");

            trainRepository.save(new Train(request));
        } catch (BadRequestException e) {
            LOG.info(String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage()));
            e.getStackTrace();
            throw e;
        } catch (DataIntegrityViolationException e) {
            LOG.info(String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage()));
            e.getStackTrace();
            throw e;
        }catch (Exception e) {
            e.getStackTrace();
            LOG.info(String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage()));
            throw e;
        }
    }

    @Override
    public void addTrains(List<TrainRequestAdd> request)
            throws BadRequestException {
        try {
            if (request.size() == 0)
                throw new BadRequestException("Data can't empty.");

            List<Train> data = new ArrayList<>();
            for (TrainRequestAdd item : request) {
                if (item.getName().trim().length() == 0)
                    throw new BadRequestException("Name can't empty.");
                if (item.getCapacity() <= 0)
                    throw  new BadRequestException("Capacity should greater than 0.");

                data.add(new Train(item));
            }

            trainRepository.saveAll(data);
        } catch (BadRequestException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }
}
