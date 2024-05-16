package com.techcareer.data.repository;

import com.techcareer.data.entity.TodoEntity;
import com.techcareer.role.Priority;
import com.techcareer.role.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

// CrudRepository<RoleEntity,Long>
// JpaRepository<RoleEntity,Long>
// PagingAndSortingRepository<RoleEntity,Long>

@Repository
public interface ITodoRepository extends JpaRepository<TodoEntity,Long> {

    // Delivered Query (database query)
    //Find todoStatus in the database
    // select * from TodoList as t where t.todo_status
    List<TodoEntity> findByTodoStatus(Status todoStatus);
    //Find todoPriority in the database
    // select * from TodoList as t where t.todo_priority
    List<TodoEntity> findByTodoPriority(Priority todoPriority);

}//end ITodoRepository
