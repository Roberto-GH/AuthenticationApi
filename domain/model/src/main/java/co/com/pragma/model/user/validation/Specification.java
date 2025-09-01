package co.com.pragma.model.user.validation;

import co.com.pragma.model.user.exception.DomainValidationException;

/**
 * @param <T> The type of object to validate.
 */
@FunctionalInterface
public interface Specification<T> {

  /**
   * Validates the candidate against the specification.
   * Throws an exception if the rule is not met.
   */
  void validate(T candidate) throws DomainValidationException;

}
