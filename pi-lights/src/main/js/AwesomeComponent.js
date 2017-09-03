import React from 'react';

class AwesomeComponent extends React.Component {

  constructor(props) {
    super(props);
    
    this.onBlink = this.onBlink.bind(this);
    this.onOn = this.onOn.bind(this);
    this.onOff = this.onOff.bind(this);
  }

  onBlink () {
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
  }
  
  onOn () {
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
  }
  
  onOff () {
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
  }

  render() {
    return (
      <div>
        Pi Light TESTER
        <div><button onClick={this.onBlink}>Blink</button></div>
        <div><button onClick={this.onOn}>On</button></div>
        <div><button onClick={this.onOff}>Off</button></div>
      </div>
    );
  }

}

export default AwesomeComponent;
