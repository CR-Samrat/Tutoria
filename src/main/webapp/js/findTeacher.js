
var i = 0
var payload = {
	isChecked : "F"
}
var res
var i = 0;
function createCard(){
	axios.post("findTeacher",payload).then((response)=>{
	res = response;
	response.data.jsonarray.map((e)=>{
			var template = `
				<div class="card" style="width: 18rem;">
    				<div class="card-header">
      					Teacher   
    				</div><hr>
    			<div class="hr">
      				<ul class="list-group list-group-flush">
        				<li class="list-group-item" id="name">Name </li>
        				<li class="list-group-item" id="names">${e.name} </li>
        				<li class="list-group-item" id="address">Address</li>
        				<li class="list-group-item" id="add">${e.address} </li>
      			</ul>
      			<hr>
    			</div>
    			<div class="hr">
      				<ul class="list-group list-group-flush">
        				<li class="list-group-item" id="qual">Qualifications</li>
        				<li class="list-group-item" id="quali">${e.qualification}</li>
        				<li class="list-group-item" id="sub">Subjects</li>
        				<li class="list-group-item" id="subj">${e.primary}</li>
        				<li class="list-group-item" id="subj">${e.secondary}</li>
      				</ul><hr>
    			</div>
    			<div class="hr">
      			<ul class="list-group list-group-flush">
        			<li class="list-group-item" id="phone">Phone</li>
        			<li class="list-group-item" id="phones">${e.phone}</li>
        			<li class="list-group-item" id="email">Email</li>
        			<li class="list-group-item" id="phones">${e.email}</li>
      			</ul><hr>
    			</div>
    			<div class="hr">
      				<ul class="list-group list-group-flush">
      					<form action="apply" method="post">
      					<li class="list-group-item"><label for="sub">Select Subject</label> <select name="subject" id="sub">
                    			<option value="${e.primary}">${e.primary}</option>
                    			<option value="${e.secondary}">${e.secondary}</option>
                		</select></li>
                		<input type="hidden" name="teacherID" value="${e.uname}">
        				<li class="list-group-item" id="submit"><input type="submit" class="btn btn-primary btn-round mr-md-3 mb-md-0 mb-2" onclick="getData(${i})" value="Apply"></li>
      				</ul>
      				</form>
      				<hr>
    			</div>
  			</div>
        	</div>`
        	document.getElementById("content").innerHTML += template;
        	i++;
		})
	})
	
}
//onclick="apply('${e.uname}')"
createCard()
document.getElementById("check").addEventListener("click",(e)=>{
	document.getElementById("content").innerHTML = "";
	if(document.getElementById("check").checked == true){
		payload={
        	isChecked : "T"
    	}
	}else{
		payload={
        	isChecked : "F"
        	
    	}
	}
	createCard()
})