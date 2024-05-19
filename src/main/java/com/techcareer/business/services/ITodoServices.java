package com.techcareer.business.services;

import com.techcareer.role.Priority;
import com.techcareer.role.Status;

import java.util.List;

//D: Dto
//E: Entity
public interface ITodoServices<D,E> {

    //MODEL MAPPER
    public D entityToDto(E e);
    public E dtoToEntity(D d);

    // ***************************************** //
    // TO-DO CRUD

    // CREATE To-do(Service)
    public D todoServiceCreate(D d);

    // LIST To-do(Service)
    public List<D> todoServiceList();

    // FIND BY-ID To-do(Service)
    public D todoServiceFindById(Long id);

    // FIND BY-PRIORITY To-do(Service)
    public List<D> todoServiceFindByPriority(Priority priority);

    // FIND BY-STATUS To-do(Service)
    public List<D> todoServiceFindByStatus(Status status);

    // UPDATE To-do(Service)
    public D todoServiceUpdateById(Long id,D d);

    // DELETE To-do(Service)
    public D todoServiceDeleteById(Long id);

    //DELETE ALL
    public void todoServiceDeleteAll();
}//end ITodoServices
