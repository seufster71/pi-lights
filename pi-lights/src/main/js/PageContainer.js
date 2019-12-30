import React, { Component } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { Switch, Route, withRouter} from "react-router";
import { bindActionCreators } from "redux";

import PiLightsContainer from "./piSwitch/piLights/PiLightsContainer";


class PageContainer extends Component {
	constructor(props) {
		super(props);
	}

	componentDidUpdate() {
		
		if (this.props.session.sessionActive == true && this.props.session.status === 'JUST_LOGGEDIN') {
			this.props.dispatch({ type: "CLEAR_SESSION_LOGIN" });
			this.props.history.replace("/member");
		}
	}
  
  render() {
    
    if (this.props.session.sessionActive == true) {
     return (
      <Switch>

      </Switch>

      );
    } else {
      return (
        <div>
        	Test
          <Switch>
            <Route exact path="/" component={PiLightsContainer}/>
          </Switch>
        </div>
      );
    }
  }
}

PageContainer.propTypes = {
  appPrefs: PropTypes.object.isRequired,
  appMenus: PropTypes.object,
  lang: PropTypes.string,
  actions: PropTypes.object,
  session: PropTypes.object,
  history: PropTypes.object
};

function mapStateToProps(state, ownProps) {
  return {
    appMenus: state.appMenus,
    lang: state.lang,
    appPrefs: state.appPrefs,
    navigation: state.navigation,
    session:state.session
  };
}

export default withRouter(connect(mapStateToProps)(PageContainer));
