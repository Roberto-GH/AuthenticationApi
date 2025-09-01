package co.com.pragma.model.user.validation;

import co.com.pragma.model.user.constants.UserModelKeys;
import co.com.pragma.model.user.exception.DomainValidationException;
import co.com.pragma.model.user.exception.ErrorEnum;

public class AmountInRangeSpecification implements Specification<Long> {

  private final Long min;
  private final Long max;
  private final String fieldName;

  public AmountInRangeSpecification(String fieldName, Long min, Long max) {
    this.fieldName = fieldName;
    this.min = min;
    this.max = max;
  }

  @Override
  public void validate(Long candidate) throws DomainValidationException {
    if (candidate == null) {
      throw new DomainValidationException(ErrorEnum.INVALID_USER_DATA, UserModelKeys.FIELD_NOT_NULL + fieldName);
    }
    if (candidate.compareTo(min) < 0 || candidate.compareTo(max) > 0) {
      throw new DomainValidationException(ErrorEnum.INVALID_USER_DATA, UserModelKeys.FIELD_RANGE + " - " + min + " and " + max + ". =>" + fieldName);
    }
  }

}
