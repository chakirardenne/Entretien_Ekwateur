package fr.joul.cie.test.springtechnicaltest.domain.ports.out;

import fr.joul.cie.test.springtechnicaltest.domain.Code;

import java.util.List;

public interface CodePort {
    List<Code> getAll();
}
