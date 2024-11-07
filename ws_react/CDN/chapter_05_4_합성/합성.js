/* 합성.jsx
function Welcome(props) {
    return <h1>Hello, {$props.name}</h1>
}

function App(props) {
    return (
        <div>
            <Welcome name="Mike" />
            <Welcome name="Steve" />
            <Welcome name="Jane" />
        </div>
    )
}

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(<App />);
*/

function Welcome(props) {
    return React.createElement("h1", null, "Hello, ", props.name);
}
function App(props) {
    return React.createElement("div", null, React.createElement(Welcome, {
        name: "Mike"
    }), React.createElement(Welcome, {
        name: "Steve"
    }), React.createElement(Welcome, {
        name: "Jane"
    }));
}
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(React.createElement(App));