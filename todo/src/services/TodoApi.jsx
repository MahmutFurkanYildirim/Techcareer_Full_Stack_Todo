import axios from "axios";

// proxy: http://localhost:4444/

//PERSIST
const TODO_API_PERSIST_URL = "/todolist/api/v1.0.0";

//TodoApi
class TodoApi {
  // CREATE To-do(Api)
  // http://localhost:4444/todolist/api/v1.0.0/create
  //@PostMapping("/create")
  todoApiCreate(todoDtoData) {
    return axios.post(`${TODO_API_PERSIST_URL}/create`, todoDtoData);
  } //end todoApiCreate

  // LIST To-do(Api)
  // http://localhost:4444/todolist/api/v1.0.0/list
  //@GetMapping("/list")
  todoApiList() {
    return axios.get(`${TODO_API_PERSIST_URL}/list`);
  } //end todoApiList

  // FIND To-do(Api)
  // http://localhost:4444/todolist/api/v1.0.0/find
  // http://localhost:4444/todolist/api/v1.0.0/find/0
  // http://localhost:4444/todolist/api/v1.0.0/find/1
  //@GetMapping({"/find","/find/{id}"})
  todoApiFindById(id) {
    return axios.get(`${TODO_API_PERSIST_URL}/find/${id}`);
  } //end todoApiFindById

  // FIND By-Priority To-do(Api)
  // http://localhost:4444/todolist/api/v1.0.0/find/priority
  //@GetMapping("/find/priority/{priority}")
  todoApiFindByPriority(priority) {
    return axios.get(`${TODO_API_PERSIST_URL}/find/priority/${priority}`);
  } //end todoApiFindByPriority

  // FIND By-Status To-do(Api)
  // http://localhost:4444/todolist/api/v1.0.0/find/status
  //@GetMapping("/find/status/{status}")
  todoApiFindByStatus(status) {
    return axios.get(`${TODO_API_PERSIST_URL}/find/status/${status}`);
  } //end todoApiFindByStatus

  // UPDATE To-do(Api)
  // http://localhost:4444/todolist/api/v1.0.0/update
  // http://localhost:4444/todolist/api/v1.0.0/update/0
  // http://localhost:4444/todolist/api/v1.0.0/update/1
  //@PutMapping({"/update","/update/{id}"})
  todoApiUpdateById(id, todoDto) {
    return axios.put(`${TODO_API_PERSIST_URL}/update/${id}`, todoDto);
  } //end todoApiUpdateById

  // DELETE To-do(Api)
  // http://localhost:4444/todolist/api/v1.0.0/delete
  // http://localhost:4444/todolist/api/v1.0.0/delete/0
  // http://localhost:4444/todolist/api/v1.0.0/delete/1
  //@DeleteMapping({"/delete","/delete/{id}"})
  todoApiDeleteById(id) {
    return axios.delete(`${TODO_API_PERSIST_URL}/delete/${id}`);
  } //end todoApiDeleteById

  // DELETE All To-dos (Api)
  // http://localhost:4444/todolist/api/v1.0.0/deleteAll
  //@DeleteMapping("/deleteAll")
  todoApiDeleteAll(){
    return axios.delete(`${TODO_API_PERSIST_URL}/deleteAll`)
  }

} //end class TodoApi

  

//Export default
export default new TodoApi();
