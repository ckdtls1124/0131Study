package org.spring.security2.repository;

import org.spring.security2.entities.Members;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MembersRepository extends JpaRepository<Members, Long> {
    Optional<Members> findByEmail(String email);


    List<Members> findByEmailContaining(String search);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE membertb m SET m.password=:password, m.gender=:gender where m.id=:id and m.email=:email", nativeQuery = true)
    int updateMember(@Param(value = "email") String email, @Param(value = "password") String password1, @Param(value = "gender") String gender,
                     @Param(value="id") Long id);
}