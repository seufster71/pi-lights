import React from 'react';
import ReactDom from 'react-dom';
import NavigationContainer from './core/navigation/NavigationContainer.js';
import PiLightsContainer from './piSwitch/piLights/PiLightsContainer.js';
import Bootstrap from 'bootstrap/dist/css/bootstrap.css';
import Theme from './theme.css';

class App extends React.Component {

	constructor() {
	    super();
	    this.state = {
	      headerName: 'RaspBerry Pi Dragon',
	    };
	  }
  render() {
    return (
		<div>
			<NavigationContainer headerName={this.state.headerName}/>
			<PiLightsContainer/>
		</div>
    );
  }
}

ReactDom.render( <App/>, document.getElementById('app') );
