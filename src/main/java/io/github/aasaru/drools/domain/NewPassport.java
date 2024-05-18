package io.github.aasaru.drools.domain;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NewPassport {
  private String passportNumber;
  private String name;
  private LocalDate expiresOn;
  private int unusedVisaPages;
  private int age;

  @Builder.Default
  private Validation validation = Validation.UNKNOWN;

  @Builder.Default
  private String cause = "";

  public boolean isExpired() {
    return expiresOn.isBefore(LocalDate.now());
  }

  @Override
  public String toString() {
    return String.format("Passport[no:%s, name:%s]", passportNumber, name);
  }
}
