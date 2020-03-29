import React,{Component} from 'react';
import {createStore,applyMiddleware} from 'redux';
import reduxThunk from 'redux-thunk';
import reducer from './redux/reducer';


const initialState = {};
const store = createStore(reducer,initialState,applyMiddleware(reduxThunk));

export default store;
