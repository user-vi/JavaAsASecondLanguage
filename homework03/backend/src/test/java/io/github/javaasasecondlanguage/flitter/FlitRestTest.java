package io.github.javaasasecondlanguage.flitter;

import io.github.javaasasecondlanguage.flitter.utils.CollectionTestUtils;
import io.github.javaasasecondlanguage.flitter.utils.FlitterRestWrapper;
import io.github.javaasasecondlanguage.flitter.utils.TestConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FlitRestTest {

    private FlitterRestWrapper rest;

    public FlitRestTest(
            @Autowired TestRestTemplate restTemplate,
            @LocalServerPort int port
    ) {
        this.rest = new FlitterRestWrapper(restTemplate, port);
    }

    @BeforeEach
    void setUp() {
        rest.clear();
    }

    @Test
    public void test_listAllFlits_empty() {
        var flits = rest.listAllFlits();
        assertEquals(0, flits.size());
    }

    @Test
    public void test_addFlit_noCheck() {
        var token = rest.addUser("Sasha");
        var content = "My first flit";

        rest.addFlit(token, content);
    }

    @Test
    public void test_addFlit_singleUser_singleFlit() {
        var token = rest.addUser("Sasha");
        var inputContent = "My first flit";

        rest.addFlit(token, inputContent);
        var flits = rest.listAllFlits();

        var expectedFlits = List.of(
                Map.of(TestConstants.USER_NAME, "Sasha", TestConstants.CONTENT, "My first flit")
        );
        CollectionTestUtils.assertMapsEqualByKeys(expectedFlits, flits, TestConstants.USER_NAME, TestConstants.CONTENT);
    }

    @Test
    public void test_addFlit_singleUser_multipleFlits() {
        var token = rest.addUser("Sasha");
        rest.addFlit(token, "My first flit");
        rest.addFlit(token, "My second flit");

        var flits = rest.listAllFlits();

        var expectedFlits = List.of(
                Map.of(TestConstants.USER_NAME, "Sasha", TestConstants.CONTENT, "My first flit"),
                Map.of(TestConstants.USER_NAME, "Sasha", TestConstants.CONTENT, "My second flit")
        );
        CollectionTestUtils.assertMapsEqualByKeys(expectedFlits, flits, TestConstants.USER_NAME, TestConstants.CONTENT);
    }

    @Test
    public void test_addFlit_multipleUsers_multipleFlits() {
        var tokenOne = rest.addUser("Sasha");
        rest.addFlit(tokenOne, "Sasha's first flit");
        rest.addFlit(tokenOne, "Sasha's second flit");

        var tokenTwo = rest.addUser("Nikita");
        rest.addFlit(tokenTwo, "Nikita's first flit");
        rest.addFlit(tokenTwo, "Nikita's second flit");

        var flits = rest.listAllFlits();

        var expectedFlits = List.of(
                Map.of(TestConstants.USER_NAME, "Sasha", TestConstants.CONTENT, "Sasha's first flit"),
                Map.of(TestConstants.USER_NAME, "Sasha", TestConstants.CONTENT, "Sasha's second flit"),
                Map.of(TestConstants.USER_NAME, "Nikita", TestConstants.CONTENT, "Nikita's first flit"),
                Map.of(TestConstants.USER_NAME, "Nikita", TestConstants.CONTENT, "Nikita's second flit")
        );
        CollectionTestUtils.assertMapsEqualByKeys(expectedFlits, flits, TestConstants.USER_NAME, TestConstants.CONTENT);
    }

    @Test
    public void test_listFlitByUser_singleUser() {
        var token = rest.addUser("Sasha");
        rest.addFlit(token, "My first flit");
        rest.addFlit(token, "My second flit");

        var flits = rest.listFlitsByUser("Sasha");

        var expectedFlits = List.of(
                Map.of(TestConstants.USER_NAME, "Sasha", TestConstants.CONTENT, "My first flit"),
                Map.of(TestConstants.USER_NAME, "Sasha", TestConstants.CONTENT, "My second flit")
        );
        CollectionTestUtils.assertMapsEqualByKeys(expectedFlits, flits, TestConstants.USER_NAME, TestConstants.CONTENT);
    }

    @Test
    public void test_listFlitByUser_multipleUsers_multipleFlits() {
        var tokenOne = rest.addUser("Sasha");
        rest.addFlit(tokenOne, "Sasha's first flit");
        rest.addFlit(tokenOne, "Sasha's second flit");

        var tokenTwo = rest.addUser("Nikita");
        rest.addFlit(tokenTwo, "Nikita's first flit");
        rest.addFlit(tokenTwo, "Nikita's second flit");

        var flitsBySasha = rest.listFlitsByUser("Sasha");
        List<Map<String, String>> expectedFlitsBySasha = List.of(
                Map.of(TestConstants.USER_NAME, "Sasha", TestConstants.CONTENT, "Sasha's first flit"),
                Map.of(TestConstants.USER_NAME, "Sasha", TestConstants.CONTENT, "Sasha's second flit")
        );
        CollectionTestUtils.assertMapsEqualByKeys(expectedFlitsBySasha, flitsBySasha, TestConstants.USER_NAME, TestConstants.CONTENT);

        var flitsByNikita = rest.listFlitsByUser("Nikita");
        var expectedFlitsByNikita = List.of(
                Map.of(TestConstants.USER_NAME, "Nikita", TestConstants.CONTENT, "Nikita's first flit"),
                Map.of(TestConstants.USER_NAME, "Nikita", TestConstants.CONTENT, "Nikita's second flit")
        );
        CollectionTestUtils.assertMapsEqualByKeys(expectedFlitsByNikita, flitsByNikita, TestConstants.USER_NAME, TestConstants.CONTENT);
    }
}
