import express from "express";
import bodyParser from "body-parser";
import cors from "cors";
import fs from 'fs';
import url from 'url';

//const jsonFile = fs.readFileSync('./data_small.json', 'utf8');
let posts= [];
// posts = JSON.parse(jsonFile); // json파일 사용시 uncomment
posts = [
  {
      id: 1,
      title: "1t",
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
  },
  {
      id: 2,
      title: "2t",
      content: "2c ",
      comments: [
          {
              id: 21,
              content: "21c"
          }
      ]
  }
]

const app = express();
const corsOptions = {
  origin: '*', // 출처 허용 옵션: 모든 출처 허묭
  credential: true, //사용자인증이 필요한 리소스(쿠키등) 허용옵션: 허용
}
app.use(cors(corsOptions))
app.use(bodyParser.json());
// parsing json in body

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
app.use( (request, response) => {
  let pathname = url.parse(request.url);  
  pathname = url.parse(request.url).pathname;  
  let filename = pathname.substring(1,pathname.length);
  console.log('pathname ', pathname);
  if (pathname == '/') {
    fs.readFile('index.html', (error, data) => {
      response.writeHead(200, { 'Content-Type':'text/html' });
      response.end(data);
    });
  } else {
    fs.readFile(filename, (error, data) => {
      let extname = filename.split('.')[1];
      if (error) {
        response.writeHead(404, { 'Content-Type':'text/html' });
        response.end('<head><meta charset="UTF-8"></head>' 
	   + '<h1>요청한 파일이 없습니다.</h1>'
	   + '<br> <a href="/"> 홈으로' );
      } else if  (extname == 'html') {
        console.log('extname ', extname);
        response.writeHead(200, { 'Content-Type':'text/html' });
	//html파일은 Content-Type 지정하지 않으면
	//Browser화면에 tag실행이 되지 않고 tag가 글자로 표시
        response.end(data);
      } else {
	// css, js, gif, clock.mp3등 파일은
	// content-type 지정하지 않아도 정상 동작함
        response.writeHead(200);
	response.end(data);
      }
    });
  }
});
