package co.com.pragma.model.user.validation;

import co.com.pragma.model.user.exception.DomainValidationException;

/**
 * @param <T> El tipo de objeto a validar.
 */
@FunctionalInterface
public interface Specification<T> {

  /**
   * Valida el candidato contra la especificacion.
   * Lanza una excepcion si la regla no se cumple.
   */
  void validate(T candidate) throws DomainValidationException;

}
