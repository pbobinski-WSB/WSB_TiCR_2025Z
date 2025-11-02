package wsb.merito.ticr.spring_test_demo;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// 1. Mówimy Springowi, aby uruchomił CAŁĄ aplikację na losowym porcie.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Testy integracyjne dla UserController")
class UserControllerIntegrationTest {

    // 2. Spring wstrzyknie nam port, na którym uruchomił serwer.
    @LocalServerPort
    private int port;

    // 3. Spring wstrzyknie nam klienta HTTP, gotowego do wysyłania żądań.
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Powinien pobrać istniejącego użytkownika przez API")
    void shouldFetchExistingUser() {
        // ARRANGE (Przygotuj)
        // Tworzymy pełny adres URL do naszego API, używając losowego portu
        String url = "http://localhost:" + port + "/api/users/1";

        // ACT (Działaj)
        // Wyślij prawdziwe żądanie GET i oczekuj odpowiedzi w postaci obiektu User
        ResponseEntity<User> response = restTemplate.getForEntity(url, User.class);

        // ASSERT (Sprawdź)
        // Sprawdź status odpowiedzi HTTP
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Sprawdź ciało odpowiedzi (JSON został automatycznie zmapowany na obiekt User)
        User user = response.getBody();
        assertNotNull(user);
        assertEquals(1L, user.getId());
        assertEquals("Jan", user.getName());
    }

    @Test
    @DisplayName("Powinien zwrócić status 404 dla nieistniejącego użytkownika")
    void shouldReturn404ForNonExistingUser() {
        // ARRANGE
        String url = "http://localhost:" + port + "/api/users/99";

        // ACT
        ResponseEntity<User> response = restTemplate.getForEntity(url, User.class);

        // ASSERT
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
