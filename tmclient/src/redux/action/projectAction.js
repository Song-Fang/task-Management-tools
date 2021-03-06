import axios from 'axios';
import {GET_ERRORS,GET_PROJECT,FIND_PROJECT,DELETE_PROJECT} from './type';

export const createProject = (project,history)=> async dispatch=>{
  try{
    const res = await axios.post("http://localhost:8080/api/project",project);
    history.push("/DashBoard");
    dispatch({
      type:GET_ERRORS,
      payload:{}});
  }catch(err){
    dispatch({
      type:GET_ERRORS,
      payload:err.response.data
    });
  }
}

export const getProject = ()=>async dispatch=>{

  const res = await axios.get("http://localhost:8080/api/project/all");
  dispatch({
    type: GET_PROJECT,
    payload:res.data
  })
}

export const findProject = (id,history)=>async dispatch=>{
    try{
      const res = await axios.get(`http://localhost:8080/api/project/${id}`);
        dispatch({
          type:FIND_PROJECT,
          payload:res.data
        })
    }catch(err){
      history.push("/DashBoard");
    }
}

export const deleteProject = (id) =>async dispatch=>{
    await axios.delete(`http://localhost:8080/api/project/${id}`);
    dispatch({
      type:DELETE_PROJECT,
      payload:id
    })
}
