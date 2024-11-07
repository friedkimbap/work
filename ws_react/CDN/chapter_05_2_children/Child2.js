// children이 null 인 경우
function Child2(props) {
    return React.createElement(
        "div",
        {
            name: "소플"
        },
        null
    );
}
export default Child2;