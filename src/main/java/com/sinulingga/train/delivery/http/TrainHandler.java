package com.sinulingga.train.delivery.http;

import com.sinulingga.train.constant.Response;
import com.sinulingga.train.exception.BadRequestException;
import com.sinulingga.train.exception.CodeAlreadyExistsException;
import com.sinulingga.train.exception.DataNotFoundException;
import com.sinulingga.train.payload.request.TrainRequestAdd;
import com.sinulingga.train.payload.response.GenericResponse;
import com.sinulingga.train.payload.response.TrainResponseDetail;
import com.sinulingga.train.service.impl.TrainServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/train-management/api/v1")
public class TrainHandler {
    private final Logger LOG = LoggerFactory.getLogger(TrainHandler.class);

    @Autowired
    private TrainServiceImpl trainService;

    @PostMapping("/train")
    public ResponseEntity<GenericResponse> addTrain(@RequestBody TrainRequestAdd request) {
        try {
            trainService.addTrain(request);

            GenericResponse response = Response.makeGenericResponse(
                    Response.RC_SUCCESS,
                    Response.RD_SUCCESS,
                    null);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BadRequestException e) {
            GenericResponse response = Response.makeGenericResponse(
                    Response.RC_INVALID_BODY_MANDATORY,
                    Response.RD_INVALID_BODY_MANDATORY,
                    e.getMessage()
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (CodeAlreadyExistsException e) {
            GenericResponse response = Response.makeGenericResponse(
                    Response.RC_CODE_ALREADY_EXISTS,
                    Response.RD_CODE_ALREADY_EXISTS,
                    null
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            GenericResponse response = Response.makeGenericResponse(
                    Response.RC_INTERNAL_ERROR,
                    Response.RD_INTERNAL_ERROR,
                    e.getClass().getSimpleName()
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping("/train/{id}")
    public ResponseEntity<GenericResponse> getTrainById(
            @PathVariable String id
    ) {
        try {
            TrainResponseDetail detail = trainService.getTrainById(id);

            GenericResponse response = Response.makeGenericResponse(
                    Response.RC_SUCCESS,
                    Response.RD_SUCCESS,
                    detail
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            GenericResponse response = Response.makeGenericResponse(
                    Response.RC_INVALID_ID,
                    Response.RD_INVALID_ID,
                    null
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataNotFoundException e) {
            GenericResponse response = Response.makeGenericResponse(
                    Response.RC_DATA_NOT_FOUND,
                    Response.RD_DATA_NOT_FOUND,
                    e.getMessage()
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
