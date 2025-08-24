package co.com.pragma.r2dbc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.UUID;

@Table("users")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserEntity {

  @Id
  @Column("user_id")
  private UUID userId;
  @Column("first_name")
  private String firstName;
  @Column("middle_name")
  private String middleName;
  @Column("last_name")
  private String lastName;
  @Column("second_last_name")
  private String secondLastName;
  private String email;
  private String password;
  @Column("identity_document")
  private Long identityDocument;
  private LocalDate birthdate;
  private String address;
  @Column("number_phone")
  private Long numberPhone;
  @Column("base_salary")
  private Long baseSalary;
  @Column("rol_id")
  private Long rolId;

}
