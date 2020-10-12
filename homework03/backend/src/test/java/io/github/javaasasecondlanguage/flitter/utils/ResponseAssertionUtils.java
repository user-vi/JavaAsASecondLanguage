package io.github.javaasasecondlanguage.flitter.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static io.github.javaasasecondlanguage.flitter.utils.ExpectedStatus.EXPECT_SUCCESS;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.fail;

public class ResponseAssertionUtils {

    public static void assertThatStatusIsExpected(ExpectedStatus expectedStatus, ResponseEntity<?> responseEntity) {
        var statusCode = responseEntity.getStatusCode();
        var body = responseEntity.getBody();

        Object data = null;
        String errorMessage = null;
        if (body instanceof ArrayResult) {
            var arrayResult = (ArrayResult) body;
            data = arrayResult.getData();
            errorMessage = arrayResult.getErrorMessage();
        } else if (body instanceof MapResult) {
            var mapResult = (MapResult) body;
            data = mapResult.getData();
            errorMessage = mapResult.getErrorMessage();
        }

        if (expectedStatus == EXPECT_SUCCESS) {
            handleUnexpectedFail(statusCode, errorMessage, data);
        } else {
            handleUnexpectedSuccess(statusCode, errorMessage, data);
        }
    }

    private static void handleUnexpectedFail(HttpStatus statusCode,
                                             String errorMessage,
                                             Object data) {
        var hasErrorMessage = (errorMessage != null);
        var hasErrorCode = (statusCode != HttpStatus.OK);

        if (hasErrorMessage && hasErrorCode) {
            var assertionMessage = format(
                    "Expected success in this response, but it has error message and error code. Error message: %s, error code: %s, Data: %s",
                    errorMessage,
                    statusCode,
                    data
            );
            fail(assertionMessage);
        } else if (hasErrorMessage) {
            var assertionMessage = format(
                    "Expected success in this response, but it has error message. Error message: %s, data: %s",
                    errorMessage,
                    data
            );
            fail(assertionMessage);
        } else if (hasErrorCode) {
            var assertionMessage = format(
                    "Expected success in this response, but it has error code. Error code: %s, data: %s",
                    statusCode,
                    data
            );
            fail(assertionMessage);
        }
    }

    private static void handleUnexpectedSuccess(HttpStatus statusCode,
                                                String errorMessage,
                                                Object data) {
        var hasErrorMessage = (errorMessage != null);
        var hasErrorCode = (statusCode != HttpStatus.OK);

        if (!hasErrorMessage && !hasErrorCode) {
            var assertionMessage = format(
                    "Expected fail in this response, but it has no error message and no error code. Data: %s",
                    data
            );
            fail(assertionMessage);
        } else if (hasErrorMessage && !hasErrorCode) {
            var assertionMessage = format(
                    "Expected fail in this response, but it has no error code, only error message. Error message: %s, data: %s",
                    errorMessage,
                    data
            );
            fail(assertionMessage);
        } else if (!hasErrorMessage) {
            var assertionMessage = format(
                    "Expected fail in this response, but it has no error message, only error code. Error code: %s, data: %s",
                    statusCode,
                    data
            );
            fail(assertionMessage);
        }
    }
}
