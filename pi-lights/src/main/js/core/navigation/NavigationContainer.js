import React, { Component } from 'react';
import Navigation from './Navigation.js'

class NavigationContainer extends Component {
	  constructor(props) {
	    super(props);
	  }
	  
	  render() {  
		  return (
				  <Navigation headerName={this.props.headerName}/>
		  );
	  }
}

export default NavigationContainer;