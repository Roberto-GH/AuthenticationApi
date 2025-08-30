package co.com.pragma.r2dbc;

import co.com.pragma.r2dbc.constants.PostgreSQLKeys;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.UUID;

@Table(PostgreSQLKeys.TABLE_NAME_USERS)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserEntity {

  @Id
  @Column(PostgreSQLKeys.COLUMN_NAME_USER_ID)
  private UUID userId;
  @Column(PostgreSQLKeys.COLUMN_NAME_USER_FIRST_NAME)
  private String firstName;
  @Column(PostgreSQLKeys.COLUMN_NAME_USER_MIDDLE_NAME)
  private String middleName;
  @Column(PostgreSQLKeys.COLUMN_NAME_USER_LAST_NAME)
  private String lastName;
  @Column(PostgreSQLKeys.COLUMN_NAME_USER_SECOND_LAST_NAME)
  private String secondLastName;
  private String email;
  private String password;
  @Column(PostgreSQLKeys.COLUMN_NAME_USER_IDENTITY_DOCUMENT)
  private Long identityDocument;
  private LocalDate birthdate;
  private String address;
  @Column(PostgreSQLKeys.COLUMN_NAME_USER_NUMBER_PHONE)
  private Long numberPhone;
  @Column(PostgreSQLKeys.COLUMN_NAME_USER_BASE_SALARY)
  private Long baseSalary;
  @Column(PostgreSQLKeys.COLUMN_NAME_USER_ROLE_ID)
  private Long rolId;

}
