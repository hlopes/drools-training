/*
 *  Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.drools.section07;

import java.util.List;
import java.util.stream.Collectors;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieSession;

import io.github.aasaru.drools.Common;
import io.github.aasaru.drools.domain.InvalidPassport;
import io.github.aasaru.drools.domain.InvalidVisaApplication;
import io.github.aasaru.drools.domain.Passport;
import io.github.aasaru.drools.domain.SessionData;
import io.github.aasaru.drools.domain.ValidPassport;
import io.github.aasaru.drools.domain.ValidVisaApplication;
import io.github.aasaru.drools.domain.Visa;
import io.github.aasaru.drools.domain.VisaApplication;
import io.github.aasaru.drools.repository.ApplicationRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VisaInsertLogical {
  public static void main(final String[] args) {
    execute(Common.promptForStep(7, args, 1, 4));
  }

  static SessionData execute(int step) {
    log.info("Running step {}", step);

    SessionData sessionData = new SessionData();
    KieSession ksession = KieServices.Factory.get().getKieClasspathContainer()
        .newKieSession("VisaInsertLogicalStep" + step);

    if (step < 3) {
      ksession.addEventListener(new ObjectEventListener());
    }

    List<Passport> passports = ApplicationRepository.getPassports();
    passports.forEach(ksession::insert);

    List<VisaApplication> visaApplications = ApplicationRepository.getVisaApplications();
    visaApplications.forEach(ksession::insert);

    log.info("==== DROOLS SESSION START ==== ");

    ksession.fireAllRules();

    log.info("==== DROOLS SESSION END ==== ");

    if (step > 1 && step < 4) {
      log.info("==== VALID PASSPORTS FROM DROOLS SESSION === ");

      sessionData.validPassports = ksession.getObjects(o -> o.getClass() == ValidPassport.class)
          .stream()
          .map(o -> (ValidPassport) o)
          .peek(p -> log.info("ValidPassport {}", p))
          .collect(Collectors.toList());

      log.info("==== INVALID PASSPORTS FROM DROOLS SESSION === ");

      sessionData.invalidPassports = ksession.getObjects(o -> o.getClass() == InvalidPassport.class).stream()
          .map(o -> (InvalidPassport) o)
          .peek(p -> log.info("InvalidPassport {}", p))
          .collect(Collectors.toList());
    }

    if (step == 3) {
      log.info("==== VALID APPLICATIONS FROM DROOLS SESSION === ");

      sessionData.validVisaApplications = ksession.getObjects(o -> o.getClass() == ValidVisaApplication.class).stream()
          .map(o -> (ValidVisaApplication) o)
          .peek(v -> log.info("ValidVisaApplication {}", v))
          .collect(Collectors.toList());
      sessionData.validVisaApplications.forEach(System.out::println);

      log.info("==== INVALID APPLICATIONS FROM DROOLS SESSION === ");

      sessionData.invalidVisaApplications = ksession
          .getObjects(o -> o.getClass() == InvalidVisaApplication.class).stream()
          .map(o -> (InvalidVisaApplication) o)
          .peek(v -> log.info("InvalidVisaApplication {}", v))
          .collect(Collectors.toList());
    }

    if (step != 2) {
      log.info("== Visas from session == ");

      sessionData.visas = ksession
          .getObjects(o -> o.getClass() == Visa.class).stream()
          .map(o -> (Visa) o)
          .peek(v -> log.info("Visa {}", v))
          .collect(Collectors.toList());
    }

    if (Common.disposeSession) {
      ksession.dispose();
    }

    return sessionData;
  }

}
