// children이 여러 개의 element 인 경우
function Child3(props) {
    return React.createElement(
        "div", null,
        React.createElement("h1", null, "안녕, 리액트!"), 
        React.createElement("h2", null, "현재 시간: ", new Date().toLocaleTimeString())
    );
}
export default Child3;