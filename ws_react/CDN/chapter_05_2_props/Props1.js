// props가 객체인 경우
function Props1(props) {
    return React.createElement(
        "div",
        {
            name: "소플",
            introduction: "안녕하세요, 소플입니다.",
            viewcount: 1500
        },
        null
    );
}
export default Props1;