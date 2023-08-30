
 axios.get("appStatus").then((response)=>{
	response.data.map((e)=>{
		var temp = `<div class="accordion">
  				<div class="accordion-item">
    				<h7 class="accordion-header" id="headingOne">
    					Application for Student Name : ${e.suid} applied Under  Teacher Name : ${e.tuid} for subject : ${e.subject} has been ${e.status}
   				 	</h7>
  			</div>`
  			if(e.status == "pending"){
				document.getElementById("pending").innerHTML += temp;
			}else if(e.status == "accepted"){
				document.getElementById("accepted").innerHTML += temp;
			}else{
				document.getElementById("rejected").innerHTML += temp;
			}
	})
})
var temp = `<div class="accordion" id="accordionExample">
  				<div class="accordion-item">
    				<h2 class="accordion-header" id="headingOne">
      					
        					Accordion Item #1
   				 	</h2>
  			</div>`