import React, {Component} from 'react';
import PropTypes from 'prop-types';
import FormBuilder from '../../coreView/common/form-builder';

export default function PlugModifyView({containerState, item, inputFields, appPrefs, 
	itemPrefForms, onSave, onCancel, inputChange, onBlur}) {

	let formName = "PLUG_FORM";
	let formTitle = "Plugs";
	let formGroup = "FORM1";
    
    return (
    	<FormBuilder containerState={containerState} item={item} formName={formName} formTitle={formTitle} formGroup={formGroup} inputFields={inputFields} appPrefs={appPrefs} prefForms={itemPrefForms} onSave={onSave} onCancel={onCancel} inputChange={inputChange}/>
    );
}


PlugModifyView.propTypes = {
  containerState: PropTypes.object,
  item: PropTypes.object,
  appPrefs: PropTypes.object.isRequired,
  itemPrefForms: PropTypes.object.isRequired,
  onSave: PropTypes.func,
  onCancel: PropTypes.func,
  inputChange: PropTypes.func,
  onBlur: PropTypes.func
};
