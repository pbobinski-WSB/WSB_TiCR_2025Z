package wsb.merito.ticr.spring_test_demo;



import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(Long id);
}
