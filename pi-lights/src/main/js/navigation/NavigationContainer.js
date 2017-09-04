import React from 'react';
import Navigation from './Navigation.js'

class NavigationContainer extends React.Component {
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