// const updateDetail=document.querySelector('#updateDetail');
// updateDetail.addEventListener('click', requestFn);



function requestFn(){


    function stateFn(){

        if(xhr.status === 200){
            if(xhr.readyState===4){

            console.log(xhr.responseText);

            var data=JSON.stringify(xhr.responseText);

            var output='';
                
            data.forEach(element => {
                output = output + '<form th:action="@{/member/update}"  method="post">'
                + '<ul>'
                +    '<li class="s1">Update Information</li>'
                +    '<li class="s2"> <label for="email">email</label>'
                +   '<input type="text"  id="email" name="email" th:value="${'+element.email+'}"></li>'
                +   '<li class="s2">'
                +   '<label for="password">password</label>'
                +   '<input type="password" id="password" name="password" th:value=${'+element.password+'}></li>'
                +   '<li class="s3">'
                +   '<label for="gender">gender</label>'
                +   '<input type="text" id="gender" name="gender" th:value=${'+element.gender+'}></li>'
                +   '<li class="s4">'
                +   '<input type="submit" value="Update" class="s3sub">'
                +   '<button type="button" class="s3sub"><a th:href="@{/index}">Home</a></button></li>'
                +   '</ul>'
            });

            document.querySelector('#innerHtmlElement').push(output);

          }

        }
    }



    const xhr=new XMLHttpRequest();
    xhr.open('get', 'http://localhost:8099/member/updateP/{id}', true);

    xhr.onload = stateFn;
    xhr.send();


}









