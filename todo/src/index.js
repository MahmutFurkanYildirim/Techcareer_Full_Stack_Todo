import React from "react";
import ReactDOM from "react-dom/client";
import reportWebVitals from "./reportWebVitals";
import "bootstrap/dist/css/bootstrap.min.css";
// Router
import { BrowserRouter } from "react-router-dom"; // React Router kullanarak yönlendirme işlemleri için

// Components
import TodoCreate from "./components/TodoCreate";
import TodoList from "./components/TodoList";

// ROOT - DOM
const root = ReactDOM.createRoot(document.getElementById("root")); // React uygulamasının kök DOM elemanını seç

// RENDER
root.render(
  <React.StrictMode>
    <BrowserRouter>
      <div>
         <TodoCreate /> {/*TodoCreate bileşenini render et */}
        <TodoList />  {/*TodoList  bileşenini render et */}
      </div>
    </BrowserRouter>
  </React.StrictMode>
);

reportWebVitals();
