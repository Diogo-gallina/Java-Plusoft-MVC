package br.com.JavaPlusoftMvc.dto.employe;

import br.com.JavaPlusoftMvc.model.Employee;


public record EmployeeDetailsWithFeedbackAverageDTO(
        EmployeeDetailsDTO employeeDetails,
        Double feedbackAvarage
) {
    public EmployeeDetailsWithFeedbackAverageDTO(Employee employee, Double feedbackAvarage) {
        this(
                new EmployeeDetailsDTO(employee),
                feedbackAvarage
        );
    }
}
