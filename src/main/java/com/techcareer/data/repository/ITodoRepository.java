package com.techcareer.data.repository;

import com.techcareer.data.entity.TodoEntity;
import com.techcareer.role.Priority;
import com.techcareer.role.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// CrudRepository<RoleEntity,Long>
// JpaRepository<RoleEntity,Long>
// PagingAndSortingRepository<RoleEntity,Long>

@Repository
public interface ITodoRepository extends JpaRepository<TodoEntity,Long> {

    // Delivered Query (database query)
    // Find todoDescription in the database
    //select * from TodoList as t where t.todo_description
    //Optional<TodoEntity> findByTodoDescription(String todoDescription);
    //Find todoStatus in the database
    // select * from TodoList as t where t.todo_status
    List<TodoEntity> findByTodoStatus(Status todoStatus);
    //Find todoPriority in the database
    // select * from TodoList as t where t.todo_priority
    List<TodoEntity> findByTodoPriority(Priority todoPriority);

}//end ITodoRepository
