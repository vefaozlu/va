package com.example.va.data.user;

import com.example.va.core.service.user._common.dto.UserDTO;
import com.example.va.core.service.user._common.protocol.UserDSGateway;
import com.example.va.core.service.user.createuser.CreateUserDsRequest;
import com.example.va.presenter._common.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaUser implements UserDSGateway {
    private final EntityManager entityManager;
    private final JpaUserRepository jpaUserRepository;

    JpaUser(EntityManager entityManager, JpaUserRepository jpaUserRepository) {
        this.entityManager = entityManager;
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public boolean createUser(CreateUserDsRequest user) {
        UserDataMapper userDataMapper = new UserDataMapper(
                user.getFullName(),
                user.getEmail(),
                user.getPhone(),
                user.getPassword(),
                user.getRole()
        );
        jpaUserRepository.save(userDataMapper);

        return true;
    }

    @Override
    public UserDTO getUserById(Integer id) {
        Optional<UserDataMapper> user = jpaUserRepository.findById(id);

        if (user.isPresent()) {
            return new UserDTO(
                    user.get().getId(),
                    user.get().getFullName(),
                    user.get().getEmail(),
                    user.get().getPhone(),
                    user.get().getPassword(),
                    user.get().getRole(),
                    null
            );
        }

        throw new NotFoundException("User not found");
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaUserRepository.existsByEmail(email);
    }

    @Override
    public boolean existsById(Integer id) {
        return jpaUserRepository.existsById(id);
    }

    @Override
    public boolean existsByPhone(String phone) {
        return jpaUserRepository.existsByPhone(phone);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        UserDataMapper user = jpaUserRepository.findByEmail(email);

        return new UserDTO(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getPhone(),
                user.getPassword(),
                user.getRole(),
                null
        );
    }

    @Override
    public List<UserDTO> getAllUsers() {
        String search = "test";

        StringBuilder jpql = new StringBuilder("SELECT e FROM UserDataMapper e WHERE 1=1");

        if (search != null) {
            jpql.append(" OR e.email LIKE :search");
            jpql.append(" OR e.fullName LIKE :search");
            jpql.append(" OR e.phone LIKE :search");
        }

        jpql.append(" e.deletedAt != CURRENT_TIMESTAMP");

        TypedQuery<UserDataMapper> query = entityManager.createQuery(jpql.toString(), UserDataMapper.class);

        if (search != null && !search.isEmpty()) query.setParameter("search", "%" + search + "%");

        return query
                .getResultList()
                .stream()
                .map(user -> new UserDTO(user.getId(),
                        user.getFullName(),
                        user.getEmail(),
                        user.getPhone(),
                        user.getPassword(),
                        user.getRole(),
                        user.getDeletedAt()))
                .toList();
    }
}
