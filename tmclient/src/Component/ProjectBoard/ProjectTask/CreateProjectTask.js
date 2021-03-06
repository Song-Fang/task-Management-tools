import React,{Component} from 'react';
import {connect} from 'react-redux';
import {createProjectTask} from '../../../redux/action/projectTaskAction';
import {Link} from 'react-router-dom';

class CreateProjectTask extends Component{

    state = {
      summary:"",
      acceptanceCriteria:"",
      status:"",
      priority:0,
      dueDate:""
    }

    onChange = (event)=>{
      this.setState({[event.target.name]:event.target.value});
    }

    onSubmit = (event)=>{
      const {id} = this.props.match.params;
      event.preventDefault();
      const projectTask = {
        summary:this.state.summary,
        acceptanceCriteria:this.state.acceptanceCriteria,
        status:this.state.status,
        priority:this.state.priority,
        dueDate:this.state.dueDate
      }
      this.props.createProjectTask(projectTask,id,this.props.history);
    }

  render(){
    const {id} = this.props.match.params;
    return(
      <div className="add-PBI">
        <div className="container">
            <div className="row">
                <div className="col-md-8 m-auto">
                    <Link to={`/ProjectBoard/${id}`} className="btn btn-light">
                        Back to Project Board
                    </Link>
                    <h4 className="display-4 text-center">Add /Update Project Task</h4>
                    <p className="lead text-center">Project Name + Project Code</p>
                    <form onSubmit={this.onSubmit}>
                        <div className="form-group">
                            <input type="text" className="form-control form-control-lg" name="summary" placeholder="Project Task summary" value={this.state.summary} onChange={this.onChange}/>
                        </div>
                        <div className="form-group">
                            <textarea className="form-control form-control-lg" placeholder="Acceptance Criteria" name="acceptanceCriteria" value={this.state.acceptanceCriteria} onChange={this.onChange}></textarea>
                        </div>
                        <h6>Due Date</h6>
                        <div className="form-group">
                            <input type="date" className="form-control form-control-lg" name="dueDate" value = {this.state.dueDate} onChange = {this.onChange}/>
                        </div>
                        <div className="form-group">
                            <select className="form-control form-control-lg" name="priority" value = {this.state.priority} onChange = {this.onChange}>
                                <option value={0}>Select Priority</option>
                                <option value={1}>High</option>
                                <option value={2}>Medium</option>
                                <option value={3}>Low</option>
                            </select>
                        </div>

                        <div className="form-group">
                            <select className="form-control form-control-lg" name="status" value = {this.state.status} onChange={this.onChange}>
                                <option value="">Select Status</option>
                                <option value="TO_DO">TO DO</option>
                                <option value="IN_PROGRESS">IN PROGRESS</option>
                                <option value="DONE">DONE</option>
                            </select>
                        </div>

                        <input type="submit" className="btn btn-primary btn-block mt-4" />
                    </form>
                </div>
            </div>
        </div>
    </div>
    );
  }
}

export default connect(null,{createProjectTask})(CreateProjectTask);
