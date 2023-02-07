const updateDetail = document.querySelector('#updateDetail a');
const updateCon = document.querySelector('.update');
const memberContainer = document.querySelector('.membercontainer');
const no= document.querySelector('#no');

updateDetail.addEventListener('click', stateFn);
//const queryString='/member/updateP/'+no.value.toString();
//console.log('this is a queryString : '+queryString);
//console.log('Type of the delivered data: '+typeof no.value);


//xhr.onload = function() {
//  alert(`Loaded: ${xhr.status} ${xhr.response}`);
//};
//
//xhr.onerror = function() { // only triggers if the request couldn't be made at all
//  alert(`Network Error`);
//};
//
//xhr.onprogress = function(event) { // triggers periodically
//  // event.loaded - how many bytes downloaded
//  // event.lengthComputable = true if the server sent Content-Length header
//  // event.total - total number of bytes (if lengthComputable)
//  alert(`Received ${event.loaded} of ${event.total}`);
//};

function stateFn(e) {
    e.preventDefault();
//    memberContainer.style.display='none';
//    updateCon.style.display='flex';

    xhr.open('get', 'member/updateP?id={id}', true);

    xhr.setRequestHeader("Content-Type", "text/xml;charset=utf8");

    xhr.onreadystatechange = () => {
        if (xhr.readyState == 4) {
            if (xhr.status == 200) {

                const data = xhr.responseText;
                var jsonob = eval('(' + data + ')');
                var email = document.querySelector('#email');
                var password = document.querySelector('#password');
                var gender = document.querySelector('#gender');

                var result = jsonob.result;

                email.value = result.email;
                password.value = result.password;
                gender.value = result.gender;

                return false;


            }
        }
    }
xhr.send(null);
}

