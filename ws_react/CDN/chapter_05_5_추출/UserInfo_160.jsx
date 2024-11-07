function UserInfo(props) {
    return (
        <div className="user-info">
            <Avatar user={props.author} />
            <div className="user-info-name">
                {props.user.name}
            </div>
        </div>
    );
}