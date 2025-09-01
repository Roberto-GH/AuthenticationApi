package co.com.pragma.usecase.user;

import co.com.pragma.model.user.Token;
import co.com.pragma.model.user.User;
import co.com.pragma.model.user.UserLogin;
import co.com.pragma.model.user.exception.ErrorEnum;
import co.com.pragma.model.user.exception.UserException;
import co.com.pragma.model.user.gateways.UserRepository;
import co.com.pragma.model.user.validation.AmountInRangeSpecification;
import co.com.pragma.model.user.validation.EmailSpecification;
import co.com.pragma.model.user.validation.NotEmptySpecification;
import co.com.pragma.model.user.validation.Specification;
import co.com.pragma.usecase.user.adapters.UserControllerUseCase;
import co.com.pragma.usecase.user.constants.UserUseCaseKeys;
import reactor.core.publisher.Mono;

public class  UserUseCase implements UserControllerUseCase {

  private static final Specification<String> FIRST_NAME_NOT_EMPTY = new NotEmptySpecification(UserUseCaseKeys.FIRST_NAME_FIELD);
  private static final Specification<String> LAST_NAME_NOT_EMPTY = new NotEmptySpecification(UserUseCaseKeys.LAST_NAME_FIELD);
  private static final Specification<String> EMAIL_FORMAT = new EmailSpecification(UserUseCaseKeys.EMAIL_FIELD);
  private static final Specification<String> PASSWORD_NOT_EMPTY = new NotEmptySpecification(UserUseCaseKeys.PASSWORD_FIELD);
  private static final Specification<Long> SALARY_RANGE = new AmountInRangeSpecification(UserUseCaseKeys.SALARY_RANGE_FIELD, UserUseCaseKeys.SALARY_MIN, UserUseCaseKeys.SALARY_MAX);

  private final UserRepository userRepository;

  public UserUseCase(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public Mono<User> saveUser(User user) {
    return Mono.fromCallable(() -> {
      FIRST_NAME_NOT_EMPTY.validate(user.getFirstName());
      LAST_NAME_NOT_EMPTY.validate(user.getLastName());
      EMAIL_FORMAT.validate(user.getEmail());
      SALARY_RANGE.validate(user.getBaseSalary());
      return user;
    }).flatMap(validateUser -> this.assertUserEmailNotExists(user.getEmail())
      .then(userRepository.saveUser(user)));
  }

  @Override
  public Mono<Token> login(UserLogin userLogin) {
    return Mono.fromCallable(() -> {
      EMAIL_FORMAT.validate(userLogin.getEmail());
      PASSWORD_NOT_EMPTY.validate(userLogin.getPassword());
      return userLogin;
    }).flatMap(userRepository::getToken);
  }

    @Override
    public Mono<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Mono<Void> assertUserEmailNotExists(String email) {
    return userRepository.findByEmail(email)
      .flatMap(user -> Mono.error(new UserException(ErrorEnum.INVALID_USER_DATA, UserUseCaseKeys.USER_VALIDATED_EMAIL + email)))
      .then();
  }

}

