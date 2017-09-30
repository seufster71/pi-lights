import React from 'react';

export default function PiLights(props) {

    return (
	    <div className="container" role="main">
	      <div className="row">
	      	<div className="col-sm-9">
		        <div>Pi Light TESTER 1</div>
		        <button id="blink" type="button" className="btn btn-primary" onClick={(e) => props.onClick("blink",e)}>Blink</button>
		        <button id="on" type="button" className="btn btn-secondary" onClick={(e) => props.onClick("on",e)}>On</button>
		        <button id="off" type="button" className="btn btn-success" onClick={(e) => props.onClick("off",e)}>Off</button>
	        </div>
	      </div>
	    </div>
    );

}

PiLights.propTypes = {
		onClick: React.PropTypes.func.isRequired
};
