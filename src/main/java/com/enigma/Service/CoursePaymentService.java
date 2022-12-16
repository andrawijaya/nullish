package com.enigma.Service;

import com.enigma.model.request.CoursePaymentRequest;
import com.enigma.model.response.CoursePaymentResponse;
import com.enigma.repository.ICoursePaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoursePaymentService implements ICoursePaymentService {
   private ICoursePaymentRepository coursePaymentRepository;

    public CoursePaymentService(@Autowired ICoursePaymentRepository coursePaymentRepository) {
        this.coursePaymentRepository = coursePaymentRepository;
    }

    @Override
    public CoursePaymentResponse pay(CoursePaymentRequest coursePaymentRequest) {
        return coursePaymentRepository.callPaymentApi(coursePaymentRequest);
    }
}
