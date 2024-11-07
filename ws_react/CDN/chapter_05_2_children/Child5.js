// children이 1개의 string 인 경우
function Child5(props) {
    return React.createElement(
        "div", null, "현재 시간: " + new Date().toLocaleTimeString()
    );
}
export default Child5;