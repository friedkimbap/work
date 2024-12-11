import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import styled from 'styled-components';
import CommentList from '../../component/list/CommentList';
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

const PostContainer = styled.div`
    padding: 8px 16px;
    border: 1px solid grey;
    border-radius: 8px;
`;

const TitleText = styled.p`
    font-size: 28px;
    font-weight: 500;
`;

const ContentText = styled.p`
    font-size: 20px;
    line-height: 32px;
    white-space: pre-wrap;
`;

const CommentLabel = styled.p`
    font-size: 16px;
    font-weight: 500;
`;

const ServerIP = "localhost";
//const ServerIP = "192.168.43.194"; // 핸드폰에서 접속할때

function PostViewPage(props) {
    const navigate = useNavigate();
    const { postId } = useParams();

    const [post, setPost] = useState({});
    const [comments, setComments] = useState([]);
    const [fetched, setFetched] = useState(true);
    const url = `http://${ServerIP}:8000/api/v1/posts/${postId}`;
    // [fetched]가 없으면 axios.get()이 계속 호출됨
    // axios 사용법 : https://axios-http.com/docs/example
    useEffect(() => {
        axios.get(url)
        .then(response => setPost(response.data) )
            //.then(response => {post = response.data; setPost(post)} ) 와 결과 동일
        .catch(err=>console.log(err));
        },[fetched]);
    // setComments(comments) 가 없으면 글만 표시되고 댓글 표시 없음
    // [fetched]가 없으면 axios.get()이 계속 호출됨
    useEffect(() => {
        axios.get(url)
        .then(response => setComments(response.data.comments) )
            //.then(response => {comments = response.data.comments; setComments(comments)} )와 결과 동일
        .catch(err=>console.log(err));
        },[fetched]);

    const [comment, setComment] = useState('');
    // axios 사용법 : https://axios-http.com/docs/post_example
    const insertComment = () =>
        axios.post(url, {
            id: post.id,
            title: post.title,
            content: post.content,
            comments: [
                {
                id: null,
                content: comment
                } 
            ]
            })
            .then((response)=> {
                console.log(response.data);
            })
            .catch((error) => {
                console.error(error);
            });

    return (
        <Wrapper>
            <Container>
                <Button
                    title='뒤로 가기'
                    onClick={() => {
                        navigate('/');
                    }}
                />
                <PostContainer>
                    <TitleText>{post.title}</TitleText>
                    <ContentText>{post.content}</ContentText>
                </PostContainer>

                <CommentLabel>댓글</CommentLabel>
                <CommentList comments={comments} />

                <TextInput
                    height={40}
                    value={comment}
                    onChange={(event) => {
                        setComment(event.target.value);
                    }}
                />
                <Button
                    title='댓글 작성하기'
                    onClick={() => {
                        insertComment();
                        navigate('/');
                    }}
                />
            </Container>
        </Wrapper>
    );
}

export default PostViewPage;
