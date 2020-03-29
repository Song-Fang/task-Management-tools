import React from 'react';
import {combineReducers} from 'redux';
import errorReducer from './errorReducer';
import getResult from './getResult';
import backLogReducer from './backlogReducer';

export default combineReducers({
  errs:errorReducer,
  getResult:getResult,
  backLogReducer:backLogReducer
});
