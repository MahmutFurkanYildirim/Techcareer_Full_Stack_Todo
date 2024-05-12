package com.techcareer.controller.api;

import com.techcareer.role.Priority;
import com.techcareer.role.Status;
import org.springframework.http.ResponseEntity;

import java.util.List;

// API INTERFACE (ITodoApi)
// D: Dto
public interface ITodoApi<D> {

    // TODO CRUD
    // Todo Create
    public ResponseEntity<?> todoApiCreate(D d);

    // Todo List
    public ResponseEntity<List<D>> todoApiList();

    // Todo Find ID
    public ResponseEntity<?> todoApiFindById(Long id);

    // Todo Find Priority
    public ResponseEntity<List<?>> todoApiFindByPriority(Priority priority);

    //Todo Find Status
    public ResponseEntity<List<?>> todoApiFindByStatus(Status status);

    // Todo Update ID, Object
    public ResponseEntity<?> todoApiUpdateById(Long id, D d);

    // Todo Delete ID
    public ResponseEntity<?> todoApiDeleteById(Long id);
}
