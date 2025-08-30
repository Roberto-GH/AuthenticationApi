package co.com.pragma.model.user.validation;

import co.com.pragma.model.user.exception.DomainValidationException;
import co.com.pragma.model.user.exception.ErrorEnum;

import java.util.regex.Pattern;

public class EmailSpecification implements Specification<String> {

  private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
  private final String fieldName;

  public EmailSpecification(String fieldName) {
    this.fieldName = fieldName;
  }

  @Override
  public void validate(String candidate) throws DomainValidationException {
    if (candidate == null || candidate.trim().isEmpty()) {
      throw new DomainValidationException(ErrorEnum.INVALID_USER_DATA, "The email field '" + fieldName + "' cannot be empty.");
    }
    if (!EMAIL_PATTERN.matcher(candidate).matches()) {
      throw new DomainValidationException(ErrorEnum.INVALID_USER_DATA, "The format of the field '" + fieldName + "' is not a valid email.");
    }
  }

}
