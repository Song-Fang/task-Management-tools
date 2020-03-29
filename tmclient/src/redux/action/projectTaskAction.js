import axios from 'axios';

export const createProjectTask = (projectTask,backlogId,history)=> async dispatch=>{
  await axios.post(`http://localhost:8080/api/backlog/${backlogId}`,projectTask);
  history.push(`/ProjectBoard/${backlogId}`);
}
