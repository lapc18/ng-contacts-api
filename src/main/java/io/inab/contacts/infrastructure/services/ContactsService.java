package io.inab.contacts.infrastructure.services;

import io.inab.contacts.core.interfaces.IService;
import io.inab.contacts.core.models.PageResponse;
import io.inab.contacts.domain.dtos.ContactDto;
import io.inab.contacts.domain.dtos.UserDto;
import io.inab.contacts.domain.entities.Contact;
import io.inab.contacts.domain.entities.User;
import io.inab.contacts.infrastructure.repositories.ContactsRepository;
import io.inab.contacts.infrastructure.repositories.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactsService implements IService<ContactDto> {

    @Autowired
    private ContactsRepository repository;

    @Autowired
    private ModelMapper mapper;

    /**
     * @param dto ContactDto
     * @return ContactDto
     * @throws Exception
     */
    @Override
    public ContactDto create(ContactDto dto) throws Exception {
        if(dto == null) throw new Exception("Your entity can not be empty!");

        try {
            var entity = this.mapper.map(dto, Contact.class);
            return this.mapper.map(this.repository.save(entity), ContactDto.class);
        } catch (Exception e) {
            throw new Exception("An exception has occurred saving your entity", e);
        }
    }

    /**
     * @param dto ContactDto
     * @return ContactDto
     * @throws Exception
     */
    @Override
    public ContactDto update(ContactDto dto) throws Exception {
        if(dto == null) throw new Exception("Your entity can not be empty!");
        if(this.repository.findById(dto.getId()).isEmpty()) throw new Exception("No entity found with provided ID!");

        try {
            var entity = this.mapper.map(dto, Contact.class);
            return this.mapper.map(this.repository.save(entity), ContactDto.class);
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
    public PageResponse<ContactDto> getAll(Pageable pageable) throws Exception {
        if(pageable == null) throw new Exception("Pageable can not be empty!");

        try {
            var data = this.repository.findAll(pageable);
            var list = data.getContent()
                    .stream()
                    .filter((entity -> !entity.isDeleted()))
                    .map((entity -> this.mapper.map(entity, ContactDto.class)))
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
    public ContactDto getById(int id) throws Exception {
        if(id == 0) throw new Exception("Entity ID can not be empty!");

        try {
            Optional<?> product = this.repository.findById(id);
            if(product.isEmpty()) return null;

            return this.mapper.map(product.get(), ContactDto.class);
        } catch (Exception e) {
            throw new Exception("An exception has occurred finding your Entity.", e);
        }
    }
}
