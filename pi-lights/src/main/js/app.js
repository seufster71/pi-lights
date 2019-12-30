import React, { Component } from 'react';
import { render } from 'react-dom';
import configureStore from "./store/configureStore";
import { Provider } from "react-redux";
import { BrowserRouter } from "react-router-dom";
import PageContainer from "./PageContainer.js";
import Bootstrap from 'bootstrap/dist/css/bootstrap.css';
import Theme from './theme.css';

if (process.env.NODE_ENV !== "production") {
	  console.log("Looks like we are in development mode!");
}

const store = configureStore();


render(
	<Provider store={store}>
		<BrowserRouter>
			<PageContainer />
		</BrowserRouter>
	</Provider>,
	document.getElementById("app")
);

export function getDebugClient() {
	  let state = store.getState();
	  return state.appPrefs.debugClient;
	}
	export function getHost() {
	  return "";
	}
