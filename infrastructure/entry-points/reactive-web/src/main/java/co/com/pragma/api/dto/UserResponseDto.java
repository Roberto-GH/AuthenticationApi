package co.com.pragma.api.dto;

import java.time.LocalDate;

public record UserResponseDto(
  String firstName,
  String middleName,
  String lastName,
  String secondLastName,
  String email,
  Long identityDocument,
  LocalDate birthdate,
  String address,
  Long numberPhone,
  Long baseSalary
) {}
