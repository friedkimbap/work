/* Clock2.js in vanillaJS
function Clock2(obj) {
  let newDIV1 = document.createElement("div");
  newDIV1.innerHTML = "clock2 현재 시간:"+ new Date().toLocaleTimeString();
  obj.appendChild(newDIV1);
}
export default Clock2;
*/

// 웹기초 8.4 HTML 문서의 동적구성
// DOM 객체 동적 생성
// DOM 트리에 삽입
// DOM 객체의 삭제

function Clock3(parent) {
  // Create new node:
  const newNode = document.createElement("div");
  newNode.innerHTML = "clock3 현재 시간:" + new Date().toLocaleTimeString();
  // Replace old node:
  parent.replaceChild(newNode, parent.childNodes[0]);
}
export default Clock3;