import React from "react";
import ReactDOM from "react-dom/client";
import reportWebVitals from "./reportWebVitals";
import "bootstrap/dist/css/bootstrap.min.css";
// Router
import { BrowserRouter } from "react-router-dom";

// Components
import TodoCreate from "./components/TodoCreate";
import TodoList from "./components/TodoList";

// ROOT - DOM
const root = ReactDOM.createRoot(document.getElementById("root"));

// RENDER
root.render(
  <React.StrictMode>
    <BrowserRouter>
      <div>
        <TodoCreate />
        <TodoList />
      </div>
    </BrowserRouter>
  </React.StrictMode>
);

reportWebVitals();
