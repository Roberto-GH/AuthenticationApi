package co.com.pragma.model.user;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {

  private UUID userId;
  private String firstName;
  private String middleName;
  private String lastName;
  private String secondLastName;
  private String email;
  private String password;
  private Long identityDocument;
  private LocalDate birthdate;
  private String address;
  private Long numberPhone;
  private Long baseSalary;
  private Long rolId;

}
