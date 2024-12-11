import express from "express";
import bodyParser from "body-parser";
import cors from "cors";

const todo_db = {
  todos: [
    {
      id: 1,
      task: "task 1",
    },
    {
      id: 2,
      task: "task 2",
    },
  ]
};

const app = express();
const corsOptions = {
  origin: '*', // 출처 허용 옵션: 모든 출처 허묭
  credential: true, //사용자인증이 필요한 리소스(쿠키등) 허용옵션: 허용
}
app.use(cors(corsOptions))
app.use(bodyParser.json());
// parsing json in body

app.listen({ port: 8000 }, () => {
  console.log("todos REST API 서버가 localhost:8000 에서 실행됩니다...");
});

// 할일 전체 보기
app.get("/api/v1/todos", (req, res) => {
  res.send(todo_db.todos).status(200);
});
/*
res.send(JSobject) 인 경우
RP header : content-type : application/json
RP body : [{"id":1,"task":"자바스크립트 다시 보기"}, {"id":2,"task":"..."}]
*/
// 1가지 할일 보기
app.get("/api/v1/todos/:id", (req, res) => {
  const todo = todo_db.todos.find((todo) => todo.id == req.params.id);
  if(typeof todo === 'undefined') {
    res.sendStatus(404);
  } else {
    res.send(todo);
  } 
});
// 할일 등록
app.post("/api/v1/todos", (req, res) => {
  const todo = req.body;
  console.log ("post "+ todo)
  todo_db.todos.push(todo);
  res.sendStatus(200);
});
// 할일 변경
app.put("/api/v1/todos/:id", (req, res) => {
  const index = todo_db.todos.findIndex((todo) => todo.id == req.params.id);
  if(index != -1) {
    todo_db.todos[index] = req.body;
    res.sendStatus(200);
  }
  else {
    res.sendStatus(404);
  }
});
// 할일 삭제
app.delete("/api/v1/todos/:id", (req, res) => {
  todo_db.todos = todo_db.todos.filter((todo) => todo.id != req.params.id);
  res.sendStatus(200);
});
// 나머지 URL
app.use((err, req, res, next) => {
  res.status(500).send(err.message);
});
