/**
 * 
 */
 /**
 * 
 */
 console.log("hello")
axios.get("isProfile").then((response)=>{
    if(response.data == ""){

    }
})
axios.get("StdData").then((response)=>{
	console.log("hello");
	if(response.data != null){
        console.log("Hello there");
		document.getElementById("fname").innerHTML = response.data.fname;
		document.getElementById("lname").innerHTML = response.data.lname;
		document.getElementById("dob").innerHTML = response.data.dob;
		if(response.data.gender == "M"){
			document.getElementById("gender").innerHTML = "Male";
		}else if(response.data.gender == "F"){
			document.getElementById("gender").innerHTML = "Female";
		}else if(response.data.gender == "O"){
            document.getElementById("gender").innerHTML = "Other" 
        }
        document.getElementById("Class").innerHTML = response.data.Class
		document.getElementById("address").innerHTML = response.data.address;
        document.getElementById("city").innerHTML = response.data.city;
        document.getElementById("state").innerHTML = response.data.state;
        document.getElementById("country").innerHTML = response.data.country;
        document.getElementById("email").innerHTML = response.data.email;
        document.getElementById("phone").innerHTML = response.data.phone;
	}
})