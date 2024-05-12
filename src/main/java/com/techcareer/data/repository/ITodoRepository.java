package com.techcareer.data.repository;

import com.techcareer.data.entity.TodoEntity;
import com.techcareer.role.Priority;
import com.techcareer.role.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// CrudRepository<RoleEntity,Long>
// JpaRepository<RoleEntity,Long>
// PagingAndSortingRepository<RoleEntity,Long>

@Repository
public interface ITodoRepository extends CrudRepository<TodoEntity,Long> {

    // Delivered Query (database query)
    // Find todoDescription in the database
    //select * from TodoList as t where t.todo_description
    Optional<TodoEntity> findByTodoDescription(String todoDescription);
    //Find todoStatus in the database
    // select * from TodoList as t where t.todo_status
    Optional<TodoEntity> findByTodoStatus(Status todoStatus);
    //Find todoPriority in the database
    // select * from TodoList as t where t.todo_priority
    Optional<TodoEntity> findByTodoPriority(Priority todoPriority);

}//end ITodoRepository
