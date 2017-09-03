import React from 'react';

class AwesomeComponent extends React.Component {
  constructor(props) {
    super(props);
    
   this.onBlink = this.onBlink.bind(this);
   this.onOn = this.onOn.bind(this);
   this.onOff = this.onOff.bind(this);
  }

  onBlink() {
	  fetch('./api/public/callService',{
		  method: 'POST',
		  headers: {  
		      "Content-type": "application/json"  
		    },
		  body: JSON.stringify({params:{action:'blink'}})
	  })
	  .then(function(data) {
		  console.log('Request succeeded with JSON response', data); 
	  })
	  .catch(function(error) {
		  console.log('Request failed', error);
	  });  
  };
  
  onOn() {
	  fetch('./api/public/callService',{
		  method: 'POST',
		  headers: {  
		      "Content-type": "application/json"  
		    },
		  body: JSON.stringify({params:{action:'on'}})
	  })
	  .then(function(data) {
		  console.log('Request succeeded with JSON response', data); 
	  })
	  .catch(function(error) {
		  console.log('Request failed', error);
	  });  
  };
  
  onOff() {
	  fetch('./api/public/callService',{
		  method: 'POST',
		  headers: {  
		      "Content-type": "application/json"  
		    },
		  body: JSON.stringify({params:{action:'off'}})
	  })
	  .then(function(data) {
		  console.log('Request succeeded with JSON response', data); 
	  })
	  .catch(function(error) {
		  console.log('Request failed', error);
	  });  
  };

  render() {
    return (
	    <div className="container" role="main">
	      <div className="row">
	      	<div className="col-sm-9">
		        <div>Pi Light TESTER 1</div>
		        <button type="button" className="btn btn-primary" onClick={this.onBlink}>Blink</button>
		        <button type="button" className="btn btn-secondary" onClick={this.onOn}>On</button>
		        <button type="button" className="btn btn-success" onClick={this.onOff}>Off</button>
	        </div>
	      </div>
	    </div>
    );
  }

}

export default AwesomeComponent;
