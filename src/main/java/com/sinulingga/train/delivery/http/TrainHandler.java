package com.sinulingga.train.delivery.http;

import com.sinulingga.train.constant.Response;
import com.sinulingga.train.exception.BadRequestException;
import com.sinulingga.train.payload.request.TrainRequestAdd;
import com.sinulingga.train.payload.response.GenericResponse;
import com.sinulingga.train.service.impl.TrainServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/train-management/api/v1")
public class TrainHandler {
    @Autowired
    private TrainServiceImpl trainService;

    @PostMapping("/train")
    public ResponseEntity<?> addTrain(@RequestBody TrainRequestAdd request) {
        try {
            trainService.addTrain(request);

            GenericResponse response = new GenericResponse(Response.RC_SUCCESS, Response.RD_SUCCESS);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BadRequestException e) {
            Map<String,String> message = new HashMap<>();
            message.put("message", e.getMessage());

            GenericResponse response = new GenericResponse(
                    Response.RC_INVALID_BODY_MANDATORY,
                    Response.RD_INVALID_BODY_MANDATORY,
                    message
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String,String> message = new HashMap<>();
            message.put("message", e.getClass().getSimpleName());

            GenericResponse response = new GenericResponse(
                    Response.RC_INTERNAL_ERROR,
                    Response.RD_INTERNAL_ERROR,
                    message
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
