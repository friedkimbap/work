/* Clock.jsx
import React from "react";

function Clock(props) {
    return (
        <div>
            <h1>안녕, 리액트!</h1>
            <h2>현재 시간: {new Date().toLocaleTimeString()}</h2>
        </div>
    );
}

export default Clock;
*/

function Clock(props) {
  return React.createElement("div", null,
    React.createElement("h1",null,"제출자 : 김영민"), 
    React.createElement("h1", null, "안녕, 리액트!"), 
    React.createElement("h2", null, "현재 시간: ", new Date().toLocaleTimeString()));
}
export default Clock;