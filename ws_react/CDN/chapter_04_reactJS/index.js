/*
  Clock2는 root div 객체 아래에 child 1개
  Clock은 root div 객체 아래에 child 1개 grandchild 2개
*/
import Clock from './Clock.js';
import Clock2 from './Clock2.js';

const root = ReactDOM.createRoot(document.getElementById('root'));
setInterval( () => {
  root.render(React.createElement(Clock));
},1000);