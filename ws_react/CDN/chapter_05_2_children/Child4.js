// children이 여러개의 string 인 경우
function Child4(props) {
    return React.createElement(
        "div", null, "현재 시간: ", new Date().toLocaleTimeString()
    );
}
export default Child4;