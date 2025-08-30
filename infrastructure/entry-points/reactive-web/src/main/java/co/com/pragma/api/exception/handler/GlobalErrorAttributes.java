package co.com.pragma.api.exception.handler;

import co.com.pragma.api.constans.AuthenticationWebKeys;
import co.com.pragma.model.user.exception.CustomException;
import co.com.pragma.model.user.exception.ErrorEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.HashMap;
import java.util.Map;

@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

  private static final Logger LOG = LoggerFactory.getLogger(GlobalErrorAttributes.class);

  @Override
  public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
    Map<String, Object> errorMap = new HashMap<>();
    Throwable error = getError(request);
    errorMap.put(AuthenticationWebKeys.ERROR_ATTRIBUTE_MESSAGE, ErrorEnum.INTERNAL_SERVER_ERROR.getDefaultMessage());
    errorMap.put(AuthenticationWebKeys.ERROR_ATTRIBUTE_ERROR_CODE, ErrorEnum.INTERNAL_SERVER_ERROR.getCode());
    errorMap.put(AuthenticationWebKeys.ERROR_ATTRIBUTE_ERROR, HttpStatus.valueOf(ErrorEnum.INTERNAL_SERVER_ERROR.getStatus()));
    errorMap.put(AuthenticationWebKeys.ERROR_ATTRIBUTE_PATH, request.path());
    if (error instanceof CustomException apiException) {
      errorMap.put(AuthenticationWebKeys.ERROR_ATTRIBUTE_MESSAGE, apiException.getMessage());
      errorMap.put(AuthenticationWebKeys.ERROR_ATTRIBUTE_ERROR_CODE, apiException.getErrorCode());
      errorMap.put(AuthenticationWebKeys.ERROR_ATTRIBUTE_ERROR, HttpStatus.valueOf(apiException.getStatus()).getReasonPhrase());
      errorMap.put(AuthenticationWebKeys.ERROR_ATTRIBUTE_PATH, request.path());
    }
    LOG.error(error.getMessage());
    return errorMap;
  }

}
