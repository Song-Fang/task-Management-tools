import {GET_BACKLOGS,GET_PROJECT_TASK,DELETE_PROJECT_TASK} from '../action/type';

const initialState = {
  projectTask:{},
  projectTasks:[]
}

export default function(state = initialState, action){
  switch(action.type){
    case GET_BACKLOGS:
     return {
       ...state,
       projectTasks:action.payload
     }

    case GET_PROJECT_TASK:
      return {
        ...state,
        projectTask:action.payload
      }

    case DELETE_PROJECT_TASK:
      return {
        ...state
      }



    default:
      return state;


  }

}
