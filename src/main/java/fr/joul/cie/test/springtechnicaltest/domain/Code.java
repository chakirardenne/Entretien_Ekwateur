package fr.joul.cie.test.springtechnicaltest.domain;

import java.time.LocalDate;

public record Code(Long code, Float discountValue, LocalDate endDate) {
}
