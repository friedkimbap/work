/* Library.jsx
import React from "react";
import Book from "./Book";

function Library(props) {
    return (
        <div>
            <Book name="처음 만난 파이썬" numOfPage={300} />
            <Book name="처음 만난 AWS" numOfPage={400} />
            <Book name="처음 만난 리액트" numOfPage={500} />
        </div>
    );
}
export default Library;
*/
import Book from "./Book.js";

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

