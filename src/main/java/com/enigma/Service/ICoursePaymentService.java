package com.enigma.Service;

import com.enigma.model.request.CoursePaymentRequest;
import com.enigma.model.response.CoursePaymentResponse;

public interface ICoursePaymentService {
    CoursePaymentResponse pay(CoursePaymentRequest coursePaymentRequest);
}
