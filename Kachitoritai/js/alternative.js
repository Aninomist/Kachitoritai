function processAlternative(result) {
    // Can grab any DIV or SPAN HTML element and can then manipulate its
    // contents dynamically via javascript

    var LOD = JSON.parse(urlParams.get('LOD'));
    console.log("LOD:" + LOD);

    console.log("result:" + result);
    var js = JSON.parse(result);
    var alternatives = js["alt"];
    var length = alternatives.length;
    var upVotes
    var downVotes
    var newName

    var textarea = document.getElementById('upvoteUser1');
    textarea.value = "";
    textarea = document.getElementById('upvoteUser2');
    textarea.value = "";
    textarea = document.getElementById('upvoteUser3');
    textarea.value = "";
    textarea = document.getElementById('upvoteUser4');
    textarea.value = "";
    textarea = document.getElementById('upvoteUser5');
    textarea.value = "";
    textarea = document.getElementById('downvoteUser1');
    textarea.value = "";
    textarea = document.getElementById('downvoteUser2');
    textarea.value = "";
    textarea = document.getElementById('downvoteUser3');
    textarea.value = "";
    textarea = document.getElementById('downvoteUser4');
    textarea.value = "";
    textarea = document.getElementById('downvoteUser5');
    textarea.value = "";

    for (var i = 0; i < length; i++){
        if (alternatives[i]["noAlter"] == 1){
            var alt1  = alternatives[i];
            upVotes  = alt1["upVotes"];
            downVotes  = alt1["downVotes"];
            document.querySelector("#alt1").value = LOD[i];
            document.querySelector("#upvote1").value = upVotes.length;
            document.querySelector("#downvote1").value = downVotes.length;
            if(upVotes.length > 0){
                for (var j = 0; j < upVotes.length; j++){
                    newName = upVotes[j];
                    const textareaUP = document.getElementById('upvoteUser1');
                    textareaUP.value += newName + "\n";
                }
            }
            if(downVotes.length > 0){
                for (var j = 0; j < downVotes.length; j++){
                    newName = downVotes[j];
                    const textareaUP = document.getElementById('downvoteUser1');
                    textareaUP.value += newName + "\n";
                }
            }
        }
        if (alternatives[i]["noAlter"] == 2){
            var alt2  = alternatives[i];
            upVotes  = alt2["upVotes"];
            downVotes  = alt2["downVotes"];
            document.querySelector("#alt2").value = LOD[i];
            document.querySelector("#upvote2").value = upVotes.length;
            document.querySelector("#downvote2").value = downVotes.length;
            if(upVotes.length > 0){
                for (var j = 0; j < upVotes.length; j++){
                    newName = upVotes[j];
                    const textareaUP = document.getElementById('upvoteUser2');
                    textareaUP.value += newName + "\n";
                }
            }
            if(downVotes.length > 0){
                for (var j = 0; j < downVotes.length; j++){
                    newName = downVotes[j];
                    const textareaUP = document.getElementById('downvoteUser2');
                    textareaUP.value += newName + "\n";
                }
            }
        }
        if (alternatives[i]["noAlter"] == 3){
            var alt3 = alternatives[i];
            upVotes  = alt3["upVotes"];
            downVotes  = alt3["downVotes"];
            document.querySelector("#alt3").value = LOD[i];
            document.getElementById('nav-item-3').style.visibility = 'visible';
            document.querySelector("#upvote3").value = upVotes.length;
            document.querySelector("#downvote3").value = downVotes.length;
            if(upVotes.length > 0){
                for (var j = 0; j < upVotes.length; j++){
                    newName = upVotes[j];
                    const textareaUP = document.getElementById('upvoteUser3');
                    textareaUP.value += newName + "\n";
                }
            }
            if(downVotes.length > 0){
                for (var j = 0; j < downVotes.length; j++){
                    newName = downVotes[j];
                    const textareaUP = document.getElementById('downvoteUser3');
                    textareaUP.value += newName + "\n";
                }
            }
        }
        if (alternatives[i]["noAlter"] == 4){
            var alt4  = alternatives[i];
            upVotes  = alt4["upVotes"];
            downVotes  = alt4["downVotes"];
            document.querySelector("#alt4").value = LOD[i];
            document.getElementById('nav-item-4').style.visibility = 'visible';
            document.querySelector("#upvote4").value = upVotes.length;
            document.querySelector("#downvote4").value = downVotes.length;
            if(upVotes.length > 0){
                for (var j = 0; j < upVotes.length; j++){
                    newName = upVotes[j];
                    const textareaUP = document.getElementById('upvoteUser4');
                    textareaUP.value += newName + "\n";
                }
            }
            if(downVotes.length > 0){
                for (var j = 0; j < downVotes.length; j++){
                    newName = downVotes[j];
                    const textareaUP = document.getElementById('downvoteUser4');
                    textareaUP.value += newName + "\n";
                }
            }
        }
        if (alternatives[i]["noAlter"] == 5){
            var alt5  = alternatives[i];
            upVotes  = alt5["upVotes"];
            downVotes  = alt5["downVotes"];
            document.querySelector("#alt5").value = LOD[i];
            document.getElementById('nav-item-5').style.visibility = 'visible';
            document.querySelector("#upvote5").value = upVotes.length;
            document.querySelector("#downvote5").value = downVotes.length;
            if(upVotes.length > 0){
                for (var j = 0; j < upVotes.length; j++){
                    newName = upVotes[j];
                    const textareaUP = document.getElementById('upvoteUser5');
                    textareaUP.value += newName + "\n";
                }
            }
            if(downVotes.length > 0){
                for (var j = 0; j < downVotes.length; j++){
                    newName = downVotes[j];
                    const textareaUP = document.getElementById('downvoteUser5');
                    textareaUP.value += newName + "\n";
                }
            }
        }
        upVotes  = [];
        downVotes  = [];
    }
}
function getAlternative(choiceID){
    var xhr = new XMLHttpRequest();
    xhr.open("POST", getAlternative_url, true);

    var data = {};
    data["choiceID"] = choiceID;
    var js = JSON.stringify(data);
    // send the collected data as JSON
    xhr.send(js);

    // This will process results and update HTML as appropriate.
    xhr.onloadend = function () {
        console.log(xhr);
        console.log(xhr.request);
        if (xhr.readyState == XMLHttpRequest.DONE) {

            var responseJs = JSON.parse(xhr.responseText);
            //choiceID = responseJs.response;
            reponseCode = responseJs.httpCode;

            if (reponseCode == 200) {

                console.log ("XHR:" + xhr.responseText);
                processAlternative(xhr.responseText);

                //signInHTML = signInURL + "?choiceID="  + choiceID;
                //console.log(signInHTML)
                //window.location.replace(signInHTML);


            } else {
                alert ("unable to process request");
            }
        } else {
            //processResponse("N/A");
        }
    };
}


function updateAlt(choiceID, noAlter){
	getAlternative(choiceID);
	var LOD = JSON.parse(urlParams.get('LOD'));
	document.getElementById('currentAltDes').innerHinnerHTML = 'Description of selected Alternative:' + LOD[noAlter];
}
