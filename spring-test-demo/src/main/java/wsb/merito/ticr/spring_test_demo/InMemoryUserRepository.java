package wsb.merito.ticr.spring_test_demo;



import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

// 1. Oznaczamy tę klasę jako komponent Springa, a konkretnie jako Repozytorium.
//    Dzięki temu Spring ją znajdzie podczas skanowania.
@Repository
public class InMemoryUserRepository implements UserRepository { // 2. Implementujemy nasz interfejs

    // Używamy ConcurrentHashMap, która jest bezpieczna w środowisku wielowątkowym.
    // Będzie udawać naszą tabelę w bazie danych.
    private final Map<Long, User> users = new ConcurrentHashMap<>();

    // @PostConstruct to adnotacja Springa, która mówi: "Uruchom tę metodę zaraz po
    // stworzeniu tego beana". Użyjemy jej do wypełnienia naszej "bazy" danymi startowymi.
    @PostConstruct
    void setup() {
        users.put(1L, new User(1L, "Jan", false));
        users.put(2L, new User(2L, "Anna", true));
        users.put(3L, new User(3L, "Zofia", false));
    }

    // 3. Musimy zaimplementować metodę z interfejsu.
    @Override
    public Optional<User> findById(Long id) {
        // Zwracamy użytkownika z mapy. Optional.ofNullable elegancko obsługuje
        // sytuację, gdy użytkownik o danym ID nie istnieje (zwróci pusty Optional).
        return Optional.ofNullable(users.get(id));
    }
}