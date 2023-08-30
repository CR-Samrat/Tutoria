var uname = document.getElementById("uname");
var email = document.getElementById("email");
var phone = document.getElementById("phone");
var pass = document.getElementById("pass");
var cpass = document.getElementById("cpass")
var teacherSelect =  document.getElementById('teacherSelect');
var studentSelect = document.getElementById('studentSelect');
var result ;
var userError =document.getElementById("userError")
var emailError = document.getElementById("emailError")
var phoneError = document.getElementById("phoneError")


teacherSelect.addEventListener("click",(e)=>{
	result = "tch"; // select teacher
	document.getElementById("choiceSelection").hidden = true;
	document.getElementById("Register").hidden = false;
})


studentSelect.addEventListener("click",(e)=>{
	result = "std"; //select student
	document.getElementById("choiceSelection").hidden = true;
	document.getElementById("Register").hidden = false;
})
function resetValue(){
	userError.innerHTML = "";
	emailError.innerHTML = "";
	phoneError.innerHTML = "";
	
}
function validate(res){
	if(res === "duplicate username"){
		userError.innerHTML = "Username already exist"
	}else if(res === "duplicate email"){
		emailError.innerHTML = "Email number already exist"
	}else if(res === "duplicate phone"){
		phoneError.innerHTML = "Phone number already exist"
	}
}
const form = document.getElementById("Register").addEventListener("submit",(e)=>{
    e.preventDefault();
    if(pass.value == cpass.value){
		const payload={
        	uname : uname.value,
        	email : email.value,
        	phone : phone.value,
        	pass : pass.value,
        	result : result
    	}
    	console.log(payload);
    	axios.post('register',payload).then((response)=>{
			resetValue()
			console.log(response);
			if(response.data.message == "failed"){
				validate(response.data.QueryMessage);
			}else if(response.data.message == "success"){
				//code for redirect to login page to be written here
				alert("Registration Succesfull");
			}
			
   		})
	}
    
})
// complete total validation like number of password character etc required fields etc......