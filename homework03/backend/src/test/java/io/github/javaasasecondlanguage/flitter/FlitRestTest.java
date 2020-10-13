package io.github.javaasasecondlanguage.flitter;

import io.github.javaasasecondlanguage.flitter.utils.FlitterRestWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;
import java.util.Map;

import static io.github.javaasasecondlanguage.flitter.utils.CollectionTestUtils.assertMapsEqualByKeys;
import static io.github.javaasasecondlanguage.flitter.utils.ExpectedStatus.EXPECT_FAIL;
import static io.github.javaasasecondlanguage.flitter.utils.ExpectedStatus.EXPECT_SUCCESS;
import static io.github.javaasasecondlanguage.flitter.utils.TestConstants.CONTENT;
import static io.github.javaasasecondlanguage.flitter.utils.TestConstants.USER_NAME;
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
        var flits = rest.discoverFlits(EXPECT_SUCCESS);
        assertEquals(0, flits.size());
    }

    @Test
    public void test_addFlit_noCheck() {
        var token = rest.addUser("Sasha", EXPECT_SUCCESS);
        var content = "My first flit";

        rest.addFlit(token, content, EXPECT_SUCCESS);
    }

    @Test
    public void test_addFlit_singleUser_singleFlit() {
        var token = rest.addUser("Sasha", EXPECT_SUCCESS);
        var inputContent = "My first flit";

        rest.addFlit(token, inputContent, EXPECT_SUCCESS);
        var flits = rest.discoverFlits(EXPECT_SUCCESS);

        var expectedFlits = List.of(
                Map.of(USER_NAME, "Sasha", CONTENT, "My first flit")
        );
        assertMapsEqualByKeys(expectedFlits, flits, USER_NAME, CONTENT);
    }

    @Test
    public void test_addFlit_singleUser_multipleFlits() {
        var token = rest.addUser("Sasha", EXPECT_SUCCESS);
        rest.addFlit(token, "My first flit", EXPECT_SUCCESS);
        rest.addFlit(token, "My second flit", EXPECT_SUCCESS);

        var flits = rest.discoverFlits(EXPECT_SUCCESS);

        var expectedFlits = List.of(
                Map.of(USER_NAME, "Sasha", CONTENT, "My first flit"),
                Map.of(USER_NAME, "Sasha", CONTENT, "My second flit")
        );
        assertMapsEqualByKeys(expectedFlits, flits, USER_NAME, CONTENT);
    }

    @Test
    public void test_addFlit_multipleUsers_multipleFlits() {
        var tokenOne = rest.addUser("Sasha", EXPECT_SUCCESS);
        rest.addFlit(tokenOne, "Sasha's first flit", EXPECT_SUCCESS);
        rest.addFlit(tokenOne, "Sasha's second flit", EXPECT_SUCCESS);

        var tokenTwo = rest.addUser("Nikita", EXPECT_SUCCESS);
        rest.addFlit(tokenTwo, "Nikita's first flit", EXPECT_SUCCESS);
        rest.addFlit(tokenTwo, "Nikita's second flit", EXPECT_SUCCESS);

        var flits = rest.discoverFlits(EXPECT_SUCCESS);

        var expectedFlits = List.of(
                Map.of(USER_NAME, "Sasha", CONTENT, "Sasha's first flit"),
                Map.of(USER_NAME, "Sasha", CONTENT, "Sasha's second flit"),
                Map.of(USER_NAME, "Nikita", CONTENT, "Nikita's first flit"),
                Map.of(USER_NAME, "Nikita", CONTENT, "Nikita's second flit")
        );
        assertMapsEqualByKeys(expectedFlits, flits, USER_NAME, CONTENT);
    }

    @Test
    void test_addFlit_unknownToken() {
        rest.addUser("Sasha", EXPECT_SUCCESS);
        rest.addUser("Nikita", EXPECT_SUCCESS);

        var token = "Unknown token";
        var content = "Flit from unknown token";
        rest.addFlit(token, content, EXPECT_FAIL);
    }

    @Test
    public void test_listFlitByUser_singleUser() {
        var token = rest.addUser("Sasha", EXPECT_SUCCESS);
        rest.addFlit(token, "My first flit", EXPECT_SUCCESS);
        rest.addFlit(token, "My second flit", EXPECT_SUCCESS);

        var flits = rest.listFlitsByUser("Sasha", EXPECT_SUCCESS);

        var expectedFlits = List.of(
                Map.of(USER_NAME, "Sasha", CONTENT, "My first flit"),
                Map.of(USER_NAME, "Sasha", CONTENT, "My second flit")
        );
        assertMapsEqualByKeys(expectedFlits, flits, USER_NAME, CONTENT);
    }

    @Test
    public void test_listFlitByUser_multipleUsers_multipleFlits() {
        var tokenOne = rest.addUser("Sasha", EXPECT_SUCCESS);
        rest.addFlit(tokenOne, "Sasha's first flit", EXPECT_SUCCESS);
        rest.addFlit(tokenOne, "Sasha's second flit", EXPECT_SUCCESS);

        var tokenTwo = rest.addUser("Nikita", EXPECT_SUCCESS);
        rest.addFlit(tokenTwo, "Nikita's first flit", EXPECT_SUCCESS);
        rest.addFlit(tokenTwo, "Nikita's second flit", EXPECT_SUCCESS);

        var flitsBySasha = rest.listFlitsByUser("Sasha", EXPECT_SUCCESS);
        List<Map<String, String>> expectedFlitsBySasha = List.of(
                Map.of(USER_NAME, "Sasha", CONTENT, "Sasha's first flit"),
                Map.of(USER_NAME, "Sasha", CONTENT, "Sasha's second flit")
        );
        assertMapsEqualByKeys(expectedFlitsBySasha, flitsBySasha, USER_NAME, CONTENT);

        var flitsByNikita = rest.listFlitsByUser("Nikita", EXPECT_SUCCESS);
        var expectedFlitsByNikita = List.of(
                Map.of(USER_NAME, "Nikita", CONTENT, "Nikita's first flit"),
                Map.of(USER_NAME, "Nikita", CONTENT, "Nikita's second flit")
        );
        assertMapsEqualByKeys(expectedFlitsByNikita, flitsByNikita, USER_NAME, CONTENT);
    }

    @Test
    void test_listFlitByUser_unknownUser() {
        var sashaToken = rest.addUser("Sasha", EXPECT_SUCCESS);
        rest.addFlit(sashaToken, "Sashas flit", EXPECT_SUCCESS);

        rest.addUser("Nikita", EXPECT_SUCCESS);

        var sashaFlits = rest.listFlitsByUser("Sasha", EXPECT_SUCCESS);
        assertEquals(1, sashaFlits.size());

        var nikitaFlits = rest.listFlitsByUser("Nikita", EXPECT_SUCCESS);
        assertEquals(0, nikitaFlits.size());

        rest.listFlitsByUser("UnknownUser", EXPECT_FAIL);
    }
}
