import React, {Component} from 'react';
import DashBoard from './DashBoard';
import Header from './Layout/Header';
import {BrowserRouter,Route} from 'react-router-dom';
import CreateProject from './CreateProject/CreateProject';
import {Provider} from 'react-redux';
import UpdateProject from './UpdateProject/UpdateProject';
import store from '../store';
import ProjectBoard from './ProjectBoard/ProjectBoard';
import CreateProjectTask from './ProjectBoard/ProjectTask/CreateProjectTask';



class App extends Component{
  render(){
    return (
      <Provider store={store}>
        <div>
          <Header/>
            <BrowserRouter>
              <Route  exact path='/DashBoard' component = {DashBoard} />
              <Route exact path='/CreateProject' component = {CreateProject} />
              <Route exact path='/UpdateProject/:id' component = {UpdateProject} />
              <Route exact path='/ProjectBoard/:id' component={ProjectBoard} />
              <Route exact path = '/CreateProjectTask/:id' component ={CreateProjectTask} />
            </BrowserRouter>
        </div>
      </Provider>
    );
  }
}


export default App;
