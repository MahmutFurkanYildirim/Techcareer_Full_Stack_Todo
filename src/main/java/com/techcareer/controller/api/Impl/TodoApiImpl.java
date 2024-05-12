package com.techcareer.controller.api.Impl;

import com.techcareer.business.dto.TodoDto;
import com.techcareer.business.services.ITodoServices;
import com.techcareer.controller.api.ITodoApi;
import com.techcareer.error.ApiResult;
import com.techcareer.role.Priority;
import com.techcareer.role.Status;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

// Lombok
@RequiredArgsConstructor
@Log4j2

// API (REST)
@RestController
@RequestMapping("/todo/api/v1.0.0")
@CrossOrigin //CORS: Error
//@CrossOrigin(origins = ProjectUrl.REACT_FRONTEND_PORT_URL)
//@CrossOrigin(origins = "localhost:3000")
public class TodoApiImpl implements ITodoApi<TodoDto> {

    // Injection
    private final ITodoServices iTodoServices;

    // Error
    private ApiResult apiResult;

    // CREATE Todo(Api)
    // http://localhost:4444/todo/api/v1.0.0/create
    @PostMapping("/create")
    @Override
    public ResponseEntity<?> todoApiCreate(@Valid @RequestBody TodoDto todoDtoData) {
        TodoDto todoCreateApi=(TodoDto)iTodoServices.todoServiceCreate(todoDtoData);

        // If a null value comes while saving
        if(todoCreateApi==null){
            ApiResult apiResultCreate=ApiResult.builder()
                    .status(404)
                    .error("Todo Not Added")
                    .message("Todo Dto not found")
                    .path("localhost:4444/todo/api/v1.0.0/create")
                    .createdDate(new Date(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.status(404).body(apiResultCreate);
        }
        else if(todoCreateApi.getTodoId()==0){
            ApiResult apiResultCreate=ApiResult.builder()
                    .status(400)
                    .error("Todo Not Added")
                    .message("Todo Dto Bad Request")
                    .path("localhost:4444/todo/api/v1.0.0/create")
                    .createdDate(new Date(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.status(400).body(apiResultCreate);
        }
        log.info("Todo Api added");
        return ResponseEntity.status(201).body(todoCreateApi);
    }//end todoApiCreate

    // LIST Todo(Api)
    // http://localhost:4444/todo/api/v1.0.0/list
    @GetMapping("/list")
    @Override
    public ResponseEntity<List<TodoDto>> todoApiList() {
        log.info("Todo API Listed");
        return ResponseEntity.ok(iTodoServices.todoServiceList());
    }//end todoApiList

    // FIND Todo(Api)
    // http://localhost:4444/todo/api/v1.0.0/find
    // http://localhost:4444/todo/api/v1.0.0/find/0
    // http://localhost:4444/todo/api/v1.0.0/find/1
    @Override
    @GetMapping({"/find","/find/{id}"})
    public ResponseEntity<?> todoApiFindById(@PathVariable(name="id",required = false) Long id) {
        TodoDto roleFindApi=(TodoDto)iTodoServices.todoServiceFindById(id);
        if(roleFindApi==null){
            // Eğer kaydederken null değer gelirse
            ApiResult apiResultFind=ApiResult.builder()
                    .status(404)
                    .error("Todo Not Found")
                    .message("Todo Dto Not Found")
                    .path("localhost:4444/Todo/api/v1.0.0/find")
                    .createdDate(new Date(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.status(404).body(apiResultFind);
        }
        log.info("Todo API found");
        return ResponseEntity.ok(roleFindApi);
    }//end todoApiFindById

    // FIND By-Priority Todo(Api)
    // http://localhost:4444/todo/api/v1.0.0/find/priority
    @Override
    public ResponseEntity<List<?>> todoApiFindByPriority(@PathVariable Priority priority) {
        List<?> FindByPriority=iTodoServices.todoServiceFindByPriority(priority);
        log.info("Todo API Priority Listed");
        return ResponseEntity.ok(FindByPriority);
    }//end todoApiFindByPriority

    // FIND By-Priority Todo(Api)
    // http://localhost:4444/todo/api/v1.0.0/find/status
    @Override
    public ResponseEntity<List<?>> todoApiFindByStatus(@PathVariable Status status) {
        List<?> FindByStatus=iTodoServices.todoServiceFindByStatus(status);
        log.info("Todo API Status Listed");
        return ResponseEntity.ok(FindByStatus);
    }//end todoApiFindByStatus

    // UPDATE Todo(Api)
    // http://localhost:4444/todo/api/v1.0.0/update
    // http://localhost:4444/todo/api/v1.0.0/update/0
    // http://localhost:4444/todo/api/v1.0.0/update/1
    @Override
    @PutMapping({"/update","/update/{id}"})
    public ResponseEntity<?> todoApiUpdateById(@PathVariable(name="id",required = false) Long id, @Valid @RequestBody TodoDto todoDto) {
        TodoDto roleUpdateApi=(TodoDto)iTodoServices.todoServiceUpdateById(id,todoDto);
        if(roleUpdateApi==null){
            // Eğer kaydederken null değer gelirse
            ApiResult apiResultFind=ApiResult.builder()
                    .status(404)
                    .error("Todo Not Found")
                    .message("Role Dto Not Found")
                    .path("localhost:4444/todo/api/v1.0.0/update")
                    .createdDate(new Date(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.status(404).body(apiResultFind);
        }
        log.info("Role API Updated");
        return ResponseEntity.ok(roleUpdateApi);
    }//end todoApiUpdateById

    // DELETE Todo(Api)
    // http://localhost:4444/todo/api/v1.0.0/delete
    // http://localhost:4444/todo/api/v1.0.0/delete/0
    // http://localhost:4444/todo/api/v1.0.0/delete/1
    @Override
    @DeleteMapping({"/delete","/delete/{id}"})
    public ResponseEntity<?> todoApiDeleteById(@PathVariable(name="id",required = false) Long id) {
        TodoDto roleDto=(TodoDto)iTodoServices.todoServiceDeleteById(id);
        log.info("Role API Deleted");
        return ResponseEntity.ok(roleDto);
    }//end todoApiDeleteById
}
