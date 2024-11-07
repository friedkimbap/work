
function CommentList(parent, props) {
    let newDIV1 = document.createElement("div");
    newDIV1.innerHTML = "<br>"+props[0].name + "<br>"+ props[0].comment;
    parent.appendChild(newDIV1);
    let newDIV2 = document.createElement("div");
    newDIV2.innerHTML = "<br>"+props[1].name + "<br>"+ props[1].comment;
    parent.appendChild(newDIV2);
    let newDIV3 = document.createElement("div");
    newDIV3.innerHTML = "<br>"+props[2].name + "<br>"+ props[2].comment;
    parent.appendChild(newDIV3);
}
export default CommentList;