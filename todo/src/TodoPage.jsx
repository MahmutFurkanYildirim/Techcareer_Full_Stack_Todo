import React, { useEffect, useState } from "react";
import TodoApi from "./services/TodoApi";
//import TodoList from './Todo/TodoList';
import {
  MDBBtn,
  MDBCard,
  MDBCardBody,
  MDBCheckbox,
  MDBCol,
  MDBContainer,
  MDBInput,
  MDBListGroup,
  MDBListGroupItem,
  MDBRow,
  MDBTabs,
  MDBTabsItem,
  MDBTabsLink,
  MDBDropdown,
  MDBDropdownToggle,
  MDBDropdownMenu,
  MDBDropdownItem,
  MDBIcon,
} from "mdb-react-ui-kit";
//import HeaderComponent from './components/HeaderComponent';
import axios from "axios";

function TodoPage() {
  const [tasks, setTasks] = useState([]);
  const [filter, setFilter] = useState("All");
  const [newTaskText, setNewTaskText] = useState("");
  const [selectedPriority, setSelectedPriority] = useState(null);
  const [filteredTasks, setFilteredTasks] = useState([]);


  useEffect(() => {
    fetchTasks();
  }, []);

  useEffect(() => {
    if (filter === "Priority") {
      fetchPriorityTasks(selectedPriority);
    } else if (filter === "Done") {
      setFilteredTasks(tasks.filter((task) => task.completed));
    } else if (filter === "Pending") {
      setFilteredTasks(tasks.filter((task) => !task.completed));
    } else {
      setFilteredTasks(tasks);
    }
  }, [filter, selectedPriority]);

  const fetchTasks = async () => {
    try {
      const response = await TodoApi.todoApiList();
      setTasks(response.data);
      setFilteredTasks(response.data);
      console.log(tasks);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };

  const fetchPriorityTasks = (priority) => {
    TodoApi.todoApiFindByPriority(priority)
      .then((response) => {
        if (response.data.length === 0) {
          setFilteredTasks([]);
          console.log("There are no tasks with the selected priority.");
        } else {
          setFilteredTasks(response.data);
        }
      })
      .catch((error) => {
        console.error("Error fetching tasks by priority:", error);
      });
  };

  const addTask = (text, priority) => {
    const newTask = { todoDescription: text, todoPriority: priority, completed: false };
    TodoApi.todoApiCreate(newTask)
      .then((response) => {
        fetchTasks();
      })
      .catch((error) => {
        console.error("There was an error creating the task:", error);
      });
  };

  const toggleComplete = async (id) => {
    const task = await TodoApi.todoApiFindById(id);

    const updatedTask = { ...task, completed: !task.completed };

    await TodoApi.todoApiUpdateById(id, updatedTask);
    fetchTasks();
  };

  const updateTask = (id, text) => {
    const updatedTask = { ...tasks.find((task) => task.todoId === id), todoDescription: text };
    TodoApi.todoApiUpdateById(id, updatedTask).then(() => {
      fetchTasks();
      
    });
  };

  const removeTask = (id) => {
    TodoApi.todoApiDeleteById(id).then(() => {
      fetchTasks();
    });
  };

  const clearCompleted = () => {
    tasks.forEach((task) => {
      if (task.completed) {
        removeTask(task.todoId);
      }
    });
  };

  const clearAll = () => {
    const promises = tasks.map((task) =>
      TodoApi.todoApiDeleteById(task.todoId)
    );
    axios
      .all(promises)
      .then(
        axios.spread((...responses) => {
          fetchTasks();
        })
      )
      .catch((error) => {
        console.error("There was an error deleting the tasks:", error);
      });
  };

  const handlePriorityChange = (value) => {
    setSelectedPriority(value);
    fetchPriorityTasks(value);
    
  };

  return (
    <section className="gradient-custom vh-100">
      <MDBContainer className="py-5 h-100">
        <MDBRow className="d-flex justify-content-center align-items-center">
          <MDBCol xl="10">
            <MDBCard>
              <MDBCardBody className="p-5">
                {/* Input and Add button */}
                <div className="d-flex justify-content-center align-items-center mb-4">
                  <MDBInput
                    type="text"
                    id="form1"
                    label="New task..."
                    wrapperClass="flex-fill"
                    value={newTaskText}
                    onChange={(e) => setNewTaskText(e.target.value)}
                  />
                  <MDBDropdown>
                    <MDBDropdownToggle caret color="primary">
                      Select Priority
                    </MDBDropdownToggle>
                    <MDBDropdownMenu>
                      <MDBDropdownItem
                        onClick={() => handlePriorityChange("LOW")}
                      >
                        Low
                      </MDBDropdownItem>
                      <MDBDropdownItem
                        onClick={() => handlePriorityChange("MEDIUM")}
                      >
                        Medium
                      </MDBDropdownItem>
                      <MDBDropdownItem
                        onClick={() => handlePriorityChange("HIGH")}
                      >
                        High
                      </MDBDropdownItem>
                    </MDBDropdownMenu>
                  </MDBDropdown>
                  <MDBBtn
                    type="submit"
                    color="info"
                    className="ms-2"
                    onClick={() => addTask(newTaskText, selectedPriority)}
                  >
                    Add
                  </MDBBtn>
                </div>

                {/* Tabs for filtering */}
                <MDBTabs className="mb-4 pb-2">
                  <MDBTabsItem>
                    <MDBTabsLink
                      onClick={() => setFilter("All")}
                      active={filter === "All"}
                    >
                      All
                    </MDBTabsLink>
                  </MDBTabsItem>
                  <MDBTabsItem>
                    <MDBTabsLink
                      onClick={() => setFilter("Done")}
                      active={filter === "Done"}
                    >
                      Done
                    </MDBTabsLink>
                  </MDBTabsItem>
                  <MDBTabsItem>
                    <MDBTabsLink
                      onClick={() => setFilter("Pending")}
                      active={filter === "Pending"}
                    >
                      Pending
                    </MDBTabsLink>
                  </MDBTabsItem>
                  <MDBTabsItem>
                    <MDBDropdown>
                      <MDBDropdownToggle color="primary">
                        Select Priority
                      </MDBDropdownToggle>
                      <MDBDropdownMenu>
                        <MDBDropdownItem
                          onClick={() => handlePriorityChange("All")}
                        >
                          All
                        </MDBDropdownItem>
                        <MDBDropdownItem
                          onClick={() => handlePriorityChange("HIGH")}
                        >
                          High
                        </MDBDropdownItem>
                        <MDBDropdownItem
                          onClick={() => handlePriorityChange("MEDIUM")}
                        >
                          Medium
                        </MDBDropdownItem>
                        <MDBDropdownItem
                          onClick={() => handlePriorityChange("LOW")}
                        >
                          Low
                        </MDBDropdownItem>
                      </MDBDropdownMenu>
                    </MDBDropdown>
                  </MDBTabsItem>
                </MDBTabs>

                {/* List of tasks */}
                <MDBListGroup className="mb-5">
                  {/* Header */}
                  <MDBListGroupItem>
                    <div
                      style={{
                        display: "flex",
                        justifyContent: "space-between",
                      }}
                    >
                      <div>
                        <strong>Description</strong>
                      </div>
                      <div>
                        <strong>Priority</strong>
                      </div>
                      <div>
                        <strong>Action</strong>
                      </div>
                    </div>
                  </MDBListGroupItem>

                  {filteredTasks.map((task) => (
                    <MDBListGroupItem key={task.todoId}>
                      <div
                        style={{
                          display: "flex",
                          justifyContent: "space-between",
                          alignItems: "center",
                        }}
                      >
                        <div
                          onClick={() => toggleComplete(task.todoId)}
                          style={{ cursor: "pointer", width: "50%" }}
                        >
                          <div
                            style={{
                              textDecoration: task.completed
                                ? "line-through"
                                : "none",
                            }}
                          >
                            {task.todoDescription}
                          </div>
                        </div>
                        <div style={{ cursor: "pointer", width: "25%" }}>
                          <div
                            style={{
                              textDecoration: task.completed
                                ? "line-through"
                                : "none",
                            }}
                          >
                            {task.todoPriority}
                          </div>
                        </div>
                        <div
                          style={{
                            display: "flex",
                            justifyContent: "space-around",
                            width: "25%",
                          }}
                        >
                          <MDBIcon
                            icon="pencil-alt"
                            onClick={() =>
                              alert("Update functionality not implemented yet")
                            }
                            style={{ cursor: "pointer", marginRight: "10px" }}
                          />
                          <MDBIcon
                            icon="trash"
                            onClick={() => removeTask(task.todoId)}
                            style={{ cursor: "pointer", marginRight: "10px" }}
                          />
                          <MDBCheckbox
                            id={`checkbox-${task.todoId}`}
                            checked={task.completed}
                            onChange={() => toggleComplete(task.todoId)}
                          />
                        </div>
                      </div>
                    </MDBListGroupItem>
                  ))}
                </MDBListGroup>
                {/* Clear buttons */}
                <div className="d-flex justify-content-between">
                  <MDBBtn color="primary" onClick={clearCompleted}>
                    Clear Completed
                  </MDBBtn>
                  <MDBBtn color="danger" onClick={clearAll}>
                    Clear All
                  </MDBBtn>
                </div>
              </MDBCardBody>
            </MDBCard>
          </MDBCol>
        </MDBRow>
      </MDBContainer>
    </section>
  );
}

export default TodoPage;
