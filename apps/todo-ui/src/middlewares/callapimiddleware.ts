import { Dispatch } from 'react'
import { ActionMessage } from "../constants/ActionMessage"
import { RootState } from '../containers'
import { AnyAction } from 'redux'
/* API action */
export interface ApiActionMessage{
  // Types of actions to emit before and after
  types:[string,string,string];
  // API request parameters:
  callAPI:[RequestInfo,RequestInit];
  // Arguments to inject in begin/end actions
  payload: any;
  json:boolean;
}

export function callAPIMiddleware({ dispatch }:{dispatch:Dispatch<ActionMessage>, getState:()=>RootState}) {
  return (next:(Dispatch<AnyAction>)) => (action:ApiActionMessage) => {
    const { types, callAPI, payload = {}, json } = action
    if (!types) {
      // Normal action: pass it on
      return next(action as any)
    }
    if (
      !Array.isArray(types) ||
      types.length !== 3 ||
      !types.every(type => typeof type === 'string')
    ) {
      throw new Error('Expected an array of three string types.')
    }   
    const [requestType, successType, failureType] = types
    dispatch(
      Object.assign({}, payload, {
        type: requestType
      })
    )
    return fetch(callAPI[0],callAPI[1]).then(
      (response:Response) =>{
        return json ? response.json(): undefined;
      }).then((body:any)=>{
        return dispatch(
          Object.assign({}, payload, {
            json: body,
            type: successType
          })
        )
      }).catch((error:any) =>{
        console.error(error);
        dispatch(
          Object.assign({}, payload, {
            error,
            type: failureType
          })
        )
        })
  }
}
