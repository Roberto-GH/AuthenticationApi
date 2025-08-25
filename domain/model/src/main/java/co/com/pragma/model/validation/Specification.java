package co.com.pragma.model.validation;

/**
 * @param <T> El tipo de objeto a validar.
 */
@FunctionalInterface
public interface Specification<T> {

  /**
   * Valida el candidato contra la especificaci�n.
   * Lanza una excepci�n si la regla no se cumple.
   */
  void validate(T candidate) throws DomainValidationException;

}
