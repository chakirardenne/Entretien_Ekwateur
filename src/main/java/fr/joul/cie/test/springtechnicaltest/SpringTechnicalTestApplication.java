package fr.joul.cie.test.springtechnicaltest;

import fr.joul.cie.test.springtechnicaltest.presenter.FilePresenter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringTechnicalTestApplication implements CommandLineRunner {
	private final FilePresenter presenter;
	public static void main(String[] args) {
		SpringApplication.run(SpringTechnicalTestApplication.class, args);
	}

	@Override
	public void run(final String... args) throws Exception {
		presenter.testPromoCode(args[0]);
	}
}
