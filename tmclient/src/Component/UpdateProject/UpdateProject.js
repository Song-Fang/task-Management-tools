import React,{Component} from 'react';
import {connect} from 'react-redux';
import {findProject,createProject} from '../../redux/action/projectAction';
import classnames from 'classnames';

class UpdateProject extends Component{

  componentDidMount(){
    const {id} = this.props.match.params;
    this.props.findProject(id,this.props.history);
  }

  state={
    id:"",
    projectName:"",
    projectIdentifier:"",
    projectDescription:"",
    startDate:"",
    endDate:""
  }

  onChange=(event)=>{
    this.setState({
      [event.target.name]:event.target.value
    });
  }

  onSubmit=(event)=>{
    event.preventDefault();
    const project = {
      id:this.state.id,
      projectName:this.state.projectName,
      projectIdentifier:this.state.projectIdentifier,
      description:this.state.projectDescription,
      start_date:this.state.startDate,
      end_date:this.state.endDate
    }
    console.log(project);
    this.props.createProject(project,this.props.history);
  }

  componentWillReceiveProps(nextProps){

    this.setState({
      id:nextProps.showProject.project.id,
      projectName:nextProps.showProject.project.projectName,
      projectIdentifier:nextProps.showProject.project.projectIdentifier,
      projectDescription:nextProps.showProject.project.description,
      startDate:nextProps.showProject.project.start_date,
      endDate:nextProps.showProject.project.end_date
    });
  }

  render(){
    const project = this.state;
    const {error} = this.props;
    return(
      <div>
      <div className="project">
        <div className="container">
            <div className="row">
                <div className="col-md-8 m-auto">
                    <h5 className="display-4 text-center">Create / Edit Project form</h5>
                    <hr/>
                    <form onSubmit={this.onSubmit}>
                        <div className="invalid-feedback d-block">{error.projectName}</div>
                        <div className = "form-group">
                            <input type="text" className={classnames("form-control form-control-lg", {"is-invalid":error.projectName})} placeholder="Project Name" name="projectName" value={project.projectName}
                            onChange={this.onChange}
                            />
                        </div>

                        <h7 className="invalid-feedback d-block">{error.projectIdentifier}</h7>
                        <div className="form-group">
                            <input type="text" className={classnames("form-control form-control-lg", {"is-invalid":error.projectIdentifier})} placeholder="Unique Project ID"
                                name="projectIdentifier" value={project.projectIdentifier} disabled={true} />
                        </div>
                        <h7 className="invalid-feedback d-block">{error.description}</h7>
                        <div className="form-group">
                            <textarea className={classnames("form-control form-control-lg", {"is-invalid":error.description})} placeholder="Project Description" name="projectDescription" value={project.projectDescription}
                            onChange={this.onChange}
                            ></textarea>
                        </div>
                        <h6>Start Date</h6>
                        <div className="form-group">
                            <input type="date" className="form-control form-control-lg"  name="startDate" value= {project.startDate}
                            onChange={this.onChange}
                            />
                        </div>
                        <h6>Estimated End Date</h6>
                        <div className="form-group">
                            <input type="date" className="form-control form-control-lg"  name= "endDate" value={project.endDate}
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

function mapStateToProps(state){
  return ({
    showProject:state.getResult,
    error:state.errs
  });
}

export default connect(mapStateToProps,{findProject,createProject})(UpdateProject);
