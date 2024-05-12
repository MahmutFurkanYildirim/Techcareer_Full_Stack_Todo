package com.techcareer.business.dto;

import com.techcareer.role.Priority;
import com.techcareer.role.Status;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;
import java.util.Date;

// LOMBOK
@Data // @Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@Builder
//TodoDto
public class TodoDto implements Serializable {

    // SERILESTIRME
    public static final Long serialVersionUID = 1L;
    //todoID
    private long todoId;
    //todoDescription
    @NotEmpty(message = "{todo.Description.validation.constraints.NotNull.message}")
    private String todoDescription;
    //todoStatus
    @Builder.Default
    private String todoStatus = Status.INCOMPLETE.toString();
    //todoPriority
    private String todoPriority;
    //todoCreatedDate
    private Date todoCreatedDate;
    //todoUpdatedDate
    private Date todoUpdatedDate;

}//end TodoDto
