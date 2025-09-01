package co.com.pragma.model.user.validation;

import co.com.pragma.model.user.constants.UserModelKeys;
import co.com.pragma.model.user.exception.DomainValidationException;
import co.com.pragma.model.user.exception.ErrorEnum;

public class EmailSpecification implements Specification<String> {

  private final String fieldName;

  public EmailSpecification(String fieldName) {
    this.fieldName = fieldName;
  }

  @Override
  public void validate(String candidate) throws DomainValidationException {
    if (candidate == null || candidate.trim().isEmpty()) {
      throw new DomainValidationException(ErrorEnum.INVALID_USER_DATA, UserModelKeys.FIELD_NOT_EMPTY + fieldName);
    }
    if (!UserModelKeys.EMAIL_PATTERN.matcher(candidate).matches()) {
      throw new DomainValidationException(ErrorEnum.INVALID_USER_DATA, UserModelKeys.INVALID_FORMAT + fieldName);
    }
  }

}
