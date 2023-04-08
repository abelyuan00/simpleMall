 function onSubmit(event) {
    event.preventDefault();
    const form = document.getElementById('register-form');
    const passwordInput = document.getElementById('password');

    const loginName = document.getElementById('loginName');
    const email=document.getElementById('email').value;

    const confirmPasswordInput = document.getElementById('confirm-password');
    const recaptchaResponse = grecaptcha.getResponse();


     if (!isValidEmail(email)) {
         alert('Please provide valid email');
         return;
     }

     const hasLetters = /[a-zA-Z]/.test(loginName.value);
     if(!hasLetters){
         alert('Your log in name need letters');
         return;
     }

     if (loginName.value.length < 6) {
         alert('You need a longer name');
         return;
     }

    if (passwordInput.value !== confirmPasswordInput.value) {
    alert('Password and Confirm Password do not match!');
    return;
}

    if (!recaptchaResponse) {
    alert('Please tick the \"I\'m not a robot!\"');
    return;
}
    form.submit();
}

 function isValidEmail(email) {
     // Regular expression to match email format
     const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
     return emailRegex.test(email);
 }

