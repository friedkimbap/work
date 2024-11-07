/* render.jsx
function Welcome(props) {
    return <h1>안녕, {props.name}</h1>;
}

const element = <Welcome name = "인제" />;

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(element);
*/

function Welcome(props) {
    return React.createElement("h1", null, "안녕, ", props.name);
}
const element = React.createElement(Welcome, {
    name: "인제"
});

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(element);
