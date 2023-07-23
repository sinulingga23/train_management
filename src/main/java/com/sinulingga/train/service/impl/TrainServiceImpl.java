package com.sinulingga.train.service.impl;

import com.sinulingga.train.entity.Train;
import com.sinulingga.train.exception.BadRequestException;
import com.sinulingga.train.exception.CodeAlreadyExistsException;
import com.sinulingga.train.exception.DataNotFoundException;
import com.sinulingga.train.payload.request.TrainRequestAdd;
import com.sinulingga.train.payload.request.TrainRequestUpdate;
import com.sinulingga.train.payload.response.TrainResponseDetail;
import com.sinulingga.train.repository.TrainRepository;
import com.sinulingga.train.service.TrainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TrainServiceImpl implements TrainService {
    private final Logger LOG = LoggerFactory.getLogger(TrainServiceImpl.class);

    @Autowired
    private TrainRepository trainRepository;

    @Transactional
    @Override
    public void addTrain(TrainRequestAdd request)
            throws BadRequestException, CodeAlreadyExistsException, DataIntegrityViolationException {
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
                throw new CodeAlreadyExistsException("Code already exists.");

            trainRepository.save(new Train(request));
        } catch (BadRequestException e) {
            LOG.warn(String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage()));
            e.getStackTrace();
            throw e;
        } catch (CodeAlreadyExistsException e) {
            LOG.warn(String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage()));
            e.getStackTrace();
            throw e;
        } catch (DataIntegrityViolationException e) {
            LOG.warn(String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage()));
            e.getStackTrace();
            throw e;
        }catch (Exception e) {
            e.getStackTrace();
            LOG.warn(String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage()));
            throw e;
        }
    }

    @Override
    public TrainResponseDetail getTrainById(String id) throws DataNotFoundException, IllegalArgumentException {
        try {
            UUID idUuid = UUID.fromString(id);

            Optional<Train> optional = trainRepository.findById(idUuid);
            if (optional.isEmpty())
                throw new DataNotFoundException("Data not found");

            return new TrainResponseDetail(optional.get());
        } catch (IllegalArgumentException  e) {
            LOG.warn(String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage()));
            e.getStackTrace();
            throw e;
        } catch (DataNotFoundException e) {
            LOG.warn(String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage()));
            e.getStackTrace();
            throw e;
        } catch (Exception e) {
            LOG.warn(String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage()));
            e.getStackTrace();
            throw e;
        }
    }

    @Transactional
    @Override
    public void updateTrainById(TrainRequestUpdate request, String id)
            throws BadRequestException, DataNotFoundException, CodeAlreadyExistsException, DataIntegrityViolationException, IllegalArgumentException {
        try {
            if (!Objects.equals(request.getId(), id))
                throw new DataNotFoundException("Data not found.");
            if (request.getName() == null || request.getName().trim().length() == 0)
                throw new BadRequestException("Name can't empty");
            if (request.getCode() == null || request.getCode().trim().length() == 0)
                throw new BadRequestException("Code can't empty");
            if (request.getCapacity() == null || request.getCapacity() <= 0)
                throw new BadRequestException("Capacity should greater than 0");
            if (request.getDescription() == null || request.getDescription().trim().length() == 0)
                throw new BadRequestException("Description can't empty");

            request.setName(request.getName().trim());
            request.setDescription(request.getDescription().trim());
            request.setCode(request.getCode().replace(' ', '_').toLowerCase());

            UUID uuid = UUID.fromString(id);

            Optional<Train> optional = trainRepository.findById(uuid);
            if (optional.isEmpty())
                throw new DataNotFoundException("Data not found");

            Train train = optional.get();

            Boolean isCodeExists = false;
            if (!Objects.equals(train.getCode(), request.getCode())) {
                isCodeExists = trainRepository.existsByCode(request.getCode());
            }

            if (isCodeExists)
                throw new CodeAlreadyExistsException("Code already exists.");

            train.setCapacity(request.getCapacity());
            train.setName(request.getName());
            train.setDescription(request.getDescription());
            train.setCode(request.getCode());

            trainRepository.save(train);

        } catch (BadRequestException e) {
            LOG.warn(String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage()));
            e.getStackTrace();
            throw e;
        } catch (DataNotFoundException e) {
            LOG.warn(String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage()));
            e.getStackTrace();
            throw e;
        } catch (CodeAlreadyExistsException e) {
            LOG.warn(String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage()));
            e.getStackTrace();
            throw e;
        } catch (DataIntegrityViolationException e) {
            LOG.warn(String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage()));
            e.getStackTrace();
            throw e;
        } catch (IllegalArgumentException e) {
            LOG.warn(String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage()));
            e.getStackTrace();
            throw e;
        }
    }

    @Override
    public void deleteTrainById(String id) throws DataNotFoundException, IllegalArgumentException {
        try {
            UUID uuid = UUID.fromString(id);
            boolean isExists = trainRepository.existsById(uuid);
            if (!isExists)
                throw new DataNotFoundException("Data not found.");

            trainRepository.deleteById(uuid);
        } catch (IllegalArgumentException e) {
            LOG.warn(String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage()));
            e.getStackTrace();
            throw e;
        } catch (DataNotFoundException e) {
            LOG.warn(String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage()));
            e.getStackTrace();
            throw e;
        }
    }

    @Override
    public List<TrainResponseDetail> getTrains(int currentPage, int perPage, String name)
            throws DataNotFoundException {
        try {
            // one-based index
            currentPage -= 1;
            if (currentPage <= 0 ) {
                currentPage = 0;
            }

            if (perPage <= 0 || perPage > 100) {
                perPage = 10;
            }

            Pageable pageable = PageRequest.of(currentPage, perPage);

            Page<Train> page;
            if (name != null && name.trim().length() != 0) {
                page = trainRepository.findByNameContaining(name, pageable);
            } else {
                page = trainRepository.findAll(pageable);
            }

            if (page.isEmpty())
                throw new DataNotFoundException("Data not found");

            List<TrainResponseDetail> listDetail = new ArrayList<>();
            page.forEach(train -> {
                listDetail.add(new TrainResponseDetail(train));
            });


            return listDetail;
        } catch (DataNotFoundException e) {
            LOG.warn(String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage()));
            e.getStackTrace();
            throw  e;
        }
    }
}
