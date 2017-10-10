/**
 * Created by matioyoshitoki on 2017/9/11.
 */
function logout() {
    $.ajax({
        cache: true,
        type: "GET",
        url: "/logout",
        // data: {session_id: getCookie("session_id")},
        async: false,
        error: function (request) {
            alert("Connection error");
        },
        success: function (data) {
            document.cookie = "session_id=; expires=Thu, 01 Jan 1970 00:00:00 GMT";
            document.cookie = "isLogin=0";
            document.cookie = "user_name=; expires=Thu, 01 Jan 1970 00:00:00 GMT"
            document.cookie = "user_icon=; expires=Thu, 01 Jan 1970 00:00:00 GMT"
            document.cookie = "user_level=; expires=Thu, 01 Jan 1970 00:00:00 GMT"
            easyDialog.open({
                container: {
                    content: "log out～"
                },
                autoClose: 1000
            });
            location.href = "/";

        }
    });
}