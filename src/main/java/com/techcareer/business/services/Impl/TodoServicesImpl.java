package com.techcareer.business.services.Impl;

import com.techcareer.bean.ModelMapperBeanClass;
import com.techcareer.business.dto.TodoDto;
import com.techcareer.business.services.ITodoServices;
import com.techcareer.data.entity.TodoEntity;
import com.techcareer.data.repository.ITodoRepository;
import com.techcareer.exception.Resource404NotFoundException;
import com.techcareer.exception.TechcareerException;
import com.techcareer.role.Priority;
import com.techcareer.role.Status;
import lombok.SneakyThrows;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Lombok
@RequiredArgsConstructor
@Log4j2

// Service: Bearing the Main Workload
@Service
@Component("todoServicesImpl") // @Component => You are a part of Spring
public class TodoServicesImpl implements ITodoServices<TodoDto, TodoEntity> {

    //Lombok => Constructor Injection
    private final ITodoRepository iTodoRepository;
    private final ModelMapperBeanClass modelMapperBeanClass;


    ///////////////////////////////////////////////////////////////////////////////////////
    //**** Model Mapper *****************************************************************//
    // Model Mapper
    @Override
    public TodoDto entityToDto(TodoEntity todoEntity) {
        return modelMapperBeanClass.modelMapperMethod().map(todoEntity, TodoDto.class);
    }

    @Override
    public TodoEntity dtoToEntity(TodoDto todoDto) {
        return modelMapperBeanClass.modelMapperMethod().map(todoDto, TodoEntity.class);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //**** CRUD*****************************************************************//
    // CREATE (TODO)
    @Override
    @Transactional// Create,Update,Delete
    public TodoDto todoServiceCreate(TodoDto todoDto) {
        TodoEntity todoEntity1;
        // Dto => Entity convert
        todoEntity1 = dtoToEntity(todoDto);
        // Save
        TodoEntity roleEntity2 = iTodoRepository.save(todoEntity1);
        // I set ID and Date on Dto
        todoDto.setTodoId(roleEntity2.getTodoId());
        todoDto.setTodoCreatedDate(roleEntity2.getTodoCreatedDate());
        return todoDto;
    }//end todoServiceCreate

    // LIST (TODO)
    @Override
    public List<TodoDto> todoServiceList() {
        //Entity List
        List<TodoEntity> todoEntityList1 = iTodoRepository.findAll();
        // Dto List
        List<TodoDto> todoDtoList = new ArrayList<>();

        // Entity To Dto List
        for (TodoEntity tempEntity : todoEntityList1) {
            TodoDto todoDto1 = entityToDto(tempEntity);
            todoDtoList.add(todoDto1);
        }

        return todoDtoList;
    }//end todoServiceList

    // FIND (TodoById)
    @Override
    public TodoDto todoServiceFindById(Long id) {
        Boolean booleanTodoServiceFindById = iTodoRepository.findById(id).isPresent();
        TodoEntity todoEntity = null;
        if (booleanTodoServiceFindById) {
            todoEntity = iTodoRepository.findById(id).orElseThrow(
                    ()-> new Resource404NotFoundException(id + " Id not found")
            );
        }else{
            throw new TechcareerException("Todo dto id is null");
        }
        return entityToDto(todoEntity);
    }//end todoServiceFindById

    //FIND (TodoByPriority)
    @Override
    public List<TodoDto> todoServiceFindByPriority(Priority priority) {
        // Find TodoEntities
        List<TodoEntity> todoEntityList = iTodoRepository.findByTodoPriority(priority);
        // Create dto list
        List<TodoDto> todoDtoList = new ArrayList<>();
        // Convert Entities to Dtos
        for (TodoEntity temp : todoEntityList) {
            TodoDto todoDto = entityToDto(temp);
            todoDtoList.add(todoDto);
        }
        return todoDtoList;
    }//end todoServiceFindByPriority

    //FIND (TodoByStatus)
    @Override
    public List<TodoDto> todoServiceFindByStatus(Status status) {
        // Find TodoEntities
        List<TodoEntity> todoEntityList = iTodoRepository.findByTodoStatus(status);
        // Create dto list
        List<TodoDto> todoDtoList = new ArrayList<>();

        for (TodoEntity temp : todoEntityList) {
            TodoDto todoDto = entityToDto(temp);
            todoDtoList.add(todoDto);
        }
        return todoDtoList;
    }//end todoServiceFindByStatus

    //UPDATE (TODO)
    @Override
    @Transactional// Create,Update,Delete
    public TodoDto todoServiceUpdateById(Long id, TodoDto todoDto) {
        //Find
        TodoDto todoDtoFind = todoServiceFindById(id);
        //Update
        TodoEntity todoUpdateEntity = dtoToEntity(todoDtoFind);
        if (todoUpdateEntity != null) {
            todoUpdateEntity.setTodoDescription(todoDto.getTodoDescription());
            iTodoRepository.save(todoUpdateEntity);
        }
        // Set ID and Date on Dto
        todoDto.setTodoId(todoUpdateEntity.getTodoId());
        todoDto.setTodoCreatedDate(todoUpdateEntity.getTodoCreatedDate());
        return entityToDto(todoUpdateEntity);
    }//end todoServiceUpdateById

    //DELETE (TODO)
    @Override
    @Transactional// Create,Update,Delete
    public TodoDto todoServiceDeleteById(Long id) {
        //Find
        TodoDto todoDtoFind = todoServiceFindById(id);

        TodoEntity todoDeleteEntity = dtoToEntity(todoDtoFind);
        if (todoDeleteEntity != null) {
            iTodoRepository.deleteById(id);
            return todoDtoFind;
        }else {
            throw new TechcareerException(todoDtoFind+ "data numbered could not be deleted");
        }
    }//end todoServiceDeleteById
}//end TodoServicesImpl
