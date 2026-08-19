package com.D1.projectD1Campus.excepcion;

import java.time.LocalDateTime;

public record ErrorResponse(
        LocalDateTime timestamp, int status, String messsage, String errorCode) {


}
