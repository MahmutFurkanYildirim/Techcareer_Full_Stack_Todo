package com.techcareer.business.services.Impl;

import com.techcareer.business.dto.TodoDto;
import com.techcareer.business.services.ITodoServices;
import com.techcareer.data.entity.TodoEntity;
import com.techcareer.role.Priority;
import com.techcareer.role.Status;

import java.util.List;

public class TodoServicesImpl implements ITodoServices<TodoDto, TodoEntity> {
    @Override
    public TodoDto entityToDto(TodoEntity todoEntity) {
        return null;
    }

    @Override
    public TodoEntity dtoToEntity(TodoDto todoDto) {
        return null;
    }

    @Override
    public TodoDto todoServiceCreate(TodoDto todoDto) {
        return null;
    }

    @Override
    public List<TodoDto> todoServiceList() {
        return List.of();
    }

    @Override
    public TodoDto todoServiceFindById(Long id) {
        return null;
    }

    @Override
    public List<TodoDto> todoServiceFindByPriority(Priority priority) {
        return List.of();
    }

    @Override
    public List<TodoDto> todoServiceFindByStatus(Status status) {
        return List.of();
    }

    @Override
    public TodoDto todoServiceUpdateById(Long id, TodoDto todoDto) {
        return null;
    }

    @Override
    public TodoDto todoServiceDeleteById(Long id) {
        return null;
    }
}
