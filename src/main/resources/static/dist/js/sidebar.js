
    $(document).ready(function() {
    // make an Ajax request to the server to get the iconPath
    $.ajax({
        type: "GET",
        url: "/getUserIconPath",
        data: {},
        success: function(response) {
            // check if the response contains an iconPath
            if (response.iconPath) {
                // if the iconPath exists, set the background image of the div to the iconPath
                $('#user-icon').attr('src', response.iconPath);
            }
        },
        error: function(xhr, status, error) {
            console.log(error);
        }
    });
});


    $(document).ready(function() {
    // make an Ajax request to the server to get the iconPath
    $.ajax({
        type: "GET",
        url: "/getUserIconPath",
        data: {},
        success: function(response) {
            if (response.userName) {
                // if the iconPath exists, set the background image of the div to the iconPath
                $('#user-name').text(response.userName);
            } else {
                // if the iconPath doesn't exist, set the background image to a blank image
                $('#user-name').text("USER");
            }
        },
        error: function(xhr, status, error) {
            console.log("Error: " + error);
        }
    });
});
