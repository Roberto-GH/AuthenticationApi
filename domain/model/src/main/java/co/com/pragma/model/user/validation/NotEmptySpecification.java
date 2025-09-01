package co.com.pragma.model.user.validation;

import co.com.pragma.model.user.constants.UserModelKeys;
import co.com.pragma.model.user.exception.DomainValidationException;
import co.com.pragma.model.user.exception.ErrorEnum;

import java.util.logging.Logger;

public class NotEmptySpecification implements Specification<String> {

  private static final Logger LOG = Logger.getLogger(NotEmptySpecification.class.getName());

  private final String fieldName;

  public NotEmptySpecification(String fieldName) {
    this.fieldName = fieldName;
  }

  @Override
  public void validate(String candidate) throws DomainValidationException {
    if (candidate == null || candidate.trim().isEmpty()) {
      LOG.severe(UserModelKeys.FIELD_NOT_NULL_OR_EMPTY + fieldName);
      throw new DomainValidationException(ErrorEnum.INVALID_USER_DATA, UserModelKeys.FIELD_NOT_NULL_OR_EMPTY + fieldName);
    }
  }

}
