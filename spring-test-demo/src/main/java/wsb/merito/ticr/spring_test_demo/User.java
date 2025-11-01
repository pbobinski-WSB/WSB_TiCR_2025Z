package wsb.merito.ticr.spring_test_demo;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor // Lombok wygeneruje konstruktor z wszystkimi polami
@Getter         // Lombok wygeneruje gettery dla wszystkich pól
public class User {
    private final Long id;
    private final String name;
    private final boolean isAdmin;
}