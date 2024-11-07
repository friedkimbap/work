import CommentList from './CommentList.js';

const props = [{
    name: "이인제",
    comment: "안녕하세요, 소플입니다."
}, {
    name: "유재석",
    comment: "리액트 재미있어요~!"
}, {
    name: "강민경",
    comment: "저도 리액트 배워보고 싶어요!!"
}];

const  parent = document.getElementById('root');
CommentList(parent,props);