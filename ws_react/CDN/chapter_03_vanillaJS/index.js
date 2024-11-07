/* index.js in React
import Library from './Library.js';

const domContainer = document.getElementById('root');
ReactDOM.render(React.createElement(Library), domContainer);
*/
import Library from './Library.js';

const props = [{
    name: "처음 만난 파이썬",
    numOfPage: 300
}, {
    name: "처음 만난 AWS",
    numOfPage: 400
}, {
    name: "처음 만난 리액트",
    numOfPage: 500
}];

const  parent = document.getElementById('root');
Library(parent,props);
