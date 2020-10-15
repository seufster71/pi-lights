import React, { Component } from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import PiLights from './../../piSwitchViews/PiLights.js'
import {getHost} from '../../app';

// test
class PlugContainer extends Component {
	  constructor(props) {
	    super(props);

	    this.onButtonPress = this.onButtonPress.bind(this);
	  }

	  onButtonPress(param) {

		  console.log("pros " + param)
		  
		  //const uri = getHost()+params.URI;
		  fetch('http://localhost:8080/api/public/callService',{
			  method: 'POST',
			  headers: {
			      "Content-type": "application/json"
			    },
			  body: JSON.stringify({params:{action:param}})
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
		    	<PiLights onClick={this.onButtonPress}/>
		    );
	  }
}

function mapStateToProps(state, ownProps) {
  return {
    lang: state.lang,
  };
}

export default connect(mapStateToProps)(PlugContainer);