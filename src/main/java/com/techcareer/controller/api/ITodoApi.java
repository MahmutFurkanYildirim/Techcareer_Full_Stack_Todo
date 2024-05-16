package com.techcareer.controller.api;

import com.techcareer.role.Priority;
import com.techcareer.role.Status;
import org.springframework.http.ResponseEntity;

import java.util.List;

// API INTERFACE (ITodoApi)
// D: Dto
public interface ITodoApi<D> {

    // TO-DO CRUD
    // To-do Create
    public ResponseEntity<?> todoApiCreate(D d);

    // To-do List
    public ResponseEntity<List<D>> todoApiList();

    // To-do Find ID
    public ResponseEntity<?> todoApiFindById(Long id);

    // To-do Find Priority
    public ResponseEntity<List<?>> todoApiFindByPriority(Priority priority);

    //To-do Find Status
    public ResponseEntity<List<?>> todoApiFindByStatus(Status status);

    // To-do Update ID, Object
    public ResponseEntity<?> todoApiUpdateById(Long id, D d);

    // To-do Delete ID
    public ResponseEntity<?> todoApiDeleteById(Long id);
}
