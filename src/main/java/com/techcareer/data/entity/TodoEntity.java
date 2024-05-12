package com.techcareer.data.entity;

import com.techcareer.role.Priority;
import com.techcareer.role.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;

// LOMBOK
@Data // @Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@Builder
//TodoEntity
@Entity(name = "TodoList")
@Table(name = "todo")
public class TodoEntity implements Serializable {

    // SERILESTIRME
    public static final Long serialVersionUID = 1L;
    //todoID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private long todoId;
    //todoDescription
    @Column(name = "todo_description", nullable = false)
    private String todoDescription;
    //todoStatus
    @Column(name = "todo_status")
    private Status todoStatus = Status.INCOMPLETE;
    //todoPriority
    @Column(name = "todo_priority")
    private Priority todoPriority;
    //todoCreatedDate
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "todo_created_date")
    private Date todoCreatedDate;
    //todoUpdatedDate
    @Column(name = "todo_updated_date")
    private Date todoUpdatedDate;
}//end TodoEntity
