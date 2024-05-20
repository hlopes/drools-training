/*
 *  Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.drools.section08;

import io.github.aasaru.drools.Common;
import io.github.aasaru.drools.domain.*;
import io.github.aasaru.drools.repository.ApplicationRepository;
import java.util.Collection;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieSession;

@Slf4j
public class FamilyVisaApplicationValidation {
  public static void main(final String[] args) {
    execute(Common.promptForStep(8, args, 1, 5));
  }

  static void execute(int step) {
    log.info("Running step " + step);

    List<Passport> passports = ApplicationRepository.getPassports();
    String sessionName = "FamilyVisaApplicationStep" + step;

    if (step == 3) {
      boolean isYounger =
          Common.promptForYesNoQuestion("Do you want to make everyone 3 years younger?");
      sessionName += isYounger;

      if (isYounger) {
        log.info("Making everyone 3 years younger");
        passports.forEach(passport -> passport.setAge(passport.getAge() - 3));
        passports.forEach(passport -> log.info(passport + " is now " + passport.getAge()));
      }
    }

    KieSession ksession =
        KieServices.Factory.get().getKieClasspathContainer().newKieSession(sessionName);
    passports.forEach(ksession::insert);

    List<FamilyVisaApplication> familyVisaApplications =
        ApplicationRepository.getFamilyVisaApplications();
    familyVisaApplications.forEach(ksession::insert);

    log.info("==== DROOLS SESSION START ==== ");
    ksession.fireAllRules();
    log.info("==== DROOLS SESSION END ==== ");

    log.info("==== INVALID FAMILY VISA APPLICATIONS FROM DROOLS SESSION === ");
    Collection<?> invalidApplications =
        ksession.getObjects(o -> o.getClass() == InvalidFamilyVisaApplication.class);
    invalidApplications.forEach(ia -> log.info("{}", ia));

    log.info("== Visas from session == ");
    Collection<?> visas = ksession.getObjects(o -> o.getClass() == Visa.class);
    visas.forEach(v -> log.info("{}", v));

    log.info("== Group leaders from session == ");
    Collection<?> groupLeaders = ksession.getObjects(o -> o.getClass() == GroupLeader.class);
    groupLeaders.forEach(gl -> log.info("{}", gl));

    if (Common.disposeSession) {
      ksession.dispose();
    }
  }
}
