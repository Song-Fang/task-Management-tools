import React,{Component} from 'react';
import "bootstrap/dist/css/bootstrap.min.css";
import Header from './Layout/Header';
import './App.css';
import ProjectItem from './Project/ProjectItem';
import {Link} from 'react-router-dom';
import CreateProject from './CreateProject/CreateProject';
import {connect} from 'react-redux';
import {getProject} from '../redux/action/projectAction';


class DashBoard extends Component{

  componentDidMount(){
    this.props.getProject();
  }

  render(){
    const {projects} = this.props.getResults;

    return(
      <div>
        <div className="projects">
        <div className="container">
            <div className="row">
                <div className="col-md-12">
                    <h1 className="display-4 text-center">Projects</h1>
                    <br />
                    <Link to= '/CreateProject' className="btn btn-lg btn-info">
                        Create a Project
                    </Link>
                    <br />
                    <hr />

                    {
                      projects.map((ele)=><ProjectItem key={ele.id} project={ele}/>)
                    }

                    </div>
              </div>
          </div>
      </div>
      </div>

    );
  }
}

function mapStateToProps(state){
  return ({getResults:state.getResult});
}

export default connect(mapStateToProps,{getProject})(DashBoard);
