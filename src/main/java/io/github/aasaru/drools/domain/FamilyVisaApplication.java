/*
 *  Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.drools.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class FamilyVisaApplication {
  private final int applicationId;
  private LocalDate visitStartDate;
  private LocalDate visitEndDate;

  @Builder.Default
  private List<String> passportNumbers = new ArrayList<>();

  @Builder.Default
  private Boolean validationPassed = null;

  public FamilyVisaApplication(int applicationId) {
    this.applicationId = applicationId;
  }

  public static String join(Collection<String> collection) {
    return collection.stream().map(Object::toString).collect(Collectors.joining(","));
  }

  @Override
  public String toString() {
    return String.format("FamilyVisaApplication[#%d, %s]", applicationId, join(passportNumbers));
  }
}
