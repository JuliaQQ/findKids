/**
 * Created by matioyoshitoki on 2017/9/11.
 */
function login(){
    $.ajax({
        cache: true,
        type: "POST",
        url:"/log",
        data:$('#loginBox').serialize(),
        async: false,
        error: function(request) {

            alert($('#loginBox').serialize().data());
        },
        success: function(data) {
            //var json = JSON.parse(data);
            var json = data;
            if (json.status == "0") {
                document.cookie="session_id="+json.session_id;
                var btnFn = function() {
                    location.href = "/";
                    return true;
                };

                easyDialog.open({
                    container : {
                        header : 'Message',
                        content : 'Regist Success~',
                        yesFn : btnFn,
                        noFn : true
                    }
                });

//						RebindTJ.LogStat.login($.cookie("userid"), RebindTJ.LogStat.LogType.nameAndPwd, RebindTJ.LogStat.LogScene.web, RebindTJ.LogStat.LogSource.direct, RebindTJ.LogStat.LogErr.pwdErr);
            } else if (json.status == "1") {
//						RebindTJ.LogStat.login($.cookie("userid"), RebindTJ.LogStat.LogType.nameAndPwd, RebindTJ.LogStat.LogScene.web, RebindTJ.LogStat.LogSource.direct, RebindTJ.LogStat.LogErr.success);

                var btnFn = function() {
//                        location.href = "/";
                    return true;
                };

                easyDialog.open({
                    container : {
                        header : 'Message',
                        content : json.info,
                        yesFn : btnFn,
                        noFn : true
                    }
                });

                //location.href = "/review";
            }
        }
    });
}