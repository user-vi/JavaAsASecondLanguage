package io.github.javaasasecondlanguage.flitter.utils;

import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.github.javaasasecondlanguage.flitter.utils.ExpectedStatus.EXPECT_FAIL;
import static io.github.javaasasecondlanguage.flitter.utils.ResponseAssertionUtils.assertThatStatusIsExpected;
import static java.lang.String.join;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FlitterRestWrapper {

    private final TestRestTemplate restTemplate;
    private final int port;

    public FlitterRestWrapper(TestRestTemplate restTemplate, int port) {
        this.restTemplate = restTemplate;
        this.port = port;
    }

    public TestRestTemplate getRestTemplate() {
        return restTemplate;
    }

    public int getPort() {
        return port;
    }

    public String getPath(String... parts) {
        return TestConstants.LOCALHOST + port + join("/", parts);
    }

    public void clear() {
        var endpoint = getPath("/clear");
        restTemplate.delete(endpoint);
    }

    public List<String> listUsers(ExpectedStatus expectedStatus) {
        var endpoint = getPath("/user/list");
        var response = restTemplate.getForEntity(endpoint, Result.class);
        assertThatStatusIsExpected(expectedStatus, response);

        return response
                .getBody()
                .getDataAsList();
    }

    public String addUser(String name,
                          ExpectedStatus expectedStatus
    ) {
        var endpoint = getPath("/user/register");
        var params = Map.of(TestConstants.USER_NAME, name);
        var response = restTemplate.postForEntity(endpoint, params, Result.class);
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
        var endpoint = getPath("/flit/add");
        var params = Map.of(
                TestConstants.USER_TOKEN, token,
                TestConstants.CONTENT, content
        );
        var response = restTemplate.postForEntity(endpoint, params, Result.class);
        assertThatStatusIsExpected(expectedStatus, response);
    }

    public List<Map<String, Object>> discoverFlits(ExpectedStatus expectedStatus) {
        var endpoint = getPath("/flit/discover");
        var response = restTemplate.getForEntity(endpoint, Result.class);
        assertThatStatusIsExpected(expectedStatus, response);

        return response
                .getBody()
                .getDataAsMapsList();
    }

    public List<Map<String, Object>> listFlitsByUser(String name,
                                                     ExpectedStatus expectedStatus
    ) {
        var endpoint = getPath("/flit/list", name);
        var response = restTemplate.getForEntity(endpoint, Result.class);
        assertThatStatusIsExpected(expectedStatus, response);

        return response
                .getBody()
                .getDataAsMapsList();
    }

    public List<String> listSubscribers(String token,
                                        ExpectedStatus expectedStatus
    ) {
        var endpoint = getPath("/subscribers/list", token);
        var response = restTemplate.getForEntity(endpoint, Result.class);
        assertThatStatusIsExpected(expectedStatus, response);

        return response
                .getBody()
                .getDataAsList();
    }

    public List<String> listPublishers(String token,
                                       ExpectedStatus expectedStatus
    ) {
        var endpoint = getPath("/publishers/list", token);
        var response = restTemplate.getForEntity(endpoint, Result.class);
        assertThatStatusIsExpected(expectedStatus, response);

        return response
                .getBody()
                .getDataAsList();
    }

    public void subscribe(String subscriberToken,
                          String publisherName,
                          ExpectedStatus expectedStatus
    ) {
        var endpoint = getPath("/subscribe");
        var params = Map.of(
                TestConstants.SUBSCRIBER_TOKEN, subscriberToken,
                TestConstants.PUBLISHER_NAME, publisherName
        );
        var response = restTemplate.postForEntity(endpoint, params, Result.class);
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
        var endpoint = getPath("/unsubscribe");
        var params = Map.of(
                TestConstants.SUBSCRIBER_TOKEN, subscriberToken,
                TestConstants.PUBLISHER_NAME, publisherName
        );
        var response = restTemplate.postForEntity(endpoint, params, Result.class);
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
        var endpoint = getPath("/flit/list/feed", token);
        var response = restTemplate.getForEntity(endpoint, Result.class);
        assertThatStatusIsExpected(expectedStatus, response);

        return response
                .getBody()
                .getDataAsMapsList();
    }
}
