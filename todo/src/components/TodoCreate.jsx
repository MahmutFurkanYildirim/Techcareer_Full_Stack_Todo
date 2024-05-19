// TodoCreate.js
import React, { useState } from "react";
import TodoApi from "../services/TodoApi";

const TodoCreate = ({ t, i18n, props }) => {
  // Durumları (state'leri) tanımla
  const [todoDescription, setTodoDescription] = useState("");
  const [todoPriority, setPriority] = useState("MEDIUM");
  const [error, setError] = useState(null);
  const [isSubmitting, setIsSubmitting] = useState(false);

  // Todo açıklaması değiştiğinde çağrılacak fonksiyon
  const handleTodoNameChange = (e) => {
    setTodoDescription(e.target.value);
  };

  // Öncelik değiştiğinde çağrılacak fonksiyon
  const handlePriorityChange = (e) => {
    setPriority(e.target.value);
  };

  // Form gönderildiğinde çağrılacak fonksiyon
  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(null);
    setIsSubmitting(true);

    // Todo verilerini oluştur
    const todoDto = {
      todoDescription,
      todoPriority,
    };

    try {
      // Todo oluşturulma API çağrısını yap
      const response = await TodoApi.todoApiCreate(todoDto);
       // Başarılı bir şekilde oluşturulduysa
      if (response.status === 201) {
        // Formu sıfırla ve sayfayı yeniden yükle
        setTodoDescription("");
        setPriority("MEDIUM");
        window.location.reload(true);
      }
    } catch (err) {
      // Hata durumunda, hatayı konsola yazdır ve kullanıcıya bildir
      console.error("API hatası:", err.response || err);
      setError("Todo could not be created. Please try again.");
    } finally {
       // İşlemi bitir (başarılı ya da başarısız olması fark etmeksizin)
      setIsSubmitting(false);
    }
  };

  return (
    <div className="container mt-5">
      <h1 className="text-center">Create to-do</h1>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="todoName">Enter description</label>
          <input
            type="text"
            className={`form-control ${error ? "is-invalid" : ""}`}
            id="todoDescription"
            name="todoDescription"
            value={todoDescription}
            onChange={handleTodoNameChange}
            placeholder="Enter Todo Description"
            required
          />
          {error && <div className="invalid-feedback">{error}</div>}
        </div>

        <div className="form-group mt-3">
          <label htmlFor="priority">Select priority</label>
          <select
            className="form-control"
            id="priority"
            name="priority"
            value={todoPriority}
            onChange={handlePriorityChange}
          >
            <option value="HIGH">HIGH</option>
            <option value="MEDIUM">MEDIUM</option>
            <option value="LOW">LOW</option>
          </select>
        </div>

        <button
          type="submit"
          className="btn btn-primary mt-3"
          disabled={isSubmitting}
        >
          {isSubmitting ? "Adding..." : "Add"}
        </button>
      </form>
    </div>
  );
};

export default TodoCreate;