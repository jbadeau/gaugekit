import React from 'react'
import Footer from './Footer'
import VisibleTodoList from '../containers/VisibleTodoList'
import * as TodoActions from '../actions'

const MainSection:React.FunctionComponent<MainSectionProps> = ({ todosCount, completedCount, actions }:MainSectionProps) =>
  (
    <section className="main">
      {
        !!todosCount && 
        <span>
          <input
            className="toggle-all"
            type="checkbox"
            checked={completedCount === todosCount}
            readOnly
          />
          <label onClick={actions.completeAllTodos}/>
        </span>
      }
      <VisibleTodoList />
      {
        !!todosCount &&
        <Footer
          completedCount={completedCount}
          activeCount={todosCount - completedCount}
          onClearCompleted={actions.clearCompleted}
        />
      }
    </section>
  )

export interface MainSectionProps{
  todosCount: number;
  completedCount: number;
  actions: typeof TodoActions
}

export default MainSection;