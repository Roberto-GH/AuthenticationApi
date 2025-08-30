package co.com.pragma.api.dto;

import java.time.LocalDate;

public record CreateUserDto(
  String firstName,
  String middleName,
  String lastName,
  String secondLastName,
  String email,
  String password,
  Long identityDocument,
  LocalDate birthdate,
  String address,
  Long numberPhone,
  Long baseSalary
) {}
