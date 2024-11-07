// 값으로 String 타입만을 사용한 객체
let obj1 = { a: "apple", b:"banana", c:"carrot" };

// 값으로 Number 타입만을 사용한 객체
let obj2 = { a: 1, b: 2, c:2};

// 값으로 다양한 자료형들을 함께 사용한 객체
let obj3 = { a:"hello", b: 100, c:[1,2,3,4]};

// 값으로 객체를 사용한 객체
let obj4 = {
    a: {a1:1, a2:2},
    b: {b1:3, b2:4 },
    c: {c1:5, c2:6},
};

console.log(obj1['a']);
// 출력결과: apple
//console.log(obj1[a]); // error발생

console.log(obj2.a);
// 출력결과: 1
//console.log(obj2.'a'); // error 발생

console.log(obj3['c']);
// 출력 결과: [1,2,3,4]

console.log(obj4.c.c2);
// 출력 결과: 6