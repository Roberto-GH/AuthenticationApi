package co.com.pragma.model.validation;

public class NotEmptySpecification implements Specification<String> {

  private final String fieldName;

  public NotEmptySpecification(String fieldName) {
    this.fieldName = fieldName;
  }

  @Override
  public void validate(String candidate) throws DomainValidationException {
    if (candidate == null || candidate.trim().isEmpty()) {
      throw new DomainValidationException("The field '" + fieldName + "' cannot be null or empty.", 400);
    }
  }

}
