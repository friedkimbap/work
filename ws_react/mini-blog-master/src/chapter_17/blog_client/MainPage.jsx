// 모던 리액트 프로그래밍 5장 220쪽
import React from 'react';
import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import PostList from '../../component/list/PostList';
import Button from '../../component/ui/Button';
import axios from 'axios';

const Wrapper = styled.div`
    padding: 16px;
    width: calc(100% - 32px);
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
`;

const Container = styled.div`
    width: 100%;
    max-width: 720px;

    :not(:last-child) {
        margin-bottom: 16px;
    }
`;

const ServerIP = "localhost";
//const ServerIP = "192.168.43.194"; // 핸드폰에서 접속할때

function MainPage(props) {
    const navigate = useNavigate();

    const [posts, setPosts] = useState([]);
    const [fetched, setFetched] = useState(true);
    const url = 'http://'+ServerIP+':8000/api/v1/posts';

    // axios 사용법 : https://axios-http.com/docs/example
    // [fetched]가 없으면 axios.get()이 계속 호출됨
    useEffect(() => {
      axios.get(url)
        .then(response => setPosts(response.data) )
        //.then(response => {posts = response.data; setPosts(posts)} ) 와 결과 동일
        .catch(err=>console.log(err));
    },[fetched]);
   
    return (
        <Wrapper>
            <Container>
                <Button
                    title='글 작성하기'
                    onClick={() => {
                        navigate('/post-write');
                    }}
                />
                <PostList
                    posts={posts}
                    onClickItem={(item) => {
                        navigate(`/post/${item.id}`);
                    }}
                />
            </Container>
        </Wrapper>
    );
}   
export default MainPage;

