import React from "react";
import { withTranslation } from "react-i18next";
import HeaderComponent from "./components/HeaderComponent";
import FooterComponent from "./components/FooterComponent";
import { Navigate, Route,Routes } from "react-router-dom";
import MainComponent from "./components/MainComponent";

//FUNCTION COMPONENT TodoRouter
function TodoRouter() {
  return (
    <React.Fragment>
      <HeaderComponent logo ="fa-solid fa-clipboard-list"></HeaderComponent>
      <div className="container">
        <Routes>
            <Route path="/" element={<MainComponent />} />
            <Route path="/index" element={<MainComponent />} />

            <Route path={"*"} element={<Navigate to={"/"} />} />
        </Routes>
      </div>
      <FooterComponent></FooterComponent>
    </React.Fragment>
  );
}

export default withTranslation()(TodoRouter);
