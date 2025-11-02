package wsb.merito.ticr.spring_test_demo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Zewnętrzne testy na działającej aplikacji")
class SimpleHttpClientTest {

    private static RestTemplate restTemplate;

    // Adres URL naszej działającej aplikacji
    private static final String BASE_URL = "http://localhost:8080/api/users";

    @BeforeAll
    static void setup() {
        // Tworzymy instancję klienta HTTP raz dla wszystkich testów
        restTemplate = new RestTemplate();
    }

    @Test
    @DisplayName("Powinien pobrać istniejącego użytkownika (ID 1)")
    void shouldFetchExistingUser() {
        // ARRANGE
        String url = BASE_URL + "/1";

        // ACT
        // Wykonujemy prawdziwe żądanie sieciowe do serwera,
        // który MUSI być uruchomiony w tle.
        ResponseEntity<User> response = restTemplate.getForEntity(url, User.class);

        // ASSERT
        assertEquals(HttpStatus.OK, response.getStatusCode());

        User user = response.getBody();
        assertNotNull(user);
        assertEquals(1L, user.getId());
        assertEquals("Jan", user.getName());
    }

    @Test
    @DisplayName("Powinien otrzymać status 404 dla nieistniejącego użytkownika (ID 99)")
    void shouldReturn404ForNonExistingUser() {
        // ARRANGE
        String url = BASE_URL + "/99";

        // ACT & ASSERT
        // Zauważ, że błędy HTTP (jak 404) domyślnie rzucają wyjątek w RestTemplate.
        // Musimy go przechwycić, aby test się nie wywrócił.
        // W prawdziwym świecie użylibyśmy bardziej zaawansowanej obsługi błędów.
        try {
            restTemplate.getForEntity(url, User.class);
        } catch (org.springframework.web.client.HttpClientErrorException.NotFound ex) {
            // Sprawdzamy, czy otrzymaliśmy oczekiwany błąd 404 Not Found
            assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
        }
    }
}
