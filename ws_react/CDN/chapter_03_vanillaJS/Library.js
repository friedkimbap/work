/* Libray.js in React
import React from "react";
import Book from "./Book";

function Library(props) {
    return React.createElement("div", null, 
        React.createElement(Book, {
      name: "처음 만난 파이썬",
      numOfPage: 300
    }), React.createElement(Book, {
      name: "처음 만난 AWS",
      numOfPage: 400
    }), React.createElement(Book, {
      name: "처음 만난 리액트",
      numOfPage: 500
    }));
  }
export default Library;
*/

// 웹기초 8.4 HTML 문서의 동적구성
// DOM 객체 동적 생성
// DOM 트리에 삽입
// DOM 객체의 삭제

function Library(parent,props) {
    let newDIV1 = document.createElement("div");
    newDIV1.innerHTML = "<br>책이름 : "+ props[0].name + "<br>페이지수 : "+ props[0].numOfPage;
    parent.appendChild(newDIV1);
    let newDIV2 = document.createElement("div");
    newDIV2.innerHTML = "<br>책이름 : "+ props[1].name + "<br>페이지수 : "+ props[1].numOfPage;
    parent.appendChild(newDIV2);
    let newDIV3 = document.createElement("div");
    newDIV3.innerHTML = "<br>책이름 : "+ props[2].name + "<br>페이지수 : "+ props[2].numOfPage;
    parent.appendChild(newDIV3);
}
export default Library;