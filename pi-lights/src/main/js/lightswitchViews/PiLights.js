import React, { Component } from 'react';

class PiLights extends Component {
	constructor(props) {
	    super(props);
	    
	}
	  
	onPress(param) {
		this.props.onClick(param)
	};
	
	render() {  
	    return (
		    <div className="container" role="main">
		      <div className="row">
		      	<div className="col-sm-9">
			        <div>Pi Light TESTER 1</div>
			        <button type="button" className="btn btn-primary" onClick={this.onPress.bind(this,"blink")}>Blink</button>
			        <button type="button" className="btn btn-secondary" onClick={this.onPress.bind(this,"on")}>On</button>
			        <button type="button" className="btn btn-success" onClick={this.onPress.bind(this,"off")}>Off</button>
		        </div>
		      </div>
		    </div>
	    );
	}

}

export default PiLights;
