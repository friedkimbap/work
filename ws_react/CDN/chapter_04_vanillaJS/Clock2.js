/* Clock2.js in React
function Clock2(props) {
  return React.createElement("div", null, 'clock2 현재 시간:', new Date().toLocaleTimeString());
}
export default Clock2;
*/

// 웹기초 8.4 HTML 문서의 동적구성
// DOM 객체 동적 생성
// DOM 트리에 삽입
// DOM 객체의 삭제

function Clock2(parent) {
  let newDIV1 = document.createElement("div");
  newDIV1.innerHTML = "clock2 현재 시간:" + new Date().toLocaleTimeString();
  parent.appendChild(newDIV1);
}
export default Clock2;