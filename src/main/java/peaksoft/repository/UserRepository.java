package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByEmail(String email);

    @Query("select u from User u where u.email=:email")
    User findUserByWithEmail(String email);
}
