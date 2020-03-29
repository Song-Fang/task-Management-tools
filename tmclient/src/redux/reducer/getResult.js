import {GET_PROJECT} from '../action/type';
import {FIND_PROJECT,DELETE_PROJECT} from '../action/type';

const initialState = {
  project:{},
  projects:[]
};

export default function(state = initialState,action){
  switch(action.type){
    case GET_PROJECT:
      return ({
        ...state,
        projects:action.payload
      });

    case FIND_PROJECT:
      return ({
        ...state,
        project:action.payload
      });

    case DELETE_PROJECT:
      return ({
        ...state,
        projects:state.projects.filter(project=>project.projectIdentifier!=action.payload)
        }
      );

    default:
      return state;
  }
}
