package wsb.merito.ticr.spring_test_demo;



import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service // Oznaczamy klasę jako komponent Springa
@RequiredArgsConstructor // Lombok wygeneruje konstruktor dla pól finalnych
public class UserService {

    private final UserRepository userRepository; // Zależność, którą będziemy mockować

    /**
     * Zwraca specjalne powitanie dla admina lub standardowe dla zwykłego użytkownika.
     * Jeśli użytkownik nie istnieje, zwraca wiadomość o błędzie.
     */
    public String getWelcomeMessage(Long userId) {
        return userRepository.findById(userId)
                .map(user -> {
                    if (user.isAdmin()) {
                        return "Witaj, adminie " + user.getName() + "!";
                    } else {
                        return "Witaj, " + user.getName() + "!";
                    }
                })
                .orElse("Nie znaleziono użytkownika o ID: " + userId);
    }

    // Dodaj tę metodę wewnątrz klasy UserService
    public  Optional<User> findUserById(Long userId) {
        return userRepository.findById(userId);
    }
}
