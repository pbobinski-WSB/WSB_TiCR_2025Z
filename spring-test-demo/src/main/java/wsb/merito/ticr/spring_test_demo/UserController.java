package wsb.merito.ticr.spring_test_demo;



import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 1. Mówi Springowi, że ta klasa obsługuje żądania HTTP i zwraca dane (np. JSON)
@RequestMapping("/api/users") // 2. Ustawia bazowy adres URL dla wszystkich metod w tej klasie
@RequiredArgsConstructor
public class UserController {

    // 3. Wstrzykujemy nasz serwis, aby kontroler mógł go używać
    private final UserService userService;

    // 4. Tworzymy metodę, która obsłuży żądanie GET pod adresem /api/users/{id}
    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) {
        // Użyjemy nowej metody w serwisie, która jest bardziej odpowiednia dla API
        return userService.findUserById(id)
                .map(user -> ResponseEntity.ok(user)) // Jeśli user istnieje, zwróć go ze statusem 200 OK
                .orElse(ResponseEntity.notFound().build()); // Jeśli nie, zwróć status 404 Not Found
    }

    // Możemy też dodać endpoint, który użyje naszej istniejącej metody z powitaniem
    @GetMapping("/{id}/welcome")
    public ResponseEntity<String> getWelcomeMessage(@PathVariable Long id) {
        String message = userService.getWelcomeMessage(id);
        if (message.startsWith("Nie znaleziono")) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(message);
    }
}
