/**
 * 
 */
var fnameError = document.getElementById('fname-error'); // get the id to create the display message in html
var addrError = document.getElementById('addr-error');
var lnameError = document.getElementById('lname-error');
var phError = document.getElementById('ph-error');
var userError = document.getElementById('user-error');
var emailError = document.getElementById('email-error');
var passError = document.getElementById('pass-error'); // for password
var cpassError = document.getElementById('confirm-error'); //reset pass for confirm pass
var jobError = document.getElementById('job-error');
var fileError = document.getElementById('file-error');
var sError = document.getElementById('submit-error');


const isValidnumber = phone => {
    const re = /^(^(\+639)(\d){9}$)|(^(09)\d{9}$)$/;
    return re.test(phone);
}
const isValidEmail = email => {
    const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
}

const password_regex = password => {

    const re = /^(?=.*?[0-9])(?=.*?[A-zA-Z])(?=.*?[#?!@$%^&*\-_]).{8,}$/;
    return re.test(String(password).toLowerCase());

}

function validateName() {


    var firstName = document.getElementById('fname').value;

    if (firstName.length == 0) {

        fnameError.innerHTML = 'Name is Required';

        return false;
    } else {
        fnameError.innerHTML = "<span style='color:green'>Valid</span>";

        return true;
    }


}


function validateAddr() {


    var addr = document.getElementById('addr').value;

    if (addr.length === 0 || addr === '') {

        addrError.innerHTML = 'Address is Required';

        return false;
    } else {
        addrError.innerHTML = "<span style='color:green'>Valid</span>";

        return true;
    }


}

function validateLname() {


    var lname = document.getElementById('lname').value;

    if (lname.length == 0 || addr === '') {

        lnameError.innerHTML = 'Last Name is Required';

        return false;
    } else {
        lnameError.innerHTML = "<span style='color:green'>Valid</span>";

        return true;
    }


}

function validateNumber() {


    var num = document.getElementById('phone').value;

    if (num.length == 0) {

        phError.innerHTML = 'Phone is Required';

        return false;
    } else if (!isValidnumber(num)) {

        phError.innerHTML = 'Phone number is Invalid';

        return false;
    } else {
        phError.innerHTML = "<span style='color:green'>Valid</span>";

        return true;
    }


}

function validateUname() {


    var username = document.getElementById('username').value;

    if (username.length == 0 || username === '') {

        userError.innerHTML = 'Username is  Required';

        return false;
    } else {
        userError.innerHTML = "<span style='color:green'>Valid</span>";

        return true;
    }


}

function validateJob() {


    var job = document.getElementById('job').value;

    if (job.length == 0 || job === '') {

        jobError.innerHTML = 'Job Title is  Required';

        return false;
    } else {
        jobError.innerHTML = "<span style='color:green'>Valid</span>";

        return true;
    }


}




function validateEmail() {


    var email = document.getElementById('email').value;

    if (email.length == 0 || email === '') {

        emailError.innerHTML = 'Email  is Required';

        return false;
    } else if (!isValidEmail(email)) {

        emailError.innerHTML = 'Email is Invalid';
        return false;

    } else {
        emailError.innerHTML = "<span style='color:green'>Valid</span>";

        return true;
    }


}



function validatePass() {



    var pass = document.getElementById('password').value;

    if (pass.length == 0 || pass === '') {

        passError.innerHTML = 'Password  is Required';

        return false;
    } else if (pass.length < 8) {

        passError.innerHTML = 'Password minimum is 8 characters';
        return false;

    } else if (!password_regex(pass)) {

        passError.innerHTML = 'Need to contain special characters and Letters and numbers';
        return false;

    } else {
        passError.innerHTML = "<span style='color:green'>Valid</span>";

        return true;
    }


}



function validateConfirmpass() {


    var cpass = document.getElementById('cpass').value;
    let pass = document.getElementById('password').value;
    if (cpass.length == 0 || password === '') {

        cpassError.innerHTML = 'Password  is Required';

        return false;
    } else if (cpass !== pass) {
        cpassError.innerHTML = 'Password  doesnt Match';
    } else if (cpass.length < 8) {

        cpassError.innerHTML = 'Password minimum is 8 characters';
        return false;

    } else if (!password_regex(cpass)) {

        cpassError.innerHTML = 'Need to contain special characters and Letters and numbers';
        return false;

    } else {
        cpassError.innerHTML = "<span style='color:green'>Valid</span>";

        return true;
    }


}

















function validateFile() {

    var file = document.getElementById('files');




    var fileName = document.getElementById('files').value;
    var allowed_extensions = new Array("jpg", "png", "gif");
    var file_extension = fileName.split('.').pop().toLowerCase(); // split function will split the filename by dot(.), and pop function will pop the last element from the array which will give you the extension as well. If there will be no extension then it will return the filename.

    for (var i = 0; i <= allowed_extensions.length; i++) {
        if (allowed_extensions[i] == file_extension) {
            fileError.innerHTML = "<span style='color:green'>Valid</span>";
            return true; // valid file extension
        }
    }

    fileError.innerHTML = 'File is invalid submit again to check if the file is valid';
    file.value = '';
    return false;




}




function validateEditFile() {

    var file = document.getElementById('files');




    var fileName = document.getElementById('files').value;
    var allowed_extensions = new Array("jpg", "png", "gif","jpeg");
    var file_extension = fileName.split('.').pop().toLowerCase(); // split function will split the filename by dot(.), and pop function will pop the last element from the array which will give you the extension as well. If there will be no extension then it will return the filename.


    if (fileName === '' || fileName.length === 0) {

        fileError.innerHTML = "<span style='color:green'>Previous File Will Used</span>";
        return true; // valid file extension

    } else {


        for (var i = 0; i <= allowed_extensions.length; i++) {
            if (allowed_extensions[i] == file_extension) {
                fileError.innerHTML = "<span style='color:green'>Valid</span>";
                return true; // valid file extension
            }
        }

        fileError.innerHTML = 'File is invalid submit again to check if the file is valid';
        file.value = '';
        return false;

    }


}

















//  for login pass
function validateLoginPass() {


    var pass = document.getElementById('password').value;

    if (pass.length == 0 || pass === '') {

        passError.innerHTML = 'Password is  Required';

        return false;
    } else {
        passError.innerHTML = "<span style='color:green'>Valid</span>";

        return true;
    }


}





function validateForm() {

    if (!validateName() || !validateAddr() || !validateLname() || !validateNumber() || !validateUname || !validateEmail || !validatePass() || !validateJob() || !validateFile()) {
        sError.innerHTML = "Fix Field Error";
        setTimeout(function() { sError.style.display = 'none'; }, 3000);
        return false;


    }


}

function validateLoginForm() {

    if (!validateUname() || !validateLoginPass()) {
        sError.innerHTML = "Fix Field Error";
        setTimeout(function() { sError.style.display = 'none'; }, 3000);
        return false;


    }


}

function validateForgotPass() {

    if (!validateEmail()) {
        sError.innerHTML = "Fix Field Error";
        setTimeout(function() { sError.style.display = 'none'; }, 3000);
        return false;


    }


}

function validateResetPass() {

    if (!validatePass() || !validateConfirmpass()) {
        sError.innerHTML = "Fix Field Error";
        setTimeout(function() { sError.style.display = 'none'; }, 3000);
        return false;


    }


}

function validateUpdate() {



    if (!validateName() || !validateAddr() || !validateLname() || !validateNumber() || !validateEmail() || !validateJob() || !validateEditFile()) {
        sError.innerHTML = "Fix Field Error";
        setTimeout(function() { sError.style.display = 'none'; }, 3000);
        return false;


    }

}



function validateCreateForm() {

    if (!validateName() || !validateAddr() || !validateLname() || !validateNumber() || !validateUname || !validateEmail || !validatePass() || !validateJob() || !validateEditFile()) {
        sError.innerHTML = "Fix Field Error";
        setTimeout(function() { sError.style.display = 'none'; }, 3000);
        return false;


    }


}
