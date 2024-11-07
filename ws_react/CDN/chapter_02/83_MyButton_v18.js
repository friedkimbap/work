function MyButton(props) {
    const [isClicked, setIsClicked] = React.useState(false);

    return React.createElement(
        'button',
        {onClick: () => setIsClicked(true)},
        isClicked ? 'Clicked!' : 'Click here!'
    )
}

const root =  ReactDOM.createRoot(document.querySelector('#root'));
root.render(React.createElement(MyButton));