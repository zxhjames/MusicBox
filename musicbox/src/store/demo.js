// let arr = [];
// let a = {
//     id: 1,
//     name: 'aa'
// };
// let b = {
//     id: 2,
//     name: 'aaa'
// };
// let c = {
//     id: 1,
//     name: 'aa'
// }
// arr.push(a);
// arr.push(b);
// arr.push(c);
// arr.forEach(element => {
//     console.log(element.id + " " + element.name)
// });


// var newArr = [...new Set(arr)]
// console.log(newArr)

let a = 'tea';
let b = 'aet';
let str1 = a.split('');
let str2 = b.split('');
str1.sort()
str2.sort();
console.log(str1.toString() == str2.toString())


let str = ["eat", "tea", "tan", "ate", "nat", "bat"];
var groupAnagrams = function (strs) {
    let slist = [
        []
    ];
    for (let str in strs) {
        let flag = false;
        let len = slist.length;
        if (len != 0) {
            for (let i = 0; i < len; ++i) {
                let ss = slist[i];
                let s = ss[0];
                let s1 = str.split('');
                let s2 = s.split('');
                s1.sort();
                s2.sort();
                if (s.length != str.length || s1.toString() != s2.toString()) {
                    continue;
                }
                flag = true;
                slist[i].push(str);
            }
        }
        if (!flag || len == 0) {
            let l = [];
            l.push(str);
            slist.push(l);
        }
    }
    return slist;
};
console.log(groupAnagrams(str))