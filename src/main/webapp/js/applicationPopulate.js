/**
 * 
 */
axios.get("getClass").then((response)=>{
	document.getElementById("batch").innerHTML = ""
	response.data.jsonarray.map((e)=>{
			var template = `
		<option value="${e.cId}"><div class="row">
			<div class="d-flex flex-column justify-content-between">
  				<div class="p-2 justify-content-between">${e.cName}</div>
  				<div class="p-2 justify-content-between">subject : ${e.subject}</div>
			</div>
		</div></option>
		`;
		document.getElementById("batch").innerHTML += template;
		
	})
})

axios.get("ApplicationStatus").then((response)=>{
	console.log(response)
	let tsbody = document.getElementById("tableData");
	tsbody.innerHTML = "";
	
	response.data.jsonarray.map((e)=>{
		var template = `
				<tr id="${e.suid}">
						<td>${e.stdName}</td>
						<td>${e.email}</td>
						<td>${e.phone}</td>
						<td>${e.subject}</td>
						<td>${e.class}</td>
						<td>
							<button type="button" class="btn btn-success editbtn"> ACCEPT </button>
						</td>
						<td>
							<button type="button" class="btn btn-danger deletebtn"> DELETE </button>
						</td>
					</tr>
		`
		tsbody.innerHTML += template;
	})
})