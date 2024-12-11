import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import TextInput from '../../component/ui/TextInput';
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

function PostWritePage(props) {
    const navigate = useNavigate();
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    
    // axios 사용법 : https://axios-http.com/docs/post_example
    const url = 'http://'+ServerIP+':8000/api/v1/posts';
    const insertPost = () => 
        axios.post(url, {
            id: null,
            title: title,
            content: content,
            comments: []
            })
            .then((response)=> {
                console.log("insertPost() result " + response.data);
            })
            .catch((error) => {
                console.error(error);
            });

    return (
        <Wrapper>
            <Container>
                <TextInput
                    height={20}
                    value={title}
                    onChange={(event) => {
                        setTitle(event.target.value);
                    }}
                />

                <TextInput
                    height={480}
                    value={content}
                    onChange={(event) => {
                        setContent(event.target.value);
                    }}
                />

                <Button
                    title='글 작성하기'
                    onClick={() => {
                        insertPost();
                        navigate('/');
                    }}
                />
            </Container>
        </Wrapper>
    );
}

export default PostWritePage;
