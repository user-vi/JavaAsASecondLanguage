package io.github.javaasasecondlanguage.flitter.utils;

import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.github.javaasasecondlanguage.flitter.utils.AssertionUtils.assertEquals;
import static io.github.javaasasecondlanguage.flitter.utils.AssertionUtils.assertNotNull;
import static io.github.javaasasecondlanguage.flitter.utils.AssertionUtils.assertThatStatusIsExpected;
import static io.github.javaasasecondlanguage.flitter.utils.ExpectedStatus.EXPECT_FAIL;

public class FlitterRestMethods {

    private final LoggingRestTemplate restTemplate;

    public FlitterRestMethods(TestRestTemplate restTemplate, int port) {
        this.restTemplate = new LoggingRestTemplate(restTemplate, port);
    }

    public void clear() {
        restTemplate.delete("/clear");
    }

    public List<String> listUsers(ExpectedStatus expectedStatus) {
        var response = restTemplate.get("/user/list");
        assertThatStatusIsExpected(expectedStatus, response);

        return response
                .getBody()
                .getDataAsList();
    }

    public String addUser(String name,
                          ExpectedStatus expectedStatus
    ) {
        var params = Map.of(TestConstants.USER_NAME, name);
        var response = restTemplate.post(params, "/user/register");
        assertThatStatusIsExpected(expectedStatus, response);

        if (expectedStatus == EXPECT_FAIL) {
            return null;
        }

        var data = response
                .getBody()
                .getDataAsMap();
        var outputName = data.get(TestConstants.USER_NAME);
        var outputToken = data.get(TestConstants.USER_TOKEN);
        assertEquals(name, outputName);
        assertNotNull(outputToken);

        return outputToken.toString();
    }

    public List<String> addAllUsers(Collection<String> userNames,
                                    ExpectedStatus expectedStatus) {
        var userTokens = userNames
                .stream()
                .map(name -> addUser(name, expectedStatus))
                .collect(Collectors.toList());
        return userTokens;
    }

    public void addFlit(String token,
                        String content,
                        ExpectedStatus expectedStatus
    ) {
        var params = Map.of(
                TestConstants.USER_TOKEN, token,
                TestConstants.CONTENT, content
        );
        var response = restTemplate.post(params, "/flit/add");
        assertThatStatusIsExpected(expectedStatus, response);
    }

    public List<Map<String, Object>> discoverFlits(ExpectedStatus expectedStatus) {
        var response = restTemplate.get("/flit/discover");
        assertThatStatusIsExpected(expectedStatus, response);

        return response
                .getBody()
                .getDataAsMapsList();
    }

    public List<Map<String, Object>> listFlitsByUser(String name,
                                                     ExpectedStatus expectedStatus
    ) {
        var response = restTemplate.get("/flit/list", name);
        assertThatStatusIsExpected(expectedStatus, response);

        return response
                .getBody()
                .getDataAsMapsList();
    }

    public List<String> listSubscribers(String token,
                                        ExpectedStatus expectedStatus
    ) {
        var response = restTemplate.get("/subscribers/list", token);
        assertThatStatusIsExpected(expectedStatus, response);

        return response
                .getBody()
                .getDataAsList();
    }

    public List<String> listPublishers(String token,
                                       ExpectedStatus expectedStatus
    ) {
        var response = restTemplate.get("/publishers/list", token);
        assertThatStatusIsExpected(expectedStatus, response);

        return response
                .getBody()
                .getDataAsList();
    }

    public void subscribe(String subscriberToken,
                          String publisherName,
                          ExpectedStatus expectedStatus
    ) {
        var params = Map.of(
                TestConstants.SUBSCRIBER_TOKEN, subscriberToken,
                TestConstants.PUBLISHER_NAME, publisherName
        );
        var response = restTemplate.post(params, "/subscribe");
        assertThatStatusIsExpected(expectedStatus, response);
    }

    public void subscribeAll(Collection<String> subscriberTokens,
                             List<String> publisherNames,
                             ExpectedStatus expectedStatus
    ) {
        for (String publisherName : publisherNames) {
            for (String subscriberToken : subscriberTokens) {
                subscribe(subscriberToken, publisherName, expectedStatus);
            }
        }
    }

    public void unsubscribe(String subscriberToken,
                            String publisherName,
                            ExpectedStatus expectedStatus
    ) {
        var params = Map.of(
                TestConstants.SUBSCRIBER_TOKEN, subscriberToken,
                TestConstants.PUBLISHER_NAME, publisherName
        );
        var response = restTemplate.post(params, "/unsubscribe");
        assertThatStatusIsExpected(expectedStatus, response);
    }

    public void unsubscribeAll(Collection<String> subscriberTokens,
                               List<String> publisherNames,
                               ExpectedStatus expectedStatus
    ) {
        for (String publisherName : publisherNames) {
            for (String subscriberToken : subscriberTokens) {
                unsubscribe(subscriberToken, publisherName, expectedStatus);
            }
        }
    }

    public List<Map<String, Object>> listFlitsConsumedByUser(String token,
                                                             ExpectedStatus expectedStatus
    ) {
        var response = restTemplate.get("/flit/list/feed", token);
        assertThatStatusIsExpected(expectedStatus, response);

        return response
                .getBody()
                .getDataAsMapsList();
    }
}
