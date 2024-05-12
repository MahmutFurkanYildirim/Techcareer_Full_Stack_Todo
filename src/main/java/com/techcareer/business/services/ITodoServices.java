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
    // TODO CRUD

    // CREATE Todo(Service)
    public D todoServiceCreate(D d);

    // LIST Todo(Service)
    public List<D> todoServiceList();

    // FIND BY-ID Todo(Service)
    public D todoServiceFindById(Long id);

    //TODO Priority ve Status Dto kisimlarini tekrardan goz gecir!!!!
    // FIND BY-PRIORITY Todo(Service)
    public List<D> todoServiceFindByPriority(Priority priority);

    // FIND BY-STATUS Todo(Service)
    public List<D> todoServiceFindByStatus(Status status);

    // UPDATE Todo(Service)
    public D todoServiceUpdateById(Long id,D d);

    // DELETE Todo(Service)
    public D todoServiceDeleteById(Long id);
}//end ITodoServices
