import React from 'react';
import ReactDom from 'react-dom';
import Header from './navigation/Header.js';
import AwesomeComponent from './lightswitch/AwesomeComponent.js';
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
			<Header headerName={this.state.headerName}/>
			<AwesomeComponent/>
		</div>
    );
  }
}

ReactDom.render( <App/>, document.getElementById('app') );
