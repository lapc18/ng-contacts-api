package io.inab.contacts.infrastructure.services;

import io.inab.contacts.core.interfaces.IService;
import io.inab.contacts.core.models.PageResponse;
import io.inab.contacts.domain.dtos.UserDto;
import io.inab.contacts.domain.entities.User;
import io.inab.contacts.infrastructure.repositories.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersService implements IService<UserDto> {

    @Autowired
    private UsersRepository repository;

    @Autowired
    private ModelMapper mapper;

    /**
     * @param dto UserDto
     * @return UserDto
     * @throws Exception
     */
    @Override
    public UserDto create(UserDto dto) throws Exception {
        if(dto == null) throw new Exception("Your entity can not be empty!");

        try {
            var entity = this.mapper.map(dto, User.class);
            return this.mapper.map(this.repository.save(entity), UserDto.class);
        } catch (Exception e) {
            throw new Exception("An exception has occurred saving your entity", e);
        }
    }

    /**
     * @param dto UserDto
     * @return UserDto
     * @throws Exception
     */
    @Override
    public UserDto update(UserDto dto) throws Exception {
        if(dto == null) throw new Exception("Your entity can not be empty!");
        if(this.repository.findById(dto.getId()).isEmpty()) throw new Exception("No entity found with provided ID!");

        try {
            var entity = this.mapper.map(dto, User.class);
            return this.mapper.map(this.repository.save(entity), UserDto.class);
        } catch (Exception e) {
            throw new Exception("An exception has occurred saving the User.", e);
        }
    }

    /**
     * @param id int
     * @throws Exception
     */
    @Override
    public void softDelete(int id) throws Exception {

    }

    /**
     * @param id int
     * @throws Exception
     */
    @Override
    public void hardDelete(int id) throws Exception {

    }

    /**
     * @param pageable Pageable
     * @return PageResponse<UserDto>
     * @throws Exception
     */
    @Override
    public PageResponse<UserDto> getAll(Pageable pageable) throws Exception {
        if(pageable == null) throw new Exception("Pageable can not be empty!");

        try {
            var data = this.repository.findAll(pageable);
            var list = data.getContent()
                    .stream()
                    .filter((entity -> !entity.isDeleted()))
                    .map((entity -> this.mapper.map(entity, UserDto.class)))
                    .collect(Collectors.toList());
            return new PageResponse<>(list, data.getNumber(), data.getTotalElements(), data.getTotalPages());
        } catch (Exception e) {
            throw new Exception("An exception has occurred finding your entities.", e);
        }
    }

    /**
     * @param id
     * @return UserDto
     * @throws Exception
     */
    @Override
    public UserDto getById(int id) throws Exception {
        if(id == 0) throw new Exception("Entity ID can not be empty!");

        try {
            Optional<?> product = this.repository.findById(id);
            if(product.isEmpty()) return null;

            return this.mapper.map(product.get(), UserDto.class);
        } catch (Exception e) {
            throw new Exception("An exception has occurred finding your Entity.", e);
        }
    }

    /**
     * @param username
     * @return UserDto
     * @throws Exception
     */
    public UserDto getByUsername(String username) throws Exception {
        if(username.isEmpty()) throw new Exception("Username can not be empty!");

        try {
            var user = this.repository.findByUsername(username);
            if(user == null) return null;

            return this.mapper.map(user, UserDto.class);
        } catch (Exception e) {
            throw new Exception("An exception has occurred finding your Entity.", e);
        }
    }

    /**
     * @param email
     * @return UserDto
     * @throws Exception
     */
    public UserDto getByEmail(String email) throws Exception {
        if(email.isEmpty()) throw new Exception("Email can not be empty!");

        try {
            var user = this.repository.findByEmail(email);
            if(user == null) return null;

            return this.mapper.map(user, UserDto.class);
        } catch (Exception e) {
            throw new Exception("An exception has occurred finding your Entity.", e);
        }
    }

    /**
     * @param email
     * @return boolean
     * @throws Exception
     */
    public boolean validateCredentialsByEmail(String email, String pwd) throws Exception {
        if(email.isEmpty() || pwd.isEmpty()) return false;
        try {
            var user = this.repository.findByEmail(email);
            return Objects.equals(user.getPwd(), pwd);
        } catch (Exception e) {
            throw new Exception("An exception has occurred validating your credentials.", e);
        }
    }

    /**
     * @param username
     * @return boolean
     * @throws Exception
     */
    public boolean validateCredentialsByUsername(String username, String pwd) throws Exception {
        if(username.isEmpty() || pwd.isEmpty()) return false;
        try {
            var user = this.repository.findByUsername(username);
            return Objects.equals(user.getPwd(), pwd);
        } catch (Exception e) {
            throw new Exception("An exception has occurred validating your credentials.", e);
        }
    }

    /**
     * @param dto UserDto
     * @return UserDto
     * @throws Exception
     */
    public UserDto register(UserDto dto, String pwd) throws Exception {
        if(dto == null || pwd == null) throw new Exception("Your entity/password can not be empty!");

        try {
            var entity = this.mapper.map(dto, User.class);
            entity.setPwd(pwd);
            return this.mapper.map(this.repository.save(entity), UserDto.class);
        } catch (Exception e) {
            throw new Exception("An exception has occurred saving your entity", e);
        }
    }

}
