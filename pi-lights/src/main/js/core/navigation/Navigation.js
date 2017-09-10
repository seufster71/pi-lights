import React from 'react';
import NavbarHeader from './NavbarHeader.js'
import NavbarMenu from './../../navigationViews/NavbarMenu.js'

const Navigation = (props) => {
  return (

	<nav className="navbar navbar-inverse navbar-fixed-top">
      <div className="container">
        <NavbarHeader headerName={props.headerName}/>
        <NavbarMenu/>
      </div>
    </nav>
    
  );
}

export default Navigation;