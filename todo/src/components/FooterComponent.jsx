import { withTranslation } from "react-i18next";
import React, { Component } from "react";

//CLASS COMPONENT FOOTER
class FooterComponent extends Component {
  //Constructor
  constructor(props) {
    super(props);

    //STATE
    this.state = {};

    //BIND
    //this.methodAdi = this.methodAdi.bind(this);
  }

  //CDM

  //METHOD

  render() {
    return <div>Footer</div>;
  }
} //end class Footer

//export
export default withTranslation()(FooterComponent);
