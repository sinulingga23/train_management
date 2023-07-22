package com.sinulingga.train.constant;

import com.sinulingga.train.payload.response.GenericResponse;

import java.util.HashMap;
import java.util.Map;

public class Response {
    public static final String RC_SUCCESS = "00";
    public static final String RD_SUCCESS = "Success";

    public static final String RC_INVALID_BODY_MANDATORY = "01";
    public static final String RD_INVALID_BODY_MANDATORY = "Invalid Body Mandatory";

    public static final String RC_INTERNAL_ERROR = "02";
    public static final String RD_INTERNAL_ERROR = "Internal Error";

    public static final String RC_INVALID_ID = "03";
    public static final String RD_INVALID_ID = "Invalid Id";

    public static final String RC_DATA_NOT_FOUND = "04";
    public static final String RD_DATA_NOT_FOUND = "Data Not Found.";

    public static final String RC_CODE_ALREADY_EXISTS = "05";
    public static final String RD_CODE_ALREADY_EXISTS = "Code Already Exists.";

    public static GenericResponse makeGenericResponse(
            String responseCode,
            String responseDesc,
            Object data
    ) {
        if (data instanceof String) {
            Map<String, String> message = new HashMap<>();
            message.put("message", (String)data);
            return new GenericResponse(responseCode, responseDesc, message);
        }

        return new GenericResponse(responseCode, responseDesc, data);
    }

}
