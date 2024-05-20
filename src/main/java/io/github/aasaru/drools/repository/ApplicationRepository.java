/*
 *  Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.drools.repository;

import static java.util.Arrays.asList;

import io.github.aasaru.drools.domain.FamilyVisaApplication;
import io.github.aasaru.drools.domain.Passport;
import io.github.aasaru.drools.domain.VisaApplication;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplicationRepository {

  public static final String SARAH_PASSPORT_NUMBER = "CA-SARAH-1";
  public static final String SIMON_PASSPORT_NUMBER = "CA-SIMON-2";
  public static final String EMILY_PASSPORT_NUMBER = "AU-EMILY-3";
  public static final String JAMES_PASSPORT_NUMBER = "AU-JAMES-4";

  public static List<Passport> getPassports() {
    var passport1 =
        Passport.builder()
            .passportNumber(SARAH_PASSPORT_NUMBER)
            .name("Sarah Murphy")
            .unusedVisaPages(1)
            .expiresOn(LocalDate.of(2017, Month.DECEMBER, 17))
            .age(50)
            .build();

    var passport2 =
        Passport.builder()
            .passportNumber(SIMON_PASSPORT_NUMBER)
            .name("Simon Murphy")
            .unusedVisaPages(0)
            .expiresOn(LocalDate.of(2045, Month.MAY, 11))
            .age(12)
            .build();

    var passport3 =
        Passport.builder()
            .passportNumber(EMILY_PASSPORT_NUMBER)
            .name("Emily Brown")
            .unusedVisaPages(20)
            .expiresOn(LocalDate.of(2047, Month.NOVEMBER, 25))
            .age(16)
            .build();

    var passport4 =
        Passport.builder()
            .passportNumber(JAMES_PASSPORT_NUMBER)
            .name("James Brown")
            .unusedVisaPages(10)
            .expiresOn(LocalDate.of(2045, Month.APRIL, 10))
            .age(17)
            .build();

    return List.of(passport1, passport2, passport3, passport4);
  }

  public static List<VisaApplication> getVisaApplications() {
    List<VisaApplication> visaApplications = new ArrayList<>();

    visaApplications.add(
        VisaApplication.newBuilder()
            .withApplicationId(1)
            .withPassportNumber(SARAH_PASSPORT_NUMBER)
            .withVisitStartDate(LocalDate.of(2039, Month.DECEMBER, 27))
            .withVisitEndDate(LocalDate.of(2040, Month.JANUARY, 4))
            .build());

    visaApplications.add(
        VisaApplication.newBuilder()
            .withApplicationId(2)
            .withPassportNumber(SIMON_PASSPORT_NUMBER)
            .withVisitStartDate(LocalDate.of(2039, Month.DECEMBER, 27))
            .withVisitEndDate(LocalDate.of(2039, Month.JANUARY, 4))
            .build());

    visaApplications.add(
        VisaApplication.newBuilder()
            .withApplicationId(3)
            .withPassportNumber(EMILY_PASSPORT_NUMBER)
            .withVisitStartDate(LocalDate.of(2044, Month.JANUARY, 1))
            .withVisitEndDate(LocalDate.of(2044, Month.MARCH, 31))
            .build());

    visaApplications.add(
        VisaApplication.newBuilder()
            .withApplicationId(4)
            .withPassportNumber(JAMES_PASSPORT_NUMBER)
            .withVisitStartDate(LocalDate.of(2045, Month.JANUARY, 1))
            .withVisitEndDate(LocalDate.of(2045, Month.MARCH, 10))
            .build());

    return visaApplications;
  }

  public static List<FamilyVisaApplication> getFamilyVisaApplications() {
    var familyVisaApplications1 =
        FamilyVisaApplication.builder()
            .applicationId(10)
            .passportNumbers(asList(SARAH_PASSPORT_NUMBER, SIMON_PASSPORT_NUMBER))
            .visitStartDate(LocalDate.of(2039, Month.DECEMBER, 27))
            .visitEndDate(LocalDate.of(2040, Month.JANUARY, 4))
            .build();

    var familyVisaApplications2 =
        FamilyVisaApplication.builder()
            .applicationId(11)
            .passportNumbers(asList(EMILY_PASSPORT_NUMBER, JAMES_PASSPORT_NUMBER))
            .visitStartDate(LocalDate.of(2044, Month.JANUARY, 1))
            .visitEndDate(LocalDate.of(2044, Month.MAY, 31))
            .build();

    return List.of(familyVisaApplications1, familyVisaApplications2);
  }
}
