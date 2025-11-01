package wsb.merito.ticr.spring_test_demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// 1. Aktywuje wsparcie dla adnotacji Mockito (@Mock, @InjectMocks).
//    Jest to kluczowa linijka, która "ożywia" nasze atrapy.
@ExtendWith(MockitoExtension.class)
@DisplayName("Testy dla klasy UserService")
class UserServiceTest {

    // 2. STWÓRZ ATRAPĘ (MOCKA)
    // Mówimy Mockito: "Stwórz fałszywy, pusty obiekt, który udaje, że jest UserRepository".
    // Ten obiekt nie ma żadnej logiki, jest jak pusta skorupa.
    @Mock
    private UserRepository mockUserRepository;

    // 3. WSTRZYKNIJ ATRAPĘ
    // Mówimy Mockito: "Stwórz PRAWDZIWĄ instancję UserService, ale gdy będziesz potrzebował
    // obiektu UserRepository do jego konstruktora, użyj tej atrapy, którą stworzyliśmy powyżej".
    @InjectMocks
    private UserService userService;

    // Używamy @Nested dla lepszej organizacji i czytelności raportów z testów
    @Nested
    @DisplayName("Testy metody getWelcomeMessage")
    class GetWelcomeMessageTests {

        @Test
        @DisplayName("Powinien zwrócić standardowe powitanie dla zwykłego użytkownika")
        void shouldReturnStandardWelcomeMessageForRegularUser() {
            // ARRANGE (Przygotuj)
            // Stwórzmy fałszywego użytkownika na potrzeby tego jednego testu
            User regularUser = new User(1L, "Jan", false);

            // "Nauczmy" naszą atrapę, jak ma się zachować.
            // Mówimy: "Gdy ktokolwiek wywoła na tobie metodę findById z argumentem 1L,
            // to ZWRÓĆ Optional zawierający nasz obiekt regularUser".
            when(mockUserRepository.findById(1L)).thenReturn(Optional.of(regularUser));

            // ACT (Działaj)
            // Wywołajmy testowaną metodę w UserService.
            // Wewnątrz tej metody, gdy `userRepository.findById(1L)` zostanie wywołane,
            // Mockito przechwyci to i zwróci to, czego go nauczyliśmy.
            String message = userService.getWelcomeMessage(1L);

            // ASSERT (Sprawdź)
            // Sprawdźmy, czy logika w UserService poprawnie przetworzyła dane z atrapy.
            assertEquals("Witaj, Jan!", message);

            // (Opcjonalnie) Sprawdźmy, czy na atrapie została wywołana oczekiwana metoda
            verify(mockUserRepository).findById(1L);
        }

        @Test
        @DisplayName("Powinien zwrócić specjalne powitanie dla admina")
        void shouldReturnSpecialWelcomeMessageForAdmin() {
            // ARRANGE
            User adminUser = new User(2L, "Anna", true);
            when(mockUserRepository.findById(2L)).thenReturn(Optional.of(adminUser));

            // ACT
            String message = userService.getWelcomeMessage(2L);

            // ASSERT
            assertEquals("Witaj, adminie Anna!", message);
        }

        @Test
        @DisplayName("Powinien zwrócić informację o błędzie, gdy użytkownik nie istnieje")
        void shouldReturnErrorMessageWhenUserNotFound() {
            // ARRANGE
            // Nauczmy atrapę zwracać pusty Optional dla nieistniejącego ID
            when(mockUserRepository.findById(99L)).thenReturn(Optional.empty());

            // ACT
            String message = userService.getWelcomeMessage(99L);

            // ASSERT
            assertEquals("Nie znaleziono użytkownika o ID: 99", message);
        }
    }

    // Nowa zagnieżdżona klasa dla nowej metody
    @Nested
    @DisplayName("Testy metody findUserById")
    class FindUserByIdTests {

        @Test
        @DisplayName("Powinien zwrócić Optional z użytkownikiem, gdy użytkownik istnieje")
        void shouldReturnUserOptionalWhenUserExists() {
            // ARRANGE
            User user = new User(1L, "Jan", false);
            when(mockUserRepository.findById(1L)).thenReturn(Optional.of(user));

            // ACT
            Optional<User> foundUser = userService.findUserById(1L);

            // ASSERT
            // Sprawdzamy, czy Optional nie jest pusty i czy zawiera właściwego użytkownika
            assertEquals(Optional.of(user), foundUser);
        }

        @Test
        @DisplayName("Powinien zwrócić pusty Optional, gdy użytkownik nie istnieje")
        void shouldReturnEmptyOptionalWhenUserDoesNotExist() {
            // ARRANGE
            when(mockUserRepository.findById(99L)).thenReturn(Optional.empty());

            // ACT
            Optional<User> foundUser = userService.findUserById(99L);

            // ASSERT
            assertEquals(Optional.empty(), foundUser);
        }
    }
}