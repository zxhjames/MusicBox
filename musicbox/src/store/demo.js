let arr = [];
let a = {
    id: 1,
    name: 'aa'
};
let b = {
    id: 2,
    name: 'aaa'
};
let c = {
    id: 1,
    name: 'aa'
}
arr.push(a);
arr.push(b);
arr.push(c);
arr.forEach(element => {
    console.log(element.id + " " + element.name)
});


var newArr = [...new Set(arr)]
console.log(newArr)