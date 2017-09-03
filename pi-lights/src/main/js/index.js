import React from 'react';
import ReactDom from 'react-dom';
import AwesomeComponent from './AwesomeComponent.js';
import Bootstrap from 'bootstrap/dist/css/bootstrap.css';

class App extends React.Component {
  render() {
    return (
    		<div className="container">
    			<AwesomeComponent/>	
    		</div>
    );
  }
}

ReactDom.render( <App/>, document.getElementById('app') );
