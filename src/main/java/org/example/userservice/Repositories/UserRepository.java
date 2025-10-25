package org.example.userservice.Repositories;

import org.example.userservice.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    @Override
    Optional<User> findById(Long Id);

    Optional<User> findByEmail(String email);

    // Input user wouldn't have the ID set, if it does, it'll update the user
    // This operation is called upsert operation
    User save(User user);

}
