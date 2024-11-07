/* index.js in React
import Clock2 from './Clock2.js';

const root = ReactDOM.createRoot(document.getElementById('root'));
setInterval( () => {
  root.render(React.createElement(Clock));
},1000);
*/

import Clock2 from './Clock2.js';
import Clock3 from './Clock3.js';


const parent = document.getElementById('root');
let newDIV1 = document.createElement("div");
newDIV1.innerHTML = "clock2 현재 시간:" + new Date().toLocaleTimeString();
parent.appendChild(newDIV1);
/*
setInterval( () => {
  Clock2(parent);
},1000);
*/

setInterval(() => {
  Clock3(parent);
}, 1000);
