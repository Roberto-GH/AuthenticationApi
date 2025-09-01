package co.com.pragma.model.user.constants;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class UserModelKeysTest {

  @Test
  void constructorShouldThrowInstantiationException() throws NoSuchMethodException {
    Constructor<UserModelKeys> constructor = UserModelKeys.class.getDeclaredConstructor();
    constructor.setAccessible(true);
    InvocationTargetException exception = assertThrows(InvocationTargetException.class, constructor::newInstance);
    assertInstanceOf(InstantiationException.class, exception.getCause());
  }

}