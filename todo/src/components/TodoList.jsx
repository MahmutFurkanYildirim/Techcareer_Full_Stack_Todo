import React, { useEffect, useState } from "react";
import TodoApi from "../services/TodoApi";
import { Modal, Button } from "react-bootstrap";

function TodoList() {
  const [todos, setTodos] = useState([]);
  const [filteredTodos, setFilteredTodos] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [currentTodo, setCurrentTodo] = useState(null);
  const [updatedDescription, setUpdatedDescription] = useState('');
  const [updatedPriority, setUpdatedPriority] = useState('MEDIUM');
  const [completedTodos, setCompletedTodos] = useState([]);

  useEffect(() => {
    fetchTodoList();
  }, []);

  const fetchTodoList = () => {
    TodoApi.todoApiList()
      .then((response) => {
        if (response.status === 200) {
          setTodos(response.data);
          setFilteredTodos(response.data);
        }
      })
      .catch((err) => {
        console.error(err);
      });
  };

  const filterByStatus = (status) => {
    if (status === "All") {
      setFilteredTodos(todos);
    } else if (status === "Done") {
      const doneTodos = todos.filter((todo) => todo.todoStatus === "COMPLETE");
      setFilteredTodos(doneTodos);
    } else if (status === "Pending") {
      const pendingTodos = todos.filter((todo) => todo.todoStatus === "INCOMPLETE");
      setFilteredTodos(pendingTodos);
    }
  };

  const filterByPriority = (priority) => {
    const filteredByPriority = todos.filter(
      (todo) => todo.todoPriority === priority
    );
    setFilteredTodos(filteredByPriority);
  };

  const handleDelete = (id) => {
    if (window.confirm(`${id} id'li veriyi silmek istiyor musunuz?`)) {
      TodoApi.todoApiDeleteById(id)
        .then((response) => {
          if (response.status === 200) {
            setTodos(todos.filter((todo) => todo.todoId !== id));
            setFilteredTodos(
              filteredTodos.filter((todo) => todo.todoId !== id)
            );
          }
        })
        .catch((err) => {
          console.error(err);
          alert("Veri silinirken bir hata oluştu!");
        });
    } else {
      alert(`${id} nolu veri silinmedi!`);
    }
  };

  const handleComplete = async (id) => {
    try {
      const updatedTodos = todos.map((todo) =>
        todo.todoId === id ? { ...todo, todoStatus: todo.todoStatus === "COMPLETE" ? "INCOMPLETE" : "COMPLETE" } : todo
      );
      const updatedFilteredTodos = filteredTodos.map((todo) =>
        todo.todoId === id ? { ...todo, todoStatus: todo.todoStatus === "COMPLETE" ? "INCOMPLETE" : "COMPLETE" } : todo
      );
  
      // Frontend'deki state'i güncelle
      setTodos(updatedTodos);
      setFilteredTodos(updatedFilteredTodos);
  
      // Backend'e güncelleme isteği gönder
      const todoToUpdate = updatedTodos.find((todo) => todo.todoId === id);
      await TodoApi.todoApiUpdateById(id, todoToUpdate);
    } catch (err) {
      console.error("Update error:", err);
      alert("An error occurred while updating todo status. Please try again.");
    }
  };

  const handleDeleteDoneTasks = () => {
    const doneTasks = todos.filter((todo) => todo.todoStatus === "COMPLETE");
  
    Promise.all(doneTasks.map((todo) => TodoApi.todoApiDeleteById(todo.todoId)))
      .then((responses) => {
        const allSuccessful = responses.every((response) => response.status === 200);
        if (allSuccessful) {
          setTodos((prevTodos) => prevTodos.filter((item) => item.todoStatus === "INCOMPLETE"));
          setFilteredTodos((prevFilteredTodos) =>
            prevFilteredTodos.filter((item) => item.todoStatus === "INCOMPLETE")
          );
        } else {
          alert("Some todos could not be deleted.");
        }
      })
      .catch((err) => {
        console.error(err);
        alert("An error occurred while deleting completed tasks.");
      });
  };

  const handleDeleteAll = () => {
    if (window.confirm("Tüm verileri silmek istediğinize emin misiniz?")) {
      TodoApi.todoApiDeleteAll()
        .then((response) => {
          if (response.status === 200) {
            setTodos([]);
            setFilteredTodos([]);
            setFilteredTodos([]);
          }
        })
        .catch((err) => {
          console.error(err);
          alert("Veriler silinirken bir hata oluştu!");
        });
    }
  };

  const handleEdit = (todo) => {
    setCurrentTodo(todo);
    setUpdatedDescription(todo.todoDescription);
    setUpdatedPriority(todo.todoPriority);
    setShowModal(true);
  };

  const handleUpdateTodo = async () => {
    if (currentTodo) {
      const updatedTodo = {
        ...currentTodo,
        todoDescription: updatedDescription,
        todoPriority: updatedPriority,
      };

      try {
        const response = await TodoApi.todoApiUpdateById(currentTodo.todoId, updatedTodo);
        if (response.status === 200) {
          const updatedTodos = todos.map((todo) =>
            todo.todoId === currentTodo.todoId ? updatedTodo : todo
          );
          setTodos(updatedTodos);
          setFilteredTodos(updatedTodos);
          setShowModal(false);
        }
      } catch (err) {
        console.error("Update error:", err);
        alert("Todo could not be updated. Please try again.");
      }
    }
  };

  return (
    <div className="container mt-5">
      <h1 className="text-center">Todo List</h1>
      <div className="row mt-3">
        <div className="col-md-6">
          <div className="row">
            <div className="col-md-2">
              <button
                type="button"
                className="btn btn-info btn-block"
                onClick={() => filterByStatus("All")}
              >
                All
              </button>
            </div>
            <div className="col-md-2">
              <button
                type="button"
                className="btn btn-info btn-block"
                onClick={() => filterByStatus("Done")}
              >
                Done
              </button>
            </div>
            <div className="col-md-2">
              <button
                type="button"
                className="btn btn-info btn-block"
                onClick={() => filterByStatus("Pending")}
              >
                Pending
              </button>
            </div>
          </div>
        </div>
        <div className="col-md-2">
          <div className="dropdown">
            <button
              className="btn btn-info dropdown-toggle"
              type="button"
              id="priorityDropdown"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              Priority
            </button>
            <ul className="dropdown-menu" aria-labelledby="priorityDropdown">
              <li>
                <button
                  className ="dropdown-item"
                  onClick={() => filterByPriority("HIGH")}
                >
                  HIGH
                </button>
              </li>
              <li>
                <button
                  className="dropdown-item"
                  onClick={() => filterByPriority("MEDIUM")}
                >
                  MEDIUM
                </button>
              </li>
              <li>
                <button
                  className="dropdown-item"
                  onClick={() => filterByPriority("LOW")}
                >
                  LOW
                </button>
              </li>
            </ul>
          </div>
        </div>
      </div>
      <div className="card mt-3">
        <div className="card-body">
          <div className="row">
            <div className="col-md-4">
              <strong>Description</strong>
            </div>
            <div className="col-md-2">
              <strong>Priority</strong>
            </div>
            <div className="col-md-4">
              <strong>Action</strong>
            </div>
          </div>
          {filteredTodos.map((todo) => (
            <div className="row mt-2" key={todo.todoId}>
              <div
    className={`col-md-4 ${todo.todoStatus === "COMPLETE" ? "completed-todo" : ""}`}
    onClick={() => handleComplete(todo.todoId)}
    style={{ cursor: "pointer" }}
>
    <strong
        style={{
            textDecoration: todo.todoStatus === "COMPLETE" ? "line-through" : "none",
            color: todo.todoStatus === "COMPLETE" ? "red" : "inherit",
        }}
    >
        {todo.todoDescription}
    </strong>
</div>
              <div className="col-md-2">{todo.todoPriority}</div>
              <div className="col-md-4">
                <button
                  className="btn btn-sm btn-primary me-2"
                  onClick={() => handleEdit(todo)}
                >
                  Edit
                </button>
                <button
                  onClick={() => handleDelete(todo.todoId)}
                  className="btn btn-sm btn-danger me-2"
                >
                  Delete
                </button>
                <div className="form-check form-switch">
                  <input
                    className="form-check-input"
                    type="checkbox"
                    checked={todo.todoStatus === "COMPLETE"}
                    id={`completed-${todo.todoId}`}
                    onChange={() => handleComplete(todo.todoId)}
                  />
                  <label
                    className="form-check-label"
                    htmlFor={`completed-${todo.todoId}`}
                  >
                    Completed
                  </label>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
      <div className="mt-3">
        <button onClick={handleDeleteDoneTasks} className="btn btn-danger me-2">
          Delete Done Tasks
        </button>
        <button onClick={handleDeleteAll} className="btn btn-danger">
          Delete All Tasks
        </button>
      </div>

      {/* Modal for editing todo */}
      <Modal show={showModal} onHide={() => setShowModal(false)}>
        <Modal.Header closeButton>
          <Modal.Title>Edit Todo</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <div className="form-group">
            <label htmlFor="todoDescription">Todo Description</label>
            <input
              type="text"
              className="form-control"
              id="todoDescription"
              value={updatedDescription}
              onChange={(e) => setUpdatedDescription(e.target.value)}
            />
          </div>
          <div className="form-group mt-3">
            <label htmlFor="priority">Select Priority</label>
            <select
              className="form-control"
              id="priority"
              value={updatedPriority}
              onChange={(e) => setUpdatedPriority(e.target.value)}
            >
              <option value="HIGH">HIGH</option>
              <option value="MEDIUM">MEDIUM</option>
              <option value="LOW">LOW</option>
            </select>
          </div>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={() => setShowModal(false)}>
            Close
          </Button>
          <Button variant="primary" onClick={handleUpdateTodo}>
            Save Changes
          </Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
}

export default TodoList;