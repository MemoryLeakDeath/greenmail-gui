$('.userSelection').click(viewUserEmails);
   
function viewUserEmails() {
    var email = $(this).data('email');
    var emailForm = $('#viewEmailForm');
      
    emailForm.find('input[name="email"]').val(email);
    emailForm.submit();
}
