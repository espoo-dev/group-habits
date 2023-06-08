//= require_self

$(document).ready(function () {
  $('#reset_password_form').submit(function (event) {
    event.preventDefault();

    const params = $(this).data('params')

    const headers = {
      uid: params.uid,
      client: params.client,
      'access-token': params['access-token']
    }

    $.ajax({
      headers,
      url: $(this).attr('action'),
      type: "PUT",
      datatype: "application/js",
      data: $(this).serialize(),
      success: function (response) {
        alert(response.message)
      },
      error: function (_XMLHttpRequest, textStatus, errorThrown) {
        alert("Status: " + textStatus); alert("Error: " + errorThrown);
      }
    })

    return false;
  });
})
