import axios from 'axios';

function RestClient(props) {
    const url = 'http://localhost:8000/api/v1/todos';
    // 사용자 입력과 출력 방법 예제
    function input() {
        // 사용자가 입력한 값을 가져오기
        const input = document.getElementById('input').value;
        // 가져온 값을 출력할 요소에 설정하기
        document.getElementById('output').innerText = input;
    }
    // 할일 전체 보기
    // RP header : content-type : application/json
    // RP body : [{"id":1,"task":"..."},{"id":2,"task":"..."}...]
    const getTodoAll = () => {
        axios.get(url)
            .then( (todos) => {
                // console.log(todos.data);
                const output = document.getElementById('getTodoAll');
                output.innerText = JSON.stringify(todos.data);
                } )
            .catch(err => console.log(err));        
    }
    // 1가지 할일 보기
    const getTodoNum = () => {
        const input = document.getElementById('getTodoNumIn').value;
        axios.get(`${url}/${input}`)
            .then( (todos) => {
                console.log(todos.data);
                const output = document.getElementById('getTodoNumOut');
                output.innerText = JSON.stringify(todos.data);
                } )
            .catch(err => console.log(err));        
    }
    // 할일 등록
        // RQ header : content-type : application/json
        // RQ body : {"id":4,"task":"4 타스크"}
    const insertTodo = () => {
       const idValue = parseInt(document.getElementById('insertIdValue').value);
       const taskValue = document.getElementById('insertTaskValue').value;
       axios.post(url, {
           id: idValue,
           task: taskValue
           })
           .then((response)=> {
               console.log(response.data);
           })
           .catch((error) => {
               console.error(error);
           });
    }
    // 할일 변경
    const updateTodo = () => {
        const idValue = parseInt(document.getElementById('updateIdValue').value);
        const taskValue = document.getElementById('updateTaskValue').value;
        axios.put(`${url}/${idValue}`, {
            id: idValue,
            task: taskValue
            })
            .then((response)=> {
                console.log(response.data);
            })
            .catch((error) => {
                console.error(error);
            });
    }
    // 할일 삭제
    const deleteTodo = () => {
        const input = document.getElementById('deleteTodo').value;
        axios.delete(`${url}/${input}`)
            .then((response)=> {
                console.log(response.data);
            })
            .catch((error) => {
                console.error(error);
            });      
    }
    return (
    <div>
        입력값을 출력해보기<br/>
        <input type="text" id="input"/> 
        <button onClick={input}>입력하기</button> <br/>
        출력: <span id="output"></span>
        <hr/>
        cRud(전체 할일)<br/>
        <button onClick={getTodoAll}>전체 할일 보기</button> <br/>
        출력: <span id="getTodoAll"></span>
        <hr/>
        cRud(1가지 할일)<br/>
        <input type="text" id="getTodoNumIn"/> 
        <button onClick={getTodoNum}>할일 번호입력하기</button> <br/>
        출력: <span id="getTodoNumOut"></span>
        <hr/>
        Crud<br/>
        id <input type="text" id="insertIdValue"/> <br/>
        task <input type="text" id="insertTaskValue"/>
        <button onClick={insertTodo}>할일 등록</button> <br/>
        <hr/>
        crUd<br/>
        id <input type="text" id="updateIdValue"/> <br/>
        task <input type="text" id="updateTaskValue"/>
        <button onClick={updateTodo}>할일 변경</button> <br/>
        <hr/>
        cruD(목록번호)<br/>
        <input type="text" id="deleteTodo"/>
        <button onClick={deleteTodo}>할일 삭제</button> <br/>
        <hr/>
    </div>
    );
}

export default RestClient;
