package com.enigma.model.response;

import lombok.Getter;
import lombok.Setter;

public class CoursePaymentResponse {
    @Getter @Setter
    private String transactionId;

    @Getter @Setter
    private boolean status;

}
