import express from "express";
import bodyParser from "body-parser";
import cors from "cors";

const app = express();
const corsOptions = {
  origin: '*', // 출처 허용 옵션: 모든 출처 허묭
  credential: true, //사용자인증이 필요한 리소스(쿠키등) 허용옵션: 허용
}
app.use(cors(corsOptions))
app.use(bodyParser.json());
// parsing json in body

{/*본인이 작성하라*/}

let posts= [];
// posts = JSON.parse(jsonFile); // json파일 사용시 uncomment
posts = [
  {
      id: 1,
      title: "김영민",
      content: "1c", 
      comments: [
          {
              id: 11,
              content: "11c"
          },
          {
              id: 12,
              content: "12c"
          }
      ]
  }
]

app.listen({ port: 8000 }, () => {
  console.log("Blog 서버가 localhost:8000 에서 실행됩니다...");
});

// 전체 post 조회
app.get("/api/v1/posts", (req, res) => {
  res.send(posts).status(200);
});
/*
res.send(JSobject) 인 경우
RP header : content-type : application/json
RP body : [{"id":1,"title":"t","content":"c","comments":[]}, {"id":2,...}]
*/
// 특정 post 1개 조회
app.get("/api/v1/posts/:pid", (req, res) => {
  const post = posts.find((post) => post.id == req.params.pid);
  if(typeof post === 'undefined') {
    res.sendStatus(404);
  } else {
    res.send(post);
  } 
});
// 신규 post insert
app.post("/api/v1/posts", (req, res) => {
  const post = req.body;
  const length = posts.length;
  post.id = length+1;
  posts.push(post);
  res.sendStatus(200);
});
// 신규 comment insert
app.post("/api/v1/posts/:pid", (req, res) => {
  const inPost = req.body;
  console.log("before inPost.comments ", inPost.comments);
  const post = posts.find((post) => post.id == req.params.pid);
  if(typeof post === 'undefined') {
    res.sendStatus(404);
  } else {
    const length = post.comments.length;
    const preComments = posts[post.id-1].comments;
    inPost.comments[0].id = inPost.id * 10 + length + 1;
    console.log("after inPost.comments ", inPost.comments);
    console.log("before posts[post.id-1] ", posts[post.id-1]);
    preComments.push(inPost.comments[0]);
    inPost.comments = preComments;
    posts[post.id-1]= inPost;
    console.log("after posts[post.id-1] ", posts[post.id-1]);
    res.sendStatus(200);
  } 
});
// 나머지 path
app.use((err, req, res, next) => {
  res.status(500).send(err.message);
});

