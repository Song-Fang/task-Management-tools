import React,{Component} from 'react';
import {createProject} from '../../redux/action/projectAction';
import {connect} from 'react-redux';
import PropTypes from 'prop-types';
import classnames from 'classnames';
import "bootstrap/dist/css/bootstrap.min.css";

class CreateProject extends Component{

  state={
    projectName:"",
    projectIdentifier:"",
    projectDescription:"",
    startDate:"",
    endDate:"",
    error:{}
  }

  onChange=(event)=>{
    this.setState({[event.target.name]:event.target.value})
  }

  onSubmit=(event)=>{
    event.preventDefault();
    const project = {
      projectName:this.state.projectName,
      projectIdentifier:this.state.projectIdentifier,
      description:this.state.projectDescription,
      start_date:this.state.startDate,
      end_date:this.state.endDate
    }

    this.props.createProject(project,this.props.history);
  }


  componentWillReceiveProps(nextProps){
    if(nextProps.error){
      this.setState({error:nextProps.error});
    }
  }

  render(){
    const {error} = this.state;
  return(
    <div>
    <div className="project">
      <div className="container">
          <div className="row">
              <div className="col-md-8 m-auto">
                  <h5 className="display-4 text-center">Create / Edit Project form</h5>
                  <hr />
                  <form onSubmit={this.onSubmit}>
                      <div className="invalid-feedback d-block">{error.projectName}</div>
                      <div className = "form-group">
                          <input type="text" className={classnames("form-control form-control-lg", {"is-invalid":error.projectName})} placeholder="Project Name" name="projectName" value={this.state.projectName}
                          onChange={this.onChange}
                          />
                      </div>

                      <h7 className="invalid-feedback d-block">{error.projectIdentifier}</h7>
                      <div className="form-group">
                          <input type="text" className={classnames("form-control form-control-lg", {"is-invalid":error.projectIdentifier})} placeholder="Unique Project ID"
                              name="projectIdentifier" value={this.state.projectIdentifier} onChange={this.onChange}/>
                      </div>
                      <h7 className="invalid-feedback d-block">{error.description}</h7>
                      <div className="form-group">
                          <textarea className={classnames("form-control form-control-lg", {"is-invalid":error.description})} placeholder="Project Description" name="projectDescription" value={this.state.projectDescription}
                          onChange={this.onChange}
                          ></textarea>
                      </div>
                      <h6>Start Date</h6>
                      <div className="form-group">
                          <input type="date" className="form-control form-control-lg"  name="startDate" value= {this.state.startDate}
                          onChange={this.onChange}
                          />
                      </div>
                      <h6>Estimated End Date</h6>
                      <div className="form-group">
                          <input type="date" className="form-control form-control-lg"  name= "endDate" value={this.state.endDate}
                          onChange={this.onChange}
                          />
                      </div>

                      <input type="submit" className="btn btn-primary btn-block mt-4" />
                  </form>
              </div>
          </div>
      </div>
  </div>
    </div>
  );
}


}

CreateProject.propTypes={
  createProject:PropTypes.func.isRequired,
  error:PropTypes.object.isRequired
}

function mapStateToProps(state){
  return ({error:state.errs});
}




export default connect(mapStateToProps,{createProject})(CreateProject);
