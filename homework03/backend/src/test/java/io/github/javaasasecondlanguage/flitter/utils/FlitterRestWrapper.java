package io.github.javaasasecondlanguage.flitter.utils;

import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.String.join;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    public List<String> listUsers() {
        var endpoint = getPath("/user/list");
        var users = restTemplate.getForObject(endpoint, ArrayResult.class);
        return users.getDataAsList();
    }

    public String addUser(String name) {
        var endpoint = getPath("/user/register");
        var params = Map.of(TestConstants.USER_NAME, name);

        var result = restTemplate.postForObject(endpoint, params, MapResult.class);

        var data = result.getData();
        var outputName = data.get(TestConstants.USER_NAME);
        var outputToken = data.get(TestConstants.USER_TOKEN);
        assertEquals(name, outputName);
        assertNotNull(outputToken);

        return outputToken.toString();
    }

    public List<String> addAllUsers(Collection<String> userNames) {
        var userTokens = userNames
                .stream()
                .map(name -> addUser(name))
                .collect(Collectors.toList());
        return userTokens;
    }

    public void addFlit(String token, String content) {
        var endpoint = getPath("/flit/add");
        var params = Map.of(
                TestConstants.USER_TOKEN, token,
                TestConstants.CONTENT, content
        );

        restTemplate.postForObject(endpoint, params, MapResult.class);
    }

    public List<Map<String, Object>> discoverFlits() {
        var endpoint = getPath("/flit/discover");
        var result = restTemplate.getForObject(endpoint, ArrayResult.class);
        return result.getDataAsMaps();
    }

    public List<Map<String, Object>> listFlitsByUser(String name) {
        var endpoint = getPath("/flit/list", name);
        var result = restTemplate.getForObject(endpoint, ArrayResult.class);
        return result.getDataAsMaps();
    }

    public List<String> listSubscribers(String token) {
        var endpoint = getPath("/subscribers/list", token);
        var result = restTemplate.getForObject(endpoint, ArrayResult.class);
        return result.getDataAsList();
    }

    public List<String> listPublishers(String token) {
        var endpoint = getPath("/publishers/list", token);
        var result = restTemplate.getForObject(endpoint, ArrayResult.class);
        return result.getDataAsList();
    }

    public void subscribe(String subscriberToken, String publisherName) {
        var endpoint = getPath("/subscribe");
        var params = Map.of(
                TestConstants.SUBSCRIBER_TOKEN, subscriberToken,
                TestConstants.PUBLISHER_NAME, publisherName
        );
        var result = restTemplate.postForObject(endpoint, params, MapResult.class);
    }

    public void subscribeAll(Collection<String> subscriberTokens, List<String> publisherNames) {
        for (String publisherName : publisherNames) {
            for (String subscriberToken : subscriberTokens) {
                subscribe(subscriberToken, publisherName);
            }
        }
    }

    public void unsubscribe(String subscriberToken, String publisherName) {
        var endpoint = getPath("/unsubscribe");
        var params = Map.of(
                TestConstants.SUBSCRIBER_TOKEN, subscriberToken,
                TestConstants.PUBLISHER_NAME, publisherName
        );
        restTemplate.postForObject(endpoint, params, MapResult.class);
    }

    public void unsubscribeAll(Collection<String> subscriberTokens, List<String> publisherNames) {
        for (String publisherName : publisherNames) {
            for (String subscriberToken : subscriberTokens) {
                unsubscribe(subscriberToken, publisherName);
            }
        }
    }

    public List<Map<String, Object>> listFlitsConsumedByUser(String token) {
        var endpoint = getPath("/flit/list/feed", token);
        var result = restTemplate.getForObject(endpoint, ArrayResult.class);
        return result.getDataAsMaps();
    }
}
