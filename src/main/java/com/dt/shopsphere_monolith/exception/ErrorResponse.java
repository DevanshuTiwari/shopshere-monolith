package com.dt.shopsphere_monolith.exception;

import java.time.LocalDateTime;

public record ErrorResponse(
        int statusCode,
        String message,
        LocalDateTime timeStamp
) {}
