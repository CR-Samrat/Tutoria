/**
 * 
 */
 var uname = document.getElementById("uname");
 var pass = document.getElementById("pass");
 var form = document.getElementById("login")
var teacherSelect =  document.getElementById('teacherSelect');
var studentSelect = document.getElementById('studentSelect');
var result ;

 teacherSelect.addEventListener("click",(e)=>{
	result = "tch"; // select teacher
	document.getElementById("choiceSelection").hidden = true;
	document.getElementById("login").hidden = false;
})


studentSelect.addEventListener("click",(e)=>{
	result = "std"; //select student
	document.getElementById("choiceSelection").hidden = true;
	document.getElementById("login").hidden = false;
})


 form.addEventListener("submit",(e)=>{
	e.preventDefault();
	const payload={
		uname : uname.value,
		pass :pass.value,
		result :result
	}
	axios.post("login",payload).then((response)=>{
		if((response.data.message)== "success"){
			axios.get("isProfile").then((response)=>{
				if(response.data.isProfile == true){
					if(response.data.post == "teachers"){
						console.log("teacher dashboard")
						window.location = 'http://localhost:8080/Tutoria/teacherDashboard.html';
					}else{
						console.log("std dashboard")
						window.location = 'http://localhost:8080/Tutoria/studentDashboard.html';
					}
				}else{
					if(response.data.post == "teachers"){
						window.location = 'http://localhost:8080/Tutoria/teacherProfileCreation.html';
					}else{
						window.location = 'http://localhost:8080/Tutoria/studentProfileCreation.html';
					}
				}
			})
		}else{
			document.getElementById("error").innerHTML = "inavlid credentials"
		}
	})
})