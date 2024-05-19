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
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
    // CREATE (TO-DO)
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

    // LIST (TO-DO)
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

        if (todoDtoList.isEmpty()) {
            throw new TechcareerException("Todo List is empty");
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
                    ()-> new Resource404NotFoundException("Todo dto id is null")
            );
        }else{
            throw new TechcareerException(id + " Id not found");
        }
        return entityToDto(todoEntity);
    }//end todoServiceFindById


    //FIND (TodoByPriority)
    @Override
    public List<TodoDto> todoServiceFindByPriority(Priority priority) {
        // Find TodoEntities
        List<TodoEntity> todoPriorityList = iTodoRepository.findByTodoPriority(priority);

        //Check todoEntityList if it is empty throw exception
        if (todoPriorityList.isEmpty()) {
            throw new Resource404NotFoundException("No todos found with priority: " + priority);
        }
        // Create dto list
        List<TodoDto> todoDtoList = new ArrayList<>();
        // Convert Entities to Dtos
        for (TodoEntity temp : todoPriorityList) {
            TodoDto todoDto = entityToDto(temp);
            todoDtoList.add(todoDto);
        }
        return todoDtoList;
    }//end todoServiceFindByPriority

    //FIND (TodoByStatus)
    @Override
    public List<TodoDto> todoServiceFindByStatus(Status status) {
        // Find TodoEntities
        List<TodoEntity> todoStatusList = iTodoRepository.findByTodoStatus(status);

        //Check todoStatusList if it is empty throw exception
        if (todoStatusList.isEmpty()) {
            throw new Resource404NotFoundException("No todos found with status: " + status);
        }

        // Create dto list
        List<TodoDto> todoDtoList = new ArrayList<>();

        for (TodoEntity temp : todoStatusList) {
            TodoDto todoDto = entityToDto(temp);
            todoDtoList.add(todoDto);
        }
        return todoDtoList;
    }//end todoServiceFindByStatus

    //UPDATE (TO-DO)
    @Override
    @Transactional// Create,Update,Delete
    public TodoDto todoServiceUpdateById(Long id, TodoDto todoDto) {
        //Find
        TodoDto todoDtoFind = todoServiceFindById(id);

        //Check todoDtoFind if is null throw exception
        if(todoDtoFind == null)
        {
            throw new Resource404NotFoundException("Todo dto id not found");
        }

        //Update
        TodoEntity todoUpdateEntity = dtoToEntity(todoDtoFind);
        if (todoUpdateEntity != null) {
            todoUpdateEntity.setTodoDescription(todoDto.getTodoDescription());
            todoUpdateEntity.setTodoStatus(Status.valueOf(todoDto.getTodoStatus()));
            todoUpdateEntity.setTodoPriority(Priority.valueOf(todoDto.getTodoPriority()));
            todoUpdateEntity.setTodoUpdatedDate(new Date());
            iTodoRepository.save(todoUpdateEntity);
        }
        // Set ID and Date on Dto
        todoDto.setTodoId(todoUpdateEntity.getTodoId());
        todoDto.setTodoCreatedDate(todoUpdateEntity.getTodoCreatedDate());
        return entityToDto(todoUpdateEntity);
    }//end todoServiceUpdateById

    //DELETE (TO-DO)
    @Override
    @Transactional// Create,Update,Delete
    public TodoDto todoServiceDeleteById(Long id) {
        //Find
        TodoDto todoDtoFind = todoServiceFindById(id);

        if(todoDtoFind == null)
        {
            throw new Resource404NotFoundException("Todo dto id not found");
        }

        TodoEntity todoDeleteEntity = dtoToEntity(todoDtoFind);
        if (todoDeleteEntity != null) {
            iTodoRepository.deleteById(id);
            return todoDtoFind;
        }else {
            throw new TechcareerException(todoDtoFind+ "data numbered could not be deleted");
        }
    }//end todoServiceDeleteById

    @Override
    public void todoServiceDeleteAll() {
        iTodoRepository.deleteAll();
    }


}//end TodoServicesImpl
